package com.example.little_chemist.Chapters_dir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.example.little_chemist.Chapters;
import com.example.little_chemist.Home;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Ch3 extends AppCompatActivity {
    private CardView button1 , button2 , button3 , button4 ,button5,quiz ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ch3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Ch3.this, Chapters.class);
                startActivity(Homepage);
                finish();
            }
        });

        button1 = findViewById(R.id.cardviewlLSN1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button2 = findViewById(R.id.cardviewlLSN2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button3 = findViewById(R.id.cardviewlLSN3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button4 = findViewById(R.id.cardviewlLSN4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button5 = findViewById(R.id.cardviewlLSN5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
            //    finish();
            }
        });
        quiz = findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Quiz.class);
                n.putExtra("ChapterNumber",3);
                startActivity(n);
                //  finish();
            }
        });
    }
}
