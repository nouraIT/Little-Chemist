package com.example.little_chemist.Chapters_dir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.little_chemist.Chapters;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Quiz;
import com.example.little_chemist.Tables.Student;
import com.example.little_chemist.View_lesson.lab;
import com.example.little_chemist.quizQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class Ch2 extends AppCompatActivity {
    private CardView lsn1, lsn2, lsn3, lsn4, lsn5,quiz;

    SharedPreferences pref;
    DatabaseHelper helper = new DatabaseHelper(Ch2.this);
    String statue;
    ImageView lockpad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ch2);


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
                Intent Homepage = new Intent(Ch2.this, Chapters.class);
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

//        lin = view.findViewById(R.id.lin1);
        //Lessons colors

        statue = student.getLsnLock("1");
        lockpad = findViewById(R.id.lockicon1);

        System.out.println(statue);

        if(statue.equals("unlocked")) {
            lsn1.setCardBackgroundColor(getResources().getColor((R.color.primaryYellow)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
//            lin.setBackgroundResource(R.drawable.unlocked_lsn);
        }
        else if(statue.equals("completed")) {
            lsn1.setCardBackgroundColor((R.drawable.completed_lsn));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
        }
        else {
            lsn1.setCardBackgroundColor(getResources().getColor((R.color.Locked)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.lock));

        }


        statue = student.getLsnLock("2");
        lockpad = findViewById(R.id.lockicon2);

        if(statue.equals("unlocked")) {
            lsn2.setCardBackgroundColor(getResources().getColor((R.color.primaryYellow)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));

        }
        else if(statue.equals("completed")) {
            lsn2.setCardBackgroundColor(getResources().getColor((R.color.Completed)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
        }
        else
            lsn2.setCardBackgroundColor(getResources().getColor((R.color.Locked)));


        statue = student.getLsnLock("3");
        lockpad = findViewById(R.id.lockicon3);

        if(statue.equals("unlocked")) {
            lsn3.setCardBackgroundColor(getResources().getColor((R.color.primaryYellow)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
        }
        else if(statue.equals("completed")) {
            lsn3.setCardBackgroundColor(getResources().getColor((R.color.Completed)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
        }
        else
            lsn3.setCardBackgroundColor(getResources().getColor((R.color.Locked)));



        statue = student.getLsnLock("4");
        lockpad = findViewById(R.id.lockicon4);

        if(statue.equals("unlocked")) {
            lsn4.setCardBackgroundColor(getResources().getColor((R.color.primaryYellow)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
        }
        else if(statue.equals("completed")) {
            lsn4.setCardBackgroundColor(getResources().getColor((R.color.Completed)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
        }
        else
            lsn4.setCardBackgroundColor(getResources().getColor((R.color.Locked)));


        statue = student.getLsnLock("5");
        lockpad = findViewById(R.id.lockicon5);

        if(statue.equals("unlocked")) {
            lsn5.setCardBackgroundColor(getResources().getColor((R.color.primaryYellow)));
            lockpad.setImageDrawable(getResources().getDrawable(R.drawable.padlock));
        }
        else if(statue.equals("completed"))
            lsn5.setCardBackgroundColor(getResources().getColor((R.color.Completed)));
        else
            lsn5.setCardBackgroundColor(getResources().getColor((R.color.Locked)));


        statue = student.getQzLock("1");

        System.out.println(statue);
        if(statue.equals("unlocked"))
            quiz.setCardBackgroundColor(getResources().getColor((R.color.logoLightRed)));
//                    R.drawable.unfinished_quiz);
        else
            quiz.setCardBackgroundColor(getResources().getColor((R.color.Completed)));





//lessons btns


        lsn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        lsn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                statue = student.getLsnLock("2");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch2.this, lab.class);
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

                    Intent n = new Intent(Ch2.this, lab.class);
                    startActivity(n);
                    //  finish();


                } else{

                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

                }
            }
        });

        lsn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("4");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch2.this, lab.class);
                    startActivity(n);
                    //  finish();


                } else{

                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

                }
            }
        });

        lsn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("5");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch2.this, lab.class);
                    startActivity(n);
                    //  finish();


                } else{

                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

                }
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                statue = student.getQzLock("1");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch2.this, quizQ.class);
                    n.putExtra("ChapterNumber",2);
                    startActivity(n);
                }
                else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

            }

        });
    }
}
