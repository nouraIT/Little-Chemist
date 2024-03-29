package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.little_chemist.Tables.Student;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


public class Quiz_score extends AppCompatActivity {

    NumberProgressBar progressBar1,progressBar2, progressBar3 ,progressBar4 ,progressBar5 ;
    static Student student = new Student();
    DatabaseHelper helper = new DatabaseHelper(Quiz_score.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_score);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Quiz_score.this, Home.class);
                startActivity(Homepage);
                finish();
            }
        });

       // PieChart mPieChart = findViewById(R.id.piechart);
        progressBar1 = findViewById(R.id.numberbar6);
        progressBar2 = findViewById(R.id.numberbar5);
        progressBar3 = findViewById(R.id.numberbar4);
        progressBar4 = findViewById(R.id.numberbar2);
        progressBar5 = findViewById(R.id.numberbar3);

        double[] scores = student.GetTotalScore();
        for(int i=0;i<5;i++){
            if(scores[i] == 0.0)
                scores[i]=1;
        }

//        mPieChart.addPieSlice(new PieModel(getString(R.string.ch5)+": "+getString(R.string.Ch5Name), (float) scores[4], Color.parseColor("#DF3241") ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.ch1)+": "+getString(R.string.Ch1Name), (float) scores[0], Color.parseColor("#ff0099cc") ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.ch2)+": "+getString(R.string.Ch2Name), (float) scores[1], Color.parseColor("#ff99cc00") ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.ch3)+": "+getString(R.string.Ch3Name), (float) scores[2], Color.parseColor("#F3CB4E") ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.ch4)+": "+getString(R.string.Ch4Name), (float) scores[3], Color.parseColor("#E36F3A")  ));

        progressBar1.setProgress((int) scores[0]);
        progressBar2.setProgress((int) scores[1]);
        progressBar3.setProgress((int) scores[2]);
        progressBar4.setProgress((int) scores[3]);
        progressBar5.setProgress((int) scores[4]);

       // mPieChart.startAnimation();


    }


}
