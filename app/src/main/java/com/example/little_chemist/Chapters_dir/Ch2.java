package com.example.little_chemist.Chapters_dir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import com.example.little_chemist.Chapters;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class Ch2 extends AppCompatActivity {
    private CardView lsn1, lsn2, lsn3, lsn4, lsn5,quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ch2);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Ch2.this, Chapters.class);
                startActivity(Homepage);
                finish();
            }
        });

        lsn1 = findViewById(R.id.cardviewlLSN1);
        lsn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        lsn2 = findViewById(R.id.cardviewlLSN2);
        lsn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Ch2.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        lsn3 = findViewById(R.id.cardviewlLSN3);
        lsn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, Ch1.class);
                startActivity(n);
               // finish();
            }
        });

        lsn4 = findViewById(R.id.cardviewlLSN4);
        lsn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        lsn5 = findViewById(R.id.cardviewlLSN5);
        lsn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });
        quiz = findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, Quiz.class);
                n.putExtra("ChapterNumber",2);
                startActivity(n);
                //  finish();
            }
        });
    }
}
