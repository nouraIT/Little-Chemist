package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


//import me.itangqi.waveloadingview.WaveLoadingView;


public class Quiz_score extends AppCompatActivity {

    ProgressBar progressBar1 ;
    ProgressBar progressBar2 ;
    ProgressBar progressBar3 ;
    ProgressBar progressBar4 ;
    ProgressBar progressBar5 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);


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

         progressBar1 = (ProgressBar) findViewById(R.id.progressBar6);

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
        progressBar5 = (ProgressBar) findViewById(R.id.progressBar5);



        //progressBar1.setProgress(90);
       // progressBar2.setProgress(40);
       // progressBar5.setProgress(20);
      //  progressBar4.setProgress(15);
      //  progressBar3.setProgress(70);


    }


}
