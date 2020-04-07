package com.example.little_chemist.Quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch2;
import com.example.little_chemist.Chapters_dir.Ch3;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.Chapters_dir.Ch5;
import com.example.little_chemist.Chapters_dir.Chapters;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.Home;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import alirezat775.lib.carouselview.Carousel;
import alirezat775.lib.carouselview.CarouselListener;
import alirezat775.lib.carouselview.CarouselModel;
import alirezat775.lib.carouselview.CarouselView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class quizQ extends AppCompatActivity {

    //private Boolean hasNextPage = true;
    private static final String TAG = quizQ.class.getSimpleName();

    public ArrayList<Integer> mImage = new ArrayList<Integer>();
    private Button nextbtn, prebtn;
    private int slidcount = 0,slidchange =0,QuizID;
    private model[] modelQuestion = new model[5];
    private ArrayList<model> ModelQuestion = new ArrayList<model>();
    private String[] currectanswer = new String[5], Option = new String[5], onlyQuestion = new String[5];
    private String[][] Question = new String[5][];
    private String[] option = new String[5];
    private double Score =0;
    private QuizAdapter quizadapter;
    private ViewPager viewPager;
    private int pagecount = 0;
    private boolean inlastslid = false;
    private CarouselView carouselview;
    //private String[] whichcontent = new String[5];
    private int [] ansrPos= new int[5];
    private int [] ansrPosV2= new int[5];

    Student student;
    String name;
    DatabaseHelper helper = new DatabaseHelper(quizQ.this);

    public quizQ(){
        Intrinsics.checkExpressionValueIsNotNull(TAG, "quizQ.class.getSimpleName()");
    }

    public final String getTAG() {
        return this.TAG;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.quizes);

        //get student info
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);

        Bundle bundle=getIntent().getExtras();
        QuizID = bundle.getInt("ChapterNumber");

        //toolbar stuff
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(quizQ.this, Chapters.class);

                switch(QuizID){
                    case 1: n = new Intent(quizQ.this, Ch1.class);
                    break;
                    case 2: n = new Intent(quizQ.this, Ch2.class);
                    break;
                    case 3: n = new Intent(quizQ.this, Ch3.class);
                    break;
                    case 4: n = new Intent(quizQ.this, Ch4.class);
                    break;
                    case 5: n = new Intent(quizQ.this, Ch5.class);
                    break;
                }

                startActivity(n);
                finish();
            }
        });

        //change background image
        ConstraintLayout Quizlayout = findViewById(R.id.CRL);
        switch (QuizID){
            case 1:
                Quizlayout.setBackgroundResource(R.drawable.ch1lessonbackground);
                break;
            case 2:
                Quizlayout.setBackgroundResource(R.drawable.ch2lessonbackground);
                break;
            case 3:
                Quizlayout.setBackgroundResource(R.drawable.ch3lessonbackgrond);
                break;
            case 4:
                Quizlayout.setBackgroundResource(R.drawable.ch4lessonbackground);
                break;
            case 5:
                Quizlayout.setBackgroundResource(R.drawable.ch5lessonbackground);
                break;
            default:Quizlayout.setBackgroundResource(R.drawable.ch1lessonbackground);
        }


        // RecyclerView
        recyclerimage();

        nextbtn = findViewById(R.id.nextBtn);
        prebtn = findViewById(R.id.preBtn);

        //_______________________CarouselView_____________________________________//
//        final adapter adapter = new adapter(this,QuizID);
//        AppCompatActivity appcomp = this;
//        carouselview = findViewById(R.id.carousel_view1);
//        final Carousel carousel = new Carousel(appcomp,carouselview,adapter);
//
//        carousel.setOrientation(carouselview.HORIZONTAL, false, true);
//        carousel.autoScroll(false, 0, false);
//        carousel.scaleView(true);
//        carousel.scrollSpeed(8000f);
//        carousel.pauseAutoScroll();
//        Log.d("pppppppppppppppppppp", String.valueOf(carouselview.isAutoScroll()));
//
//        adapter.setOnClickListener((com.example.little_chemist.Quiz.adapter.OnClick)(new com.example.little_chemist.Quiz.adapter.OnClick() {
//            public void click(@NonNull model model) {
//                Intrinsics.checkParameterIsNotNull(model, "model");
//                carousel.remove((CarouselModel)model);
//            }
//        }));
//        //carousel.scrollSpeed(100f)
//       // carousel.enableSlider(true);
//
//
//        carousel.addCarouselListener((CarouselListener)(new CarouselListener() {
//            boolean positioneqfive = false;
//            public void onPositionChange(int position) {
//                slidchange = position;
//                if (position == 0){
//                    nextbtn.setEnabled(true);
//                    prebtn.setEnabled(false);
//                    prebtn.setVisibility(View.INVISIBLE);
//                    prebtn.setText("");
//                }
//                else if (position == 4){
//                    inlastslid = true;
//                    nextbtn.setEnabled(true);
//                    prebtn.setEnabled(true);
//                    nextbtn.setVisibility(View.VISIBLE);
//                    nextbtn.setText(getText(R.string.finishBtn));
//
//                } else {
//                    nextbtn.setEnabled(true);
//                    prebtn.setEnabled(true);
//                    prebtn.setVisibility(View.VISIBLE);
//                    nextbtn.setText(getText(R.string.nextBtn));
//                    prebtn.setText(getText(R.string.backBtn));
//                }
//                Log.d(quizQ.this.getTAG(), "currentPosition : " + position);
//
//
//                if(position != 0 ) {
//                    int count = position - 1;
//                    if (adapter.getOption()[count] !=null){
//                    mImage.set(count, R.drawable.tick_mark);
//                    initRecyclerView();
//                    }
//                    else {
//                        mImage.set(count, R.drawable.wrong_mark);
//                        initRecyclerView();
//                    }
//                    if (position == 4){
//                        positioneqfive = true;
//                    }
//                }
//
//                if (position != 4 && positioneqfive){
//
//                    if (adapter.getOption()[4] !=null){
//                        mImage.set(4, R.drawable.tick_mark);
//                        initRecyclerView();
//                    }
//                    else {
//                        mImage.set(4, R.drawable.wrong_mark);
//                        initRecyclerView();
//                    }
//
//                }
//
//            }
//
//            public void onScroll(int dx, int dy) {
//                carousel.pauseAutoScroll();
//                Log.d(quizQ.this.getTAG(), "onScroll dx : " + dx + " -- dy : " + dx);
//            }
//        }));
        //_______________________CarouselView_____________________________________//



        //find out which chapter quiz
        String[] QItems;
        int index =0;
        String [] whichcontent;
        switch (QuizID){
            case 1:whichcontent = C1Quiz;
            break;
            case 2:whichcontent = C2Quiz;
            break;
            case 3:whichcontent = C3Quiz;
            break;
            case 4:whichcontent = C4Quiz;
            break;
            case 5:whichcontent = C5Quiz;
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + QuizID);
        }

        //random question
        int[] Qindex = new int [5];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<whichcontent.length; i++) {
            list.add(new Integer(i));
        }

        Collections.shuffle(list);
        for (int i=0; i<5; i++) {
            Qindex[i] = list.get(i);
        }

        //get from Question
        for (int i=0; i<5;i++){
            QItems = quizQ.this.getResources().getStringArray(quizQ.this.getResources()
                    .getIdentifier(whichcontent[Qindex[i]], "array", quizQ.this.getPackageName()));
            Question[i] = QItems;
            ModelQuestion.add(new model(this,i,Question[i][index],Question[i][index+1],Question[i][index+2]
                   ,Question[i][index+3],Question[i][index+4],Question[i][index+5]));
//            modelQuestion[i] = new model(this,i,Question[i][index],Question[i][index+1],Question[i][index+2]
//                    ,Question[i][index+3],Question[i][index+4],Question[i][index+5]);
            currectanswer[i] = Question[i][index+5];
            onlyQuestion[i] = Question[i][index];
        }

        //_________________________view pager____________________//
        quizadapter = new QuizAdapter(this,ModelQuestion);
        viewPager = findViewById(R.id.quizpager);
        viewPager.setAdapter(quizadapter);
        viewPager.addOnPageChangeListener(PageListener);
        //_________________________view pager____________________//
        //DetailOnPageChangeListener detailOnPageChangeListener=new DetailOnPageChangeListener();
        //Log.d("Nooooooooooooooooooo", detailOnPageChangeListener.allpage());
        //ansrPos[detailOnPageChangeListener.getCurrentPage()]=quizadapter.getOpsSel();

        //_______________________CarouselView_____________________________________//
//        carousel.add((modelQuestion[0]));
//        carousel.add((modelQuestion[1]));
//        carousel.add((modelQuestion[2]));
//        carousel.add((modelQuestion[3]));
//        carousel.add((modelQuestion[4]));
        //_______________________CarouselView_____________________________________//



        //next button
        nextbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //viewPager.setCurrentItem(pagecount+1);
                boolean YouCantRun = false;
                int slidenum;

                if (pagecount < 4) {
                    //carouselview.setCurrentPosition(slidcount + 1);
                    //----------------------------
                    String [] arr=quizadapter.getOption();
                    if (quizadapter.getSSelected() != null){
                        Log.d("saved option Arrays", "if nem 1");
                        if (pagecount != -1){
                            Log.d("getOpsSel", String.valueOf(quizadapter.getOpsSel()));
                            if (quizadapter.getOpsSel() != 0 && arr[pagecount].equals(quizadapter.getSSelected())){
                                Log.d("saved option Arrays", "if nem 3");
                                ansrPos[pagecount]=quizadapter.getOpsSel();}}
                        quizadapter.Button(ansrPos,pagecount);}
                    //-----------------------------
                    viewPager.setCurrentItem(pagecount + 1);
                    slidenum = pagecount;
                    if (quizadapter.getOption()[slidenum] != null) {
                        mImage.set(slidenum, R.drawable.tick_mark);
                        initRecyclerView();
                    } else {
                        mImage.set(slidenum, R.drawable.wrong_mark);
                        initRecyclerView();
                    }
                    //slidcount = slidcount + 1;
                }
                        if (slidcount == 4 || pagecount == 4){
                            nextbtn.setVisibility(View.VISIBLE);
                            nextbtn.setText(getText(R.string.finishBtn));
                            Intent n = new Intent(quizQ.this, QuizResult.class);
                            for (int i=0; i<quizadapter.getOption().length;i++){
                                if(quizadapter.getOption()[i] == null){
                                    YouCantRun = true;
                                    nextbtn.setEnabled(true);
                                    Toast.makeText(quizQ.this, getText(R.string.Finishq), Toast.LENGTH_LONG).show();
                                    break;
//                            getApplicationContext()
                                }
                            }
                            if (YouCantRun == false) {
                                Option = quizadapter.getOption();
                                bundle.putStringArray("option", Option);
                                bundle.putStringArray("content", onlyQuestion);
                                bundle.putStringArray("answer", currectanswer);
                                Score = score(Option,currectanswer);
                                bundle.putDouble("score",Score);
                                n.putExtras(bundle);
                                startActivity(n);
                                finish();
                            }
                        }

                    if (pagecount == 1) {
                        prebtn.setEnabled(true);
                        prebtn.setVisibility(View.VISIBLE);
                        prebtn.setText(getText(R.string.backBtn));
                    }
            }
        });


        //back button
        prebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //----------------------------
                String [] arr=quizadapter.getOption();
                if (quizadapter.getSSelected() != null){
                    Log.d("saved option Arrays", "if nem 1");
                    if (pagecount != -1){
                        Log.d("saved option Arrays", String.valueOf(quizadapter.getOpsSel()));
                        if (quizadapter.getOpsSel() != 0 && arr[pagecount].equals(quizadapter.getSSelected())){
                            Log.d("saved option Arrays", "if nem 3");
                            ansrPos[pagecount]=quizadapter.getOpsSel();}}
                    quizadapter.Button(ansrPos,pagecount);
                    //quizadapter.makeItZero();
                    }
                //-----------------------------
                //viewPager.setCurrentItem(pagecount-1);
                int slidenum =0;
                Log.d("in back button slid count", String.valueOf(slidcount));
                if (pagecount == 4){
                    slidenum = pagecount;
                    if (quizadapter.getOption()[slidenum] != null){
                        mImage.set(slidenum, R.drawable.tick_mark);
                        initRecyclerView();
                    }else{
                        mImage.set(slidenum, R.drawable.wrong_mark);
                        initRecyclerView();
                    }
                }
                viewPager.setCurrentItem(pagecount-1);
                //carouselview.setCurrentPosition(slidcount - 1);
                //slidcount = slidcount - 1;
                if (pagecount == 0){
                    prebtn.setEnabled(false);
                    prebtn.setVisibility(View.INVISIBLE);
                    prebtn.setText("");
                }
                if (pagecount-1 < 4){
                    nextbtn.setText(getText(R.string.nextBtn));
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent n = new Intent(quizQ.this, Chapters.class);
        n.putExtra("segmentId",0) ;
        startActivity(n);
        finish();
    }

//    View.OnClickListener pauseOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            //carouselview.pauseCarousel();
//            carouselview.pauseAutoScroll();
//        }
//    };

    //recycler view progress bar
    private void recyclerimage(){

        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);

        initRecyclerView();

    }

    public void initRecyclerView(){

        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,  mImage);
        recyclerView.setAdapter(adapter);

    }

    //calculate the score
    public double score (String [] soption, String[] correctoption){
        double score =0;
        int count =0;
        int subscore = 0;
        if (Arrays.equals(soption, correctoption)){
            score = 100;
            helper.setScore(name,QuizID,score);
            student.SetTotalScore(QuizID,score);
            return score;
        }else {
            for(int i=0; i < soption.length; i++){

                if (soption[i].equals(correctoption[i])){
                    count++;
                }
            }
                int totalq= soption.length;
                int wrongans =0;
                wrongans = totalq - count;
                subscore = totalq - wrongans;
                score = subscore * 20;
                //14.2857143
        }

        helper.setScore(name,QuizID,score);
        student.SetTotalScore(QuizID,score);
        return score;
    }
    //hello

    //Questions array
    public String [] AllQuestion = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5","C1Quiz_Q6","C1Quiz_Q7","C1Quiz_Q8","C1Quiz_Q9","C1Quiz_Q10",
            "C2Quiz_Q1","C2Quiz_Q2","C2Quiz_Q3","C2Quiz_Q4","C2Quiz_Q5",
            "C3Quiz_Q1","C3Quiz_Q2","C3Quiz_Q3","C3Quiz_Q4","C3Quiz_Q5","C3Quiz_Q6","C3Quiz_Q7","C3Quiz_Q8","C3Quiz_Q9","C3Quiz_Q10",
            "C4Quiz_Q1","C4Quiz_Q2","C4Quiz_Q3","C4Quiz_Q4","C4Quiz_Q5","C4Quiz_Q6","C4Quiz_Q7","C4Quiz_Q8","C4Quiz_Q9","C4Quiz_Q10","C4Quiz_Q11","C4Quiz_Q12","C4Quiz_Q13","C4Quiz_Q14","C4Quiz_Q15",
            "C5Quiz_Q1","C5Quiz_Q2","C5Quiz_Q3","C5Quiz_Q4","C5Quiz_Q5","C5Quiz_Q6","C5Quiz_Q7","C5Quiz_Q8","C5Quiz_Q9","C5Quiz_Q10"
    };
    public String[] C1Quiz = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5","C1Quiz_Q6","C1Quiz_Q7","C1Quiz_Q8","C1Quiz_Q9","C1Quiz_Q10"
    };
    public String[] C2Quiz = {
            "C2Quiz_Q1","C2Quiz_Q2","C2Quiz_Q3","C2Quiz_Q4","C2Quiz_Q5","C2Quiz_Q6","C2Quiz_Q7","C2Quiz_Q8","C2Quiz_Q9","C2Quiz_Q10"
    };
    public String[] C3Quiz = {
            "C3Quiz_Q1","C3Quiz_Q2","C3Quiz_Q3","C3Quiz_Q4","C3Quiz_Q5","C3Quiz_Q6","C3Quiz_Q7","C3Quiz_Q8","C3Quiz_Q9","C3Quiz_Q10"
    };
    public String[] C4Quiz = {
            "C4Quiz_Q1","C4Quiz_Q2","C4Quiz_Q3","C4Quiz_Q4","C4Quiz_Q5","C4Quiz_Q6","C4Quiz_Q7","C4Quiz_Q8","C4Quiz_Q9","C4Quiz_Q10","C4Quiz_Q11","C4Quiz_Q12","C4Quiz_Q13","C4Quiz_Q14","C4Quiz_Q15"
    };
    public String[] C5Quiz = {
            "C5Quiz_Q1","C5Quiz_Q2","C5Quiz_Q3","C5Quiz_Q4","C5Quiz_Q5","C5Quiz_Q6","C5Quiz_Q7","C5Quiz_Q8","C5Quiz_Q9","C5Quiz_Q10"
    };

    ViewPager.OnPageChangeListener PageListener = new ViewPager.OnPageChangeListener() {
        int postionNow;
        int lastop;
        boolean scrollState = false;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            postionNow=position;
        }

        @Override
        public void onPageSelected(int position) {

            pagecount = position;
            boolean positioneqfive = false;

            if (position == 0) {
                nextbtn.setEnabled(false);
                prebtn.setEnabled(false);
                prebtn.setVisibility(View.INVISIBLE);
                nextbtn.setText(getText(R.string.nextBtn));
                prebtn.setText("");


            } else if (position == 4) {
                nextbtn.setEnabled(true);
                prebtn.setEnabled(false);
                prebtn.setVisibility(View.INVISIBLE);
                nextbtn.setVisibility(View.VISIBLE);
                nextbtn.setText(getText(R.string.finishBtn));
                prebtn.setText("");

            } else {
                nextbtn.setEnabled(false);
                prebtn.setEnabled(false);
                prebtn.setVisibility(View.INVISIBLE);
                nextbtn.setVisibility(View.INVISIBLE);
                nextbtn.setText(getText(R.string.nextBtn));
                prebtn.setText(getText(R.string.backBtn));
            }
            if(position != 0 ) {
                int count = position - 1;
                if (quizadapter.getOption()[count] !=null){
                    mImage.set(count, R.drawable.tick_mark);
                    initRecyclerView();
                   // quizadapter.getSelected().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                }
                else {
                    mImage.set(count, R.drawable.wrong_mark);
                    initRecyclerView();
                   // quizadapter.getSelected().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                }
                if (position == 4){
                    positioneqfive = true;
                }
            }

            if (position != 4 && positioneqfive){

                if (quizadapter.getOption()[4] !=null){
                    mImage.set(4, R.drawable.tick_mark);
                    initRecyclerView();
                }
                else {
                    mImage.set(4, R.drawable.wrong_mark);
                    initRecyclerView();
                }

            }

            //quizadapter.getQuestion().setTextColor(ColorStateList.valueOf(Color.parseColor("#8847a7")));
//            if (quizadapter.getSelected() != null){
//                quizadapter.getSelected().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                quizadapter.getSelected().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//            }
            String [] arr=quizadapter.getOption();
//            quizadapter.getSSelected() != null
            //int [] sledPos= new int[5];

           // quizadapter.makeItZero();
            if (quizadapter.getSSelected() != null){
                if (position-1 != -1){
                if (quizadapter.getOpsSel() != 0 && arr[position-1].equals(quizadapter.getSSelected())){
                ansrPos[position-1]=quizadapter.getOpsSel();}}

               // if (ansrPos[position] == 0 && arr[position] != null ){
//                if (quizadapter.getOpsSel() != 0){
//                    ansrPos[position]=quizadapter.getOpsSel();}
                ansrPos[4]=lastop;
                quizadapter.Button(ansrPos,position);
                //quizadapter.Button(quizadapter.getOpsSel(),position);
               // quizadapter.myViewHolder.Button(quizadapter.getOpsSel(),quizadapter.getSSelected());
                //quizadapter.getSelected().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                //quizadapter.getSelected().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (postionNow==4){
                    scrollState=true;
                }
                if(scrollState && postionNow == 3 && quizadapter.getOpsSel() != 0){
                    lastop=quizadapter.getOpsSel();
                    scrollState=false;
                }
            }
        }

    };

}