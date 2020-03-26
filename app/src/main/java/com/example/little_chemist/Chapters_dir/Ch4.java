package com.example.little_chemist.Chapters_dir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.example.little_chemist.Chapters;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.LabLesson3;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Quiz;
import com.example.little_chemist.Tables.Student;
import com.example.little_chemist.View_lesson.Lessons;
import com.example.little_chemist.View_lesson.lab;
import com.example.little_chemist.quizQ;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class Ch4 extends AppCompatActivity {
    private CardView lsn1, lsn2, lsn3, lsn4, lsn5,quiz;

    SharedPreferences pref;
    DatabaseHelper helper = new DatabaseHelper(Ch4.this);
    String statue;
    ImageView lockpad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ch4);


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
                Intent Homepage = new Intent(Ch4.this, Chapters.class);
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
        ImageView[] lsnimgs ={findViewById(R.id.lsnimg1), findViewById(R.id.lsnimg2) , findViewById(R.id.lsnimg3) , findViewById(R.id.lsnimg4), findViewById(R.id.lsnimg5)};

        for(i =0;i<5;i++){

            statue = student.getLsnLock(String.valueOf(i+16));
            if(statue.equals("unlocked")) {
                lsns[i].setCardBackgroundColor(getResources().getColor((R.color.primaryYellow)));
                locks[i].setImageDrawable(getResources().getDrawable(R.drawable.padlock));
                lsnimgs[i].setAlpha((float) 0.5);
            }
            else if(statue.equals("completed")) {
                lsns[i].setCardBackgroundColor(getResources().getColor((R.color.Completed)));
                locks[i].setImageDrawable(getResources().getDrawable(R.drawable.star));
                lsnimgs[i].setAlpha((float)1);

            }
            else {
                lsns[i].setCardBackgroundColor(getResources().getColor((R.color.Locked)));
                locks[i].setImageDrawable(getResources().getDrawable(R.drawable.lock));
                lsnimgs[i].setAlpha((float) 0.5);

            }

        }


        statue = student.getQzLock("4");
        ImageView qzimg = findViewById(R.id.qzimg);


//        System.out.println(statue);
        if(statue.equals("unlocked")) {
            quiz.setCardBackgroundColor(getResources().getColor((R.color.logoLightRed)));
            qzimg.setAlpha((float) 0.5);

        }
        else {
            quiz.setCardBackgroundColor(getResources().getColor((R.color.Completed)));
            qzimg.setAlpha((float)1);
        }


        // ======================== Lessons btns ========================

        lsn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch4.this, Lessons.class);
                n.putExtra("lesson",41);
                n.putExtra("lessonId",16);
                startActivity(n);
                finish();
            }
        });

        lsn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                statue = student.getLsnLock("2");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch4.this, Lessons.class);
                n.putExtra("lesson",42);
                    n.putExtra("lessonId",17);

                    startActivity(n);
                } else{

                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

                }
            }
        });

        lsn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("3");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch4.this, Lessons.class);
                n.putExtra("lesson",43);
                    n.putExtra("lessonId",18);

                    startActivity(n);
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
                            Intent n = new Intent(Ch4.this, LabLesson3.class);
                            n.putExtra("lesson",44);
                            n.putExtra("lessonId",19);

                            startActivity(n);
                        }
                    });
                    thread.start();


                }else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });

        lsn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("5");
                if (statue.equals("unlocked") || statue.equals("completed") ) {
                    Intent n = new Intent(Ch4.this, Lessons.class);
                n.putExtra("lesson",45);
                    n.putExtra("lessonId",20);

                    startActivity(n);
                }else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getQzLock("1");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch4.this, quizQ.class);
                n.putExtra("lesson",47); //TODO u sure ?
                n.putExtra("ChapterNumber",4);
                startActivity(n);
                }
                else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });
    }
}
