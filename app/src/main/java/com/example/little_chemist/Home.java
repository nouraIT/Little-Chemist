package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.little_chemist.Tables.Student;


public class Home extends AppCompatActivity {

    private ImageView set;
    private CardView cv ;
    //private pl.droidsonroids.gif.GifImageView  g ;
    private CardView chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //the users info
        Student student = (Student) getIntent().getSerializableExtra("student");
        set = findViewById(R.id.settings);
        chapters = findViewById(R.id.cardviewchapters);


        //Student student = new Student (getIntent().getStringExtra("UserName") , getIntent().getStringExtra("Password"));
        //static String name = UserNameStr;



        set.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent n = new Intent(Home.this, Settings.class);
                n.putExtra("student",student);
                startActivity(n);
                //finish();
            }
        });


        chapters.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent n = new Intent(Home.this, Chapters.class);
                n.putExtra("student",student);
                startActivity(n);

                //finish();
            }
        });

//        g = findViewById(R.id.gif);
//        g.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Intent n = new Intent(Home.this, Quiz_score.class);
//                n.putExtra("student",student);
//                startActivity(n);
//                finish();
//            }
//        });




    }

}
