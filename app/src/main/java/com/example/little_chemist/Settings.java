package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

import android.os.Bundle;

import android.app.Activity;

import android.content.Intent;

import android.content.res.Configuration;

import android.content.res.Resources;

import android.util.DisplayMetrics;

import android.view.View;

import android.widget.AdapterView;

import android.widget.Button;



public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Button booklet = findViewById(R.id.button5);
        Button En = findViewById(R.id.button3);
        Button Ara = findViewById(R.id.button4);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Settings.this, Home.class);
                startActivity(Homepage);
                finish();
            }
        });
        En.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        Ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        booklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Direct to booklet
            }
        });
    }

}
