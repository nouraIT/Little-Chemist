package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import java.lang.Object ;
import java.security.AccessController;

import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        buttonch1 = findViewById(R.id.cardviewch1);
        buttonch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch1.class);
                startActivity(n);
               // finish();
            }
        });

        buttonch2 = findViewById(R.id.cardviewch2);
        buttonch2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Chapters.this, Ch2.class);
                startActivity(n);
               // finish();
            }
        });

        buttonch3 = findViewById(R.id.cardviewch3);
        buttonch3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch3.class);
                startActivity(n);
               // finish();
            }
        });

        buttonch4 = findViewById(R.id.cardviewch4);
        buttonch4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch4.class);
                startActivity(n);
              //  finish();
            }
        });

        buttonch5 = findViewById(R.id.cardviewch5);
        buttonch5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Chapters.this, Ch5.class);
                startActivity(n);
               // finish();
            }
        });



            }

        }