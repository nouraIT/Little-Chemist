package com.example.little_chemist.Chapters_dir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Ch2 extends AppCompatActivity {
    private CardView lsn1, lsn2, lsn3, lsn4, lsn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch2);


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
                n.putExtra("ChapterNumber",1);
                startActivity(n);
                //  finish();
            }
        });
    }
}
