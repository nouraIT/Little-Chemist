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
    static Student student = new Student();

//TODO *Add reset button to lab

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
        String name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);
        //SharedPreferences.Editor editor = pref.edit();

        //the users info
//        Student student = (Student) getIntent().getSerializableExtra("student");
//        editor.putString("username", student.GetUserName()); // Storing string
//        editor.putString("password", student.GetPassword()); // Storing string
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
//                        n.putExtra("anim id in", R.anim.down_in);
//                        n.putExtra("anim id out", R.anim.down_out);
                        startActivity(n);
//                        overridePendingTransition(R.anim.up_in, R.anim.up_out);
                    }
                });
                thread.start();
            }
        });

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);


        String progress = student.getProgress();
        //"1:0,2:0,3:0,4:0,5:0,"
        int []chvalue = new int[5];

//        System.out.println(progress);

        for(int i =0;i<5;i++){

            int startIndex = progress.indexOf("c"+String.valueOf(i+1));
            int endIndex = progress.indexOf(",",startIndex);

//            System.out.println(startIndex+" and "+endIndex);

            chvalue[i] = Integer.parseInt( progress.substring(startIndex + 3 , endIndex ));


            if(chvalue[i] == 0) {
                chvalue[i] = 1;
//                System.out.println(i+" "+chvalue[i]);

            }

        }


        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch1Name), chvalue[0], Color.parseColor("#ff0099cc") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch2Name), chvalue[1], Color.parseColor("#ff99cc00") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch3Name), chvalue[2], Color.parseColor("#F3CB4E") ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch4Name), chvalue[3], Color.parseColor("#E36F3A")  ));
        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch5Name), chvalue[4], Color.parseColor("#DF3241") ));

//        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch1Name), chvalue[0], (R.color.cpb_blue) ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch2Name), chvalue[1], (R.color.cpb_green) ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch3Name), chvalue[2], (R.color.primaryYellow) ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch4Name), chvalue[3], (R.color.OrangeText) ));
//        mPieChart.addPieSlice(new PieModel(getString(R.string.Ch5Name), chvalue[4], (R.color.cpb_red) ));


        mPieChart.startAnimation();



    }

}
