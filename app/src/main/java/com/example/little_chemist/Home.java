package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;


public class Home extends AppCompatActivity {

    private ImageView set, testScore;
    private CardView cv ;
    //private pl.droidsonroids.gif.GifImageView  g ;
    private CardView chapters;
    boolean arabicFlag;
    DatabaseHelper helper = new DatabaseHelper(Home.this);
    public static boolean alreadyRecreated = false;
    public static boolean AlreadyGreeted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);



        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        //SharedPreferences.Editor editor = pref.edit();

        //the users info
//        Student student = (Student) getIntent().getSerializableExtra("student");
//        editor.putString("username", student.GetUserName()); // Storing string
//        editor.putString("password", student.GetPassword()); // Storing string
        String name = pref.getString("username", null); // getting String
        //editor.commit();

        set = findViewById(R.id.settings);
        chapters = findViewById(R.id.cardviewchapters);

        //welcome
        if(!AlreadyGreeted) {
            String welcome = getString(R.string.welcome) + " " + name;
            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
            AlreadyGreeted = true;
        }


        //check the lang
        arabicFlag = helper.checkLang(name);

        if(arabicFlag){
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            if(!alreadyRecreated) {
                recreate();
                alreadyRecreated = true;
            }
        }else{
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            if(!alreadyRecreated) {
                recreate();
                alreadyRecreated = true;
            }
        }


        set.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent n = new Intent(Home.this, Settings.class);
                //n.putExtra("student",student);
                startActivity(n);
                //finish();
            }
        });


        chapters.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent n = new Intent(Home.this, Chapters.class);
                //n.putExtra("student",student);
                startActivity(n);

                //finish();
            }
        });

        testScore = findViewById(R.id.imageView);

//        g = findViewById(R.id.gif);
        testScore.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent n = new Intent(Home.this, Quiz_score.class);
                //n.putExtra("student",student);
                startActivity(n);
                finish();
            }
        });



    }




}
