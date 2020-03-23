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
import java.util.LinkedList;

import alirezat775.lib.carouselview.Carousel;
import alirezat775.lib.carouselview.CarouselListener;
import alirezat775.lib.carouselview.CarouselModel;
import alirezat775.lib.carouselview.CarouselView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class quizQ extends AppCompatActivity {

    //private Boolean hasNextPage = true;
    private static final String TAG = quizQ.class.getSimpleName();

    private ArrayList<Integer> mImage = new ArrayList<Integer>();
    private Button nextbtn, prebtn;
    private int slidcount;
    private model[] modelQuestion = new model[5];
    private String[] currectanswer = new String[5];
    private String[] Option = new String[5];
    private String[][] Question = new String[5][];
    private double Score =0;
    private String[] onlyQuestion = new String[5];
    private String[] whichcontent = new String[5];

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


        // RecyclerView
        recyclerimage();


//CarouselView

        Bundle bundle=getIntent().getExtras();
        QuizID = bundle.getInt("ChapterNumber");
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
                slidcount = position;
                if (position == 0){
                    nextbtn.setEnabled(true);
                    prebtn.setEnabled(false);
                    prebtn.setVisibility(View.INVISIBLE);
                    prebtn.setText("");
                }
                else if (position == 4){
                    nextbtn.setEnabled(true);
                    prebtn.setEnabled(true);
                    nextbtn.setVisibility(View.VISIBLE);
                    nextbtn.setText(getText(R.string.finishBtn));

                } else {
                    nextbtn.setEnabled(true);
                    prebtn.setEnabled(true);
                    prebtn.setVisibility(View.VISIBLE);
                    prebtn.setText(getText(R.string.backBtn));
                }
                Log.d(quizQ.this.getTAG(), "currentPosition : " + position);


                if(position != 0 ) {
                    int count = position - 1;
                    mImage.set(count, R.drawable.tick_mark); //TODO should only happen if student answers
                    initRecyclerView();
                    if (position == 4){
                        positioneqfive = true;
                    }
                }

                if (position != 4 && positioneqfive){
                        mImage.set(4, R.drawable.tick_mark); //TODO should only happen if student answers
                        initRecyclerView();

                }

            }

            public void onScroll(int dx, int dy) {
                carousel.pauseAutoScroll();
                Log.d(quizQ.this.getTAG(), "onScroll dx : " + dx + " -- dy : " + dx);
            }
        }));

//        carousel.add(EmptySampleModel("empty list"))
        String[] QItems;
        int index =0;
        //String [] whichcontent = new String[C1Quiz.length];
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
        }

        for (int i=0; i<whichcontent.length;i++){
            QItems = adapter.context.getResources().getStringArray(adapter.context.getResources().getIdentifier(whichcontent[i], "array", adapter.context.getPackageName()));
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


        nextbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                carousel.setCurrentPosition(slidcount+1);
                if (slidcount == 4){
                    Intent n = new Intent(quizQ.this, QuizResult.class);
                    Option = adapter.getOption();
                    bundle.putStringArray("option", Option);
                    bundle.putStringArray("content", onlyQuestion);
                    bundle.putStringArray("answer", currectanswer);
                    n.putExtras(bundle);
                    Score = score(Option,currectanswer);
                    startActivity(n);
                }

            }
        });

        prebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                carousel.setCurrentPosition(slidcount-1);

            }
        });



//        carousel.add((CarouselModel)(new model(6)));
//        carousel.add((CarouselModel)(new model(7)));
//        carousel.add((CarouselModel)(new model(8)));
//        carousel.add((CarouselModel)(new model(9)));
//        carousel.add((CarouselModel)(new model(10)));

    }

    private void recyclerimage(){

        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);

        initRecyclerView();

    }

    private void initRecyclerView(){

        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,  mImage);
        recyclerView.setAdapter(adapter);

    }

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

    public String [] AllQuestion = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5","C2Quiz_Q1","C2Quiz_Q2","C2Quiz_Q3","C2Quiz_Q4","C2Quiz_Q5"
    };
    public String[] C1Quiz = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5"
    };
    public String[] C2Quiz = {
            "C2Quiz_Q1","C2Quiz_Q2","C2Quiz_Q3","C2Quiz_Q4","C2Quiz_Q5"
    };
    public String[] C3Quiz = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5"
    };
    public String[] C4Quiz = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5"
    };
    public String[] C5Quiz = {
            "C1Quiz_Q1","C1Quiz_Q2","C1Quiz_Q3","C1Quiz_Q4","C1Quiz_Q5"
    };


    public String [] getQuizcontent(int chnum){
        String [] Quizcontent = new String[5];
        int index =0 ;
        for (int i=0; i<AllQuestion.length;i++){
            if (chnum == AllQuestion[i].charAt(1)){
                Quizcontent[index] = AllQuestion[i];
                index++;
            }
        }
        return Quizcontent;
    }


}

