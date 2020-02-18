package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


//import me.itangqi.waveloadingview.WaveLoadingView;


public class Quiz_score extends AppCompatActivity {

    ProgressBar progressBar1,progressBar2, progressBar3 ,progressBar4 ,progressBar5 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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



        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("The study of atoms", 15, Color.parseColor("#F5545C")));
        mPieChart.addPieSlice(new PieModel("The Branches of chemistry", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Chemistry basics", 35, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Elements", 9, Color.parseColor("#FED70E")));
        mPieChart.addPieSlice(new PieModel("The periodic table", 9, Color.parseColor("#98db81")));


        mPieChart.startAnimation();


    }


}
