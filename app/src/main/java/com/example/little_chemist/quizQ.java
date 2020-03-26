package com.example.little_chemist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Tables.Quiz;
import com.example.little_chemist.Tables.Student;
import com.example.little_chemist.kotlin.Intrinsics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import alirezat775.lib.carouselview.Carousel;
import alirezat775.lib.carouselview.CarouselListener;
import alirezat775.lib.carouselview.CarouselModel;
import alirezat775.lib.carouselview.CarouselView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class quizQ extends AppCompatActivity {

    //private Boolean hasNextPage = true;
    private static final String TAG = quizQ.class.getSimpleName();

    public ArrayList<Integer> mImage = new ArrayList<Integer>();
    private Button nextbtn, prebtn;
    private int slidcount = 0;
    private model[] modelQuestion = new model[5];
    private String[] currectanswer = new String[5];
    private String[] Option = new String[5];
    private String[][] Question = new String[5][];
    private double Score =0;
    private String[] onlyQuestion = new String[5];
    private int slidchange =0;
    private boolean inlastslid = false;
    //private String[] whichcontent = new String[5];

    int QuizID;
    Student student;
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
        String name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);

        //toolbar stuff
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(quizQ.this, Ch1.class);
                startActivity(Homepage);
                finish();
            }
        });

        Bundle bundle=getIntent().getExtras();
        QuizID = bundle.getInt("ChapterNumber");

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


//CarouselView


        final adapter adapter = new adapter(this,QuizID);
        AppCompatActivity appcomp = this;
        CarouselView carouselview = findViewById(R.id.carousel_view1);
        final Carousel carousel = new Carousel(appcomp,carouselview,adapter);

        carousel.setOrientation(carouselview.HORIZONTAL, false, true);
        carousel.autoScroll(false, 1000000000, false);
        carousel.scaleView(true);

        adapter.setOnClickListener((com.example.little_chemist.adapter.OnClick)(new com.example.little_chemist.adapter.OnClick() {
            public void click(@NonNull model model) {
                Intrinsics.checkParameterIsNotNull(model, "model");
                carousel.remove((CarouselModel)model);
            }
        }));
//        carousel.scrollSpeed(100f)
       // carousel.enableSlider(true);

        nextbtn = findViewById(R.id.nextBtn);
        prebtn = findViewById(R.id.preBtn);

        carousel.addCarouselListener((CarouselListener)(new CarouselListener() {
            boolean positioneqfive = false;
            public void onPositionChange(int position) {
                slidchange = position;
                if (position == 0){
                    nextbtn.setEnabled(true);
                    prebtn.setEnabled(false);
                    prebtn.setVisibility(View.INVISIBLE);
                    prebtn.setText("");
                }
                else if (position == 4){
                    inlastslid = true;
                    nextbtn.setEnabled(true);
                    prebtn.setEnabled(true);
                    nextbtn.setVisibility(View.VISIBLE);
                    nextbtn.setText(getText(R.string.finishBtn));

                } else {
                    nextbtn.setEnabled(true);
                    prebtn.setEnabled(true);
                    prebtn.setVisibility(View.VISIBLE);
                    nextbtn.setText(getText(R.string.nextBtn));
                    prebtn.setText(getText(R.string.backBtn));
                }
                Log.d(quizQ.this.getTAG(), "currentPosition : " + position);


                if(position != 0 ) {
                    int count = position - 1;
                    if (adapter.getOption()[count] !=null){
                    mImage.set(count, R.drawable.tick_mark);
                    initRecyclerView();
                    }
                    else {
                        mImage.set(count, R.drawable.wrong_mark);
                        initRecyclerView();
                    }
                    if (position == 4){
                        positioneqfive = true;
                    }
                }

                if (position != 4 && positioneqfive){

                    if (adapter.getOption()[4] !=null){
                        mImage.set(4, R.drawable.tick_mark);
                        initRecyclerView();
                    }
                    else {
                        mImage.set(4, R.drawable.wrong_mark);
                        initRecyclerView();
                    }

                }

            }

            public void onScroll(int dx, int dy) {
                carousel.pauseAutoScroll();
                Log.d(quizQ.this.getTAG(), "onScroll dx : " + dx + " -- dy : " + dx);
            }
        }));

//        carousel.add(EmptySampleModel("empty list"))

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

        //random
        int[] Qindex = new int [5];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<whichcontent.length; i++) {
            list.add(new Integer(i));
        }

        Collections.shuffle(list);
        for (int i=0; i<5; i++) {
            Qindex[i] = list.get(i);
        }

        //get from Question string
        for (int i=0; i<5;i++){
            QItems = adapter.context.getResources().getStringArray(adapter.context.getResources().getIdentifier(whichcontent[Qindex[i]], "array", adapter.context.getPackageName()));
            Question[i] = QItems;
            modelQuestion[i] = new model(this,i,Question[i][index],Question[i][index+1],Question[i][index+2],Question[i][index+3],Question[i][index+4],Question[i][index+5]);
            currectanswer[i] = Question[i][index+5];
            onlyQuestion[i] = Question[i][index];
        }

        carousel.add((modelQuestion[0]));
        carousel.add((modelQuestion[1]));
        carousel.add((modelQuestion[2]));
        carousel.add((modelQuestion[3]));
        carousel.add((modelQuestion[4]));



        //next button
        nextbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                boolean YouCantRun = false;
                if (slidcount != 4) {
                    if (slidcount < slidchange){
                        slidcount = slidchange;
                        carousel.setCurrentPosition(slidcount+1);
                        slidcount = slidcount +1;
                        int slidenum = slidcount-1;
                        if (adapter.getOption()[slidenum] != null){
                            mImage.set(slidenum, R.drawable.tick_mark);
                            initRecyclerView();
                        }else{
                            mImage.set(slidenum, R.drawable.wrong_mark);
                            initRecyclerView();
                        }
                    }else {
                        carousel.setCurrentPosition(slidcount+1);
                        slidcount = slidcount +1;
                        int slidenum = slidcount-1;
                        if (adapter.getOption()[slidenum] != null){
                            mImage.set(slidenum, R.drawable.tick_mark);
                            initRecyclerView();
                        }else{
                            mImage.set(slidenum, R.drawable.wrong_mark);
                            initRecyclerView(); }
                        if (slidcount == 4){
                            nextbtn.setVisibility(View.VISIBLE);
                            nextbtn.setText(getText(R.string.finishBtn));
                            Intent n = new Intent(quizQ.this, QuizResult.class);
                            for (int i=0; i<adapter.getOption().length;i++){
                                if(adapter.getOption()[i] == null){
                                    YouCantRun = true;
                                    nextbtn.setEnabled(true);
                                    inlastslid = true;
                                    Toast.makeText(quizQ.this, "Sweetie finish all the questions!", Toast.LENGTH_LONG).show();
                                    break;
//                            getApplicationContext()
                                }
                            }
                            if (YouCantRun == false) {
                                Option = adapter.getOption();
                                bundle.putStringArray("option", Option);
                                bundle.putStringArray("content", onlyQuestion);
                                bundle.putStringArray("answer", currectanswer);
                                n.putExtras(bundle);
                                Score = score(Option,currectanswer);
                                startActivity(n);
                            }
                        }
                    }
                    if (slidcount > 0) {
                        prebtn.setEnabled(true);
                        prebtn.setVisibility(View.VISIBLE);
                        prebtn.setText(getText(R.string.backBtn));
                    }
                }

            }
        });


        //back button
        prebtn.setOnClickListener(new View.OnClickListener(){
            int slidenum =0;

            @Override
            public void onClick(View view) {
                if (inlastslid == true){
                    slidenum = slidcount-1;
                    if (adapter.getOption()[slidenum] != null){
                        mImage.set(slidenum+1, R.drawable.tick_mark);
                        initRecyclerView();
                    }else{
                        mImage.set(slidenum+1, R.drawable.wrong_mark);
                        initRecyclerView();
                    }
                }
                carousel.setCurrentPosition(slidcount-1);
                slidcount = slidcount -1;
                if (slidcount == 0){
                    prebtn.setEnabled(false);
                    prebtn.setVisibility(View.INVISIBLE);
                    prebtn.setText("");
                }
                if (slidcount < 4){
                    nextbtn.setText(getText(R.string.nextBtn));
                }
            }
        });



//        carousel.add((CarouselModel)(new model(6)));
//        carousel.add((CarouselModel)(new model(7)));
//        carousel.add((CarouselModel)(new model(8)));
//        carousel.add((CarouselModel)(new model(9)));
//        carousel.add((CarouselModel)(new model(10)));

    }

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
        student.SetTotalScore(QuizID,score); //TODO make sure this saves it to the database
        return score;
    }

    //Questions array
    public String [] AllQuestion = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5","C1Quiz_Q6","C1Quiz_Q7","C1Quiz_Q8","C1Quiz_Q9","C1Quiz_Q10",
            "C2Quiz_Q1","C2Quiz_Q2","C2Quiz_Q3","C2Quiz_Q4","C2Quiz_Q5",
            "C3Quiz_Q1","C3Quiz_Q2","C3Quiz_Q3","C3Quiz_Q4","C3Quiz_Q5","C3Quiz_Q6","C3Quiz_Q7","C3Quiz_Q8","C3Quiz_Q9","C3Quiz_Q10",
            "C4Quiz_Q1","C4Quiz_Q2","C4Quiz_Q3","C4Quiz_Q4","C4Quiz_Q5","C4Quiz_Q6","C4Quiz_Q7","C4Quiz_Q8","C4Quiz_Q9","C4Quiz_Q10","C4Quiz_Q11","C4Quiz_Q12","C4Quiz_Q13","C4Quiz_Q14","C4Quiz_Q15",
    };
    public String[] C1Quiz = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5","C1Quiz_Q6","C1Quiz_Q7","C1Quiz_Q8","C1Quiz_Q9","C1Quiz_Q10"
    };
    public String[] C2Quiz = {
            "C2Quiz_Q1","C2Quiz_Q2","C2Quiz_Q3","C2Quiz_Q4","C2Quiz_Q5"//,"C2Quiz_Q6","C2Quiz_Q7","C2Quiz_Q8","C2Quiz_Q9","C2Quiz_Q10"
    };
    public String[] C3Quiz = {
            "C3Quiz_Q1","C3Quiz_Q2","C3Quiz_Q3","C3Quiz_Q4","C3Quiz_Q5","C3Quiz_Q6","C3Quiz_Q7","C3Quiz_Q8","C3Quiz_Q9","C3Quiz_Q10"
    };
    public String[] C4Quiz = {
            "C4Quiz_Q1","C4Quiz_Q2","C4Quiz_Q3","C4Quiz_Q4","C4Quiz_Q5","C4Quiz_Q6","C4Quiz_Q7","C4Quiz_Q8","C4Quiz_Q9","C4Quiz_Q10","C4Quiz_Q11","C4Quiz_Q12","C4Quiz_Q13","C4Quiz_Q14","C4Quiz_Q15"
    };
    public String[] C5Quiz = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5"
    };

}