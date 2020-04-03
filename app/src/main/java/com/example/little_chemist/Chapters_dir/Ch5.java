package com.example.little_chemist.Chapters_dir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.example.little_chemist.ARCards;
import com.example.little_chemist.Chapters;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Quiz;
import com.example.little_chemist.Tables.Student;
import com.example.little_chemist.View_lesson.Lessons;
import com.example.little_chemist.quizQ;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class Ch5 extends AppCompatActivity {
    private CardView lsn1, lsn2, lsn3, lsn4, lsn5,quiz;

    SharedPreferences pref;
    DatabaseHelper helper = new DatabaseHelper(Ch5.this);
    String statue;
    ImageView lockpad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ch5);


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
                Intent n = new Intent(Ch5.this, Chapters.class);
                n.putExtra("segmentId",4) ;
                startActivity(n);
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

            statue = student.getLsnLock(String.valueOf(i+21));
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


        statue = student.getQzLock("5");
        ImageView qzimg = findViewById(R.id.qzimg);

//        System.out.println(statue);
        if(statue.equals("unlocked")) {
            quiz.setCardBackgroundColor(getResources().getColor((R.color.logoLightRed)));
            qzimg.setAlpha((float) 0.5);
        }
        else {
            quiz.setCardBackgroundColor(getResources().getColor((R.color.Completed)));
            qzimg.setAlpha((float) 1);
        }



            // ======================== Lessons btns ========================


        lsn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable(){

                    public void run(){

                        Intent n = new Intent(Ch5.this, Lessons.class);
                        n.putExtra("lesson",51);
                        n.putExtra("lessonId",21);
                        startActivity(n);

                    }
                });
                thread.start();
//                finish();
            }
        });

        lsn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                statue = student.getLsnLock("2");
                String s1,s2,s3,s4;
                s1= getString(R.string.prepare);
                s2=getString(R.string.hydrogen1);
                s3=getString(R.string.lithium1);
                s4=getString(R.string.sodium1);
                if (statue.equals("unlocked") || statue.equals("completed") ) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Ch5.this).create();
                    alertDialog.setTitle(getText(R.string.cards));
                    alertDialog.setMessage(s1 +"\n"+ s2 +"\n"+ s3 +"\n"+ s4);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getText(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Thread thread = new Thread(new Runnable() {

                                        public void run() {
                                            Intent n = new Intent(Ch5.this, ARCards.class);
                                            n.putExtra("lesson",52);
                                            n.putExtra("lessonId",22) ;
                                            startActivity(n);
                                        }
                                    });
                                    thread.start();
                                }
                            });
                    alertDialog.show();
                } else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });

        lsn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("3");
                String s1,s2,s3,s4;
                s1= getString(R.string.prepare);
               // s2=getString(R.string.barium1);
                s3=getString(R.string.calcium1);
                s4=getString(R.string.magnesium1);
                if (statue.equals("unlocked") || statue.equals("completed") ) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Ch5.this).create();
                    alertDialog.setTitle(getText(R.string.cards));
                    alertDialog.setMessage(s1 +"\n"+ s3 +"\n"+ s4);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getText(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Thread thread = new Thread(new Runnable() {

                                        public void run() {
                                            Intent n = new Intent(Ch5.this, ARCards.class);
                                            n.putExtra("lesson",53);
                                            n.putExtra("lessonId",23) ;
                                            startActivity(n);
                                        }
                                    });
                                    thread.start();
                                }
                            });
                    alertDialog.show();
                } else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });

        lsn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("4");
                String s1,s2,s3,s4;
                s1= getString(R.string.prepare);
                s2=getString(R.string.fluorine1);
                s3=getString(R.string.chlorine1);
                s4=getString(R.string.bromine1);
                if (statue.equals("unlocked") || statue.equals("completed") ) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Ch5.this).create();
                    alertDialog.setTitle(getText(R.string.cards));
                    alertDialog.setMessage(s1 +"\n"+ s2 +"\n"+ s3 +"\n"+ s4);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getText(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Thread thread = new Thread(new Runnable() {

                                        public void run() {
                                            Intent n = new Intent(Ch5.this, ARCards.class);
                                            n.putExtra("lesson",54);
                                            n.putExtra("lessonId",24) ;
                                            startActivity(n);
                                        }
                                    });
                                    thread.start();
                                }
                            });
                    alertDialog.show();
                } else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });

        lsn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getLsnLock("5");
                String s1,s2,s3,s4;
                s1= getString(R.string.prepare);
                s2=getString(R.string.argon1);
                s3=getString(R.string.helium1);
                s4=getString(R.string.neon1);
                if (statue.equals("unlocked") || statue.equals("completed") ) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Ch5.this).create();
                    alertDialog.setTitle(getText(R.string.cards));
                    alertDialog.setMessage(s1 +"\n"+ s2 +"\n"+ s3 +"\n"+ s4);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getText(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Thread thread = new Thread(new Runnable() {

                                        public void run() {
                                            Intent n = new Intent(Ch5.this, ARCards.class);
                                            n.putExtra("lesson",55);
                                            n.putExtra("lessonId",25) ;
                                            startActivity(n);
                                        }
                                    });
                                    thread.start();
                                }
                            });
                    alertDialog.show();
                } else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                statue = student.getQzLock("1");
                if (statue.equals("unlocked") || statue.equals("completed") ) {

                    Intent n = new Intent(Ch5.this, quizQ.class);
                n.putExtra("ChapterNumber",5);
                startActivity(n);
                }
                else
                    Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
            }
        });
    }
}
