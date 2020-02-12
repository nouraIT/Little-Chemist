package com.example.little_chemist.Chapters_dir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.little_chemist.Chapters;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class Ch1 extends AppCompatActivity {
    private CardView lsn1, lsn2, lsn3, lsn4, lsn5,quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Ch1.this, Chapters.class);
                startActivity(Homepage);
                finish();
            }
        });

//TODO fix the intent

        lsn1 = findViewById(R.id.cardviewlLSN1);
        lsn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch1.this, Ch1.class);
                startActivity(n);
               // finish();
            }
        });

        lsn2 = findViewById(R.id.cardviewA3);
        lsn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Ch1.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        lsn3 = findViewById(R.id.cardviewA1);
        lsn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch1.this, Ch1.class);
                startActivity(n);
               // finish();
            }
        });

        lsn4 = findViewById(R.id.cardviewA2);
        lsn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch1.this, Ch1.class);
                startActivity(n);
               // finish();
            }
        });

        lsn5 = findViewById(R.id.cardviewA4);
        lsn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch1.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });
        quiz = findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch1.this, Quiz.class);
                n.putExtra("ChapterNumber",1);
                startActivity(n);
                //  finish();
            }
        });

    }
}
