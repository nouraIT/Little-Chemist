package com.example.little_chemist.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch2;
import com.example.little_chemist.Chapters_dir.Ch3;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.Chapters_dir.Ch5;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.Home;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;

import java.util.ArrayList;


public class QuizResult extends AppCompatActivity {

    private static final String TAG = QuizResult.class.getSimpleName();
    private String[] Currectanswer,option,Question;
    private ArrayList<ResultModel> resultModels = new ArrayList<ResultModel>();
    private ResultAdapter adapter;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private Double score;
    private TextView Score;
    private int pagecount = 0,QuizID;
    Button backbtn,nextBtn;
    Bundle b;

    String name,statue;
    Student student;
    private SharedPreferences pref;
    private DatabaseHelper helper = new DatabaseHelper(this);


    public QuizResult(){
        Intrinsics.checkExpressionValueIsNotNull(TAG, "QuizResult.class.getSimpleName()");
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_result);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);

        b=this.getIntent().getExtras();
        Currectanswer = b.getStringArray("answer");
        option = b.getStringArray("option");
        Question = b.getStringArray("content");
        score = b.getDouble("score");
        Log.d(TAG,"QuizResult onCreate");

        Bundle bundle=getIntent().getExtras();
        QuizID = bundle.getInt("ChapterNumber");

        ConstraintLayout Quizlayout = findViewById(R.id.resultlayout);
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

        //back to home
        ImageView home = findViewById(R.id.homeicon);
        home.setOnClickListener(new View.OnClickListener(){
        public void onClick(View view) {
                    Intent n = new Intent(QuizResult.this, Home.class);
                    startActivity(n);
        finish();}
        });

        //ProgressBar
        progressBar = findViewById(R.id.resultprogressbar);
        progressBar.setProgress(score.intValue());
        Score = findViewById(R.id.score);
        Score.setText(String.valueOf(score.intValue()));


        //view pager
        for (int i=0; i<Question.length; i++){
            if (!option[i].equals(Currectanswer[i])){
                resultModels.add(new ResultModel(Question[i],option[i],Currectanswer[i], R.drawable.wrong_mark));
            }
            else{
                resultModels.add(new ResultModel(Question[i],option[i],Currectanswer[i], R.drawable.tick_mark));
            }
        }

        nextBtn = findViewById(R.id.nextbtn) ;
        backbtn = findViewById(R.id.backbtn) ;


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(pagecount+1);
            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(pagecount-1);
            }
        });

        adapter = new ResultAdapter(this,resultModels);
        viewPager = findViewById(R.id.resultviewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(PageListener);
    }

    ViewPager.OnPageChangeListener PageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            pagecount = position;

            if (position == 0) {
                nextBtn.setEnabled(true);
                backbtn.setEnabled(false);
                backbtn.setVisibility(View.INVISIBLE);
                nextBtn.setText(getText(R.string.nextBtn));
                backbtn.setText("");

            } else if (position == 4) {
                nextBtn.setEnabled(true);
                backbtn.setEnabled(true);
                backbtn.setVisibility(View.VISIBLE);
                nextBtn.setText(getText(R.string.finishBtn));
                backbtn.setText(getText(R.string.backBtn));
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent n;
                        switch (QuizID){
                            case 1:
                                 n = new Intent(QuizResult.this, Ch1.class);
                                 break;
                            case 2:
                                 n = new Intent(QuizResult.this, Ch2.class);
                                 break;
                            case 3:
                                 n = new Intent(QuizResult.this, Ch3.class);
                                 break;
                            case 4:
                                 n = new Intent(QuizResult.this, Ch4.class);
                                 break;
                            case 5:
                                 n = new Intent(QuizResult.this, Ch5.class);
                                 break;
                            default: n = new Intent(QuizResult.this, Home.class);
                        }
                        helper.updateQuiz(name,b.getInt("ChapterNumber"),"completed");//,Integer.toString(lessonkey).charAt(0));
                        statue = student.getLsnLock(String.valueOf(b.getInt("ChapterNumber")));
//                        System.out.println(statue) ;
                        startActivity(n);
                        finish();
                    }
                });

            } else {
                nextBtn.setEnabled(true);
                backbtn.setEnabled(true);
                backbtn.setVisibility(View.VISIBLE);
                nextBtn.setText(getText(R.string.nextBtn));
                backbtn.setText(getText(R.string.backBtn));
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };
}
