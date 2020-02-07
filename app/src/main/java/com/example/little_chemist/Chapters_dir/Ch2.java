package com.example.little_chemist.Chapters_dir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.little_chemist.Ch2_lessons.ch2_l1;
import com.example.little_chemist.Ch2_lessons.ch2_l2;
import com.example.little_chemist.Ch2_lessons.ch2_l3;
import com.example.little_chemist.Ch2_lessons.ch2_l4;
import com.example.little_chemist.Ch2_lessons.ch2_l5;
import com.example.little_chemist.Chapters;
import com.example.little_chemist.Home;
import com.example.little_chemist.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Ch2 extends AppCompatActivity {
    private Button button1 ;
    private Button button2 ;
    private Button button3 ;
    private Button button4 ;
    private Button button5 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch2);


        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, ch2_l1.class);
                startActivity(n);
                finish();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Ch2.this, ch2_l2.class);
                startActivity(n);
                finish();
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, ch2_l3.class);
                startActivity(n);
                finish();
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, ch2_l4.class);
                startActivity(n);
                finish();
            }
        });

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent n = new Intent(Ch2.this, ch2_l5.class);
                startActivity(n);
                finish();
            }
        });
    }
}
