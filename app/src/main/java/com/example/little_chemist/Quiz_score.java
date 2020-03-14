package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.little_chemist.Tables.Student;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


//import me.itangqi.waveloadingview.WaveLoadingView;


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
                // finish();
            }
        });



        PieChart mPieChart = findViewById(R.id.piechart);
        progressBar1 = findViewById(R.id.numberbar6);
        progressBar2 = findViewById(R.id.numberbar5);
        progressBar3 = findViewById(R.id.numberbar4);
        progressBar4 = findViewById(R.id.numberbar2);
        progressBar5 = findViewById(R.id.numberbar3);

        String progress = student.getProgress();
        //"1:0,2:0,3:0,4:0,5:0,"
        int []chvalue = new int[5];

//        System.out.println(progress);

        for(int i =0;i<5;i++){

            int startIndex = progress.indexOf("c"+String.valueOf(i+1));
            int endIndex = progress.indexOf(",",startIndex);

//            System.out.println(startIndex+" and "+endIndex);

            chvalue[i] = Integer.parseInt( progress.substring(startIndex + 3 , endIndex ));


            if(chvalue[i] == 0) {
                chvalue[i] = 1;
//                System.out.println(i+" "+chvalue[i]);

            }

        }


        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch1Name), chvalue[0], Color.parseColor("#ff0099cc") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch2Name), chvalue[1], Color.parseColor("#ff99cc00") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch3Name), chvalue[2], Color.parseColor("#F3CB4E") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch4Name), chvalue[3], Color.parseColor("#E36F3A")  ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch5Name), chvalue[4], Color.parseColor("#DF3241") ));

        progressBar1.setProgress(chvalue[0]);
        progressBar2.setProgress(chvalue[1]);
        progressBar3.setProgress(chvalue[2]);
        progressBar4.setProgress(chvalue[3]);
        progressBar5.setProgress(chvalue[4]);


        mPieChart.startAnimation();


    }


}
