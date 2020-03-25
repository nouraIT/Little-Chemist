package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.little_chemist.Tables.Student;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Locale;

import info.hoang8f.widget.FButton;


public class Home extends AppCompatActivity {

    private ImageView set;
    FButton chapters;
    private  org.eazegraph.lib.charts.PieChart pie ;
    boolean arabicFlag;
    DatabaseHelper helper = new DatabaseHelper(Home.this);
    public static boolean alreadyRecreated = false , AlreadyGreeted = false;
    static Student student ;//= new Student();


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        // ========================= initializing  ============================================
        set = findViewById(R.id.settings);
        pie = findViewById(R.id.piechart) ;
        chapters = findViewById(R.id.homeBtn) ;
        chapters.setButtonColor(getResources().getColor(R.color.HomeButton));
        chapters.setShadowColor(getResources().getColor(R.color.gray));
        chapters.setShadowEnabled(true);
        chapters.setShadowHeight(7);
        chapters.setCornerRadius(30);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);
        // ========================= initializing  ============================================


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
        } else{
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


        // ========================= Buttons ============================================
        set.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){
                Intent n = new Intent(Home.this, Settings.class);
                n.putExtra("name","home");
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
                    }
                });
                thread.start();
            }
        });

        pie.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        Intent n = new Intent(Home.this, Quiz_score.class);
//                        n.putExtra("anim id in", R.anim.down_in);
//                        n.putExtra("anim id out", R.anim.down_out);
                        startActivity(n);
//                        overridePendingTransition(R.anim.up_in, R.anim.up_out);
                    }
                });
                thread.start();
            }
        });
        // ========================= Buttons ============================================


        // ========================= progress and chart  ============================================
        int progress = student.viewProgress();
        ProgressBar progressbar = findViewById(R.id.content_pro);
        progressbar.setProgress(progress,true);

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
        double[] scores = student.GetTotalScore();

        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch1Name), (float)scores[0], Color.parseColor("#ff0099cc") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch2Name), (float)scores[1], Color.parseColor("#ff99cc00") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch3Name), (float)scores[2], Color.parseColor("#F3CB4E") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch4Name), (float)scores[3], Color.parseColor("#E36F3A")  ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch5Name), (float) scores[4], Color.parseColor("#DF3241") ));

        mPieChart.startAnimation();
        // ========================= progress and chart  ============================================


    }

}
