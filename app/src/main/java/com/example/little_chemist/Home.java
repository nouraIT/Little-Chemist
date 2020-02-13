package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.little_chemist.Tables.Student;


public class Home extends AppCompatActivity {
private ImageView set;
    private Button button ;
    private Button buttonQuiz ;
    private CardView cv ;
    //private pl.droidsonroids.gif.GifImageView  g ;
    //private Button button ;
    private CardView chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //the users info
        Student student = (Student) getIntent().getSerializableExtra("student");

        //Student student = new Student (getIntent().getStringExtra("UserName") , getIntent().getStringExtra("Password"));

        //static String name = UserNameStr;


        set = findViewById(R.id.settings);
        set.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent n = new Intent(Home.this, Settings.class);
                n.putExtra("student",student);
                startActivity(n);
                //finish();
            }
        });

        chapters = findViewById(R.id.cardviewchapters);
       // cv.setOnClickListener();
        //button = findViewById(R.id.cardviewchapters);

        Button logout = findViewById(R.id.logoutBtn);

        chapters.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent n = new Intent(Home.this, Chapters.class);
                startActivity(n);

                //finish();
            }
        });

//                Intent intent = new Intent(Home.this,
//                        LoginPage.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();



//        g = findViewById(R.id.gif);
//        g.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Intent n = new Intent(Home.this, Quiz_score.class);
//                startActivity(n);
//                finish();
//            }
//        });


//        Button logout = findViewById(R.id.logoutBtn);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences myPrefs = getSharedPreferences("Activity",
//                        MODE_PRIVATE);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();
//                editor.commit();
//
//                Intent intent = new Intent(Home.this,
//                        LoginPage.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

}
