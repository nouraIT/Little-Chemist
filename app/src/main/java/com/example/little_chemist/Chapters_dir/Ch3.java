package com.example.little_chemist.Chapters_dir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.example.little_chemist.Home;
import com.example.little_chemist.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ch3 extends AppCompatActivity {
    private CardView button1 , button2 , button3 , button4 ,button5 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3);


        button1 = findViewById(R.id.cardviewlLSN1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button2 = findViewById(R.id.cardviewlLSN2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button3 = findViewById(R.id.cardviewlLSN3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button4 = findViewById(R.id.cardviewlLSN4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
              //  finish();
            }
        });

        button5 = findViewById(R.id.cardviewlLSN5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch3.this, Ch1.class);
                startActivity(n);
            //    finish();
            }
        });
    }
}
