package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import java.lang.Object ;
import java.security.AccessController;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch2;
import com.example.little_chemist.Chapters_dir.Ch3;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.Chapters_dir.Ch5;


public class Chapters extends AppCompatActivity {

  private CardView buttonch1,buttonch2, buttonch3,buttonch4,buttonch5 ;

  private CardView  card1 ;
    private CardView  card2 ;
    private CardView  card3 ;
    private CardView  card4 ;
    private CardView  card5 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_chapters);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Chapters.this, Home.class);
                startActivity(Homepage);
                //finish();
            }
        });

        card1 = findViewById(R.id.cardviewch1);
        card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch1.class);
                startActivity(n);
               // finish();
            }
        });

        card2 = findViewById(R.id.cardviewch2);
        card2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Chapters.this, Ch2.class);
                startActivity(n);
               // finish();
            }
        });

        card3 = findViewById(R.id.cardviewch3);
        card3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch3.class);
                startActivity(n);
               // finish();
            }
        });

        card4 = findViewById(R.id.cardviewch4);
        card4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch4.class);
                startActivity(n);
              //  finish();
            }
        });

        card5 = findViewById(R.id.cardviewch5);
        card5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch5.class);
                startActivity(n);
               // finish();
            }
        });



            }

        }