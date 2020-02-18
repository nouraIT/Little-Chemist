package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.little_chemist.Tables.Student;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;


public class Home extends AppCompatActivity {

    private ImageView set, testScore;
    private CardView cv ;
    private CardView chapters;
    private  org.eazegraph.lib.charts.PieChart pie ;
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
        //Body of your click handler
//        Thread thread = new Thread(new Runnable(){
//            @Override
//            public void run(){
//
//            }
//        });
//        thread.start();


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
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){
                Intent n = new Intent(Home.this, Settings.class);
                //n.putExtra("student",student);
                startActivity(n);
                //finish();
                    }
                });
                thread.start();
            }
        });


        chapters.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){

                Intent n = new Intent(Home.this, Chapters.class);
                //n.putExtra("student",student);
                startActivity(n);
                //finish();
                    }
                });
                thread.start();
            }
        });

        pie = findViewById(R.id.piechart) ;
        pie.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){
                Intent n = new Intent(Home.this, Quiz_score.class);
                startActivity(n);
                    }
                });
                thread.start();
            }
        });

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch1Name), 15, Color.parseColor("#F5545C")));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch2Name), 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch3Name), 35, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch4Name), 9, Color.parseColor("#FED70E")));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch5Name), 9, Color.parseColor("#98db81")));


        mPieChart.startAnimation();



    }

}
