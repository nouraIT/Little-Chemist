package com.example.little_chemist.Chapters_dir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.example.little_chemist.Chapters;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.LabLesson4;
import com.example.little_chemist.LabLesson5;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Lesson;
import com.example.little_chemist.Tables.Quiz;
import com.example.little_chemist.Tables.Student;
import com.example.little_chemist.View_lesson.Lessons;
import com.example.little_chemist.quizQ;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class Ch3 extends AppCompatActivity {
    private CardView lsn1, lsn2, lsn3, lsn4, lsn5,quiz ;

    SharedPreferences pref;
    DatabaseHelper helper = new DatabaseHelper(Ch3.this);
    String statue;
    ImageView lockpad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ch3);


        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name = pref.getString("username", null); // getting String
        Student student = helper.getStudent(name);


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

        lsn1 = findViewById(R.id.cardviewlLSN1);
        lsn2 = findViewById(R.id.cardviewlLSN2);
        lsn3 = findViewById(R.id.cardviewlLSN3);
        lsn4 = findViewById(R.id.cardviewlLSN4);
        lsn5 = findViewById(R.id.cardviewlLSN5);
        quiz = findViewById(R.id.quiz);


            // ======================== Lessons colors ========================

        int i ;
        CardView[] lsns = {lsn1,lsn2,lsn3,lsn4,lsn5};
        ImageView[] locks = {findViewById(R.id.lockicon1) , findViewById(R.id.lockicon2), findViewById(R.id.lockicon3), findViewById(R.id.lockicon4), findViewById(R.id.lockicon5)};

        for(i =0;i<5;i++){

            statue = student.getLsnLock(String.valueOf(i+1));
            if(statue.equals("unlocked")) {
                lsns[i].setCardBackgroundColor(getResources().getColor((R.color.primaryYellow)));
                locks[i].setImageDrawable(getResources().getDrawable(R.drawable.padlock));
//            lin.setBackgroundResource(R.drawable.unlocked_lsn);
            }
            else if(statue.equals("completed")) {
                lsns[i].setCardBackgroundColor(getResources().getColor((R.color.Completed)));
                locks[i].setImageDrawable(getResources().getDrawable(R.drawable.tick_mark));
            }
            else {
                lsns[i].setCardBackgroundColor(getResources().getColor((R.color.Locked)));
                locks[i].setImageDrawable(getResources().getDrawable(R.drawable.lock));

            }

        }


        statue = student.getQzLock("3");

        System.out.println(statue);
        if(statue.equals("unlocked"))
            quiz.setCardBackgroundColor(getResources().getColor((R.color.logoLightRed)));
        else
            quiz.setCardBackgroundColor(getResources().getColor((R.color.Completed)));



        // ======================== Lessons btns ========================

        lsn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Lessons.class);
                n.putExtra("lesson", 31);
                startActivity(n);
              //  finish();
            }
        });

        lsn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                statue = student.getLsnLock("2");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch3.this, Lessons.class);
                    n.putExtra("lesson", 32);
                    startActivity(n);
              //  finish();
                } else{

                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

                }
            }
        });

        lsn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("3");
                if (statue.equals("unlocked") || statue.equals("completed") ) {


                    Thread thread = new Thread(new Runnable() {

                        public void run() {
                            Intent n = new Intent(Ch3.this, LabLesson5.class);
                            startActivity(n);
                        }
                    });
                    thread.start();
                }else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

            }
        });

        lsn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("4");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Thread thread = new Thread(new Runnable() {

                        public void run() {
                            Intent n = new Intent(Ch3.this, LabLesson4.class);
                            startActivity(n);
                        }
                    });
                    thread.start();
              //  finish();
                }else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

            }
        });

        lsn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("5");
                if (statue.equals("unlocked") || statue.equals("completed") ) {
                    Intent n = new Intent(Ch3.this, Ch1.class);
                    startActivity(n);
                }else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getQzLock("1");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch3.this, quizQ.class);
                    n.putExtra("ChapterNumber",3);
                    startActivity(n);
                }
                else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });
    }
}
