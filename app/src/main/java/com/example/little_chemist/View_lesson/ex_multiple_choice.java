package com.example.little_chemist.View_lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.little_chemist.Chapters;
import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.R;

public class ex_multiple_choice extends AppCompatActivity {



    public int segmentN ;
    public TextView Q;
    public Button a1 ;
    public Button a2 ;
    public Button a3 ;
    public Button a4 ;
    public TextView exNum ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_multiple_choice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(ex_multiple_choice.this, slideAdapter.class);
                startActivity(Homepage);
                finish();
            }
        });

        Bundle bundle=getIntent().getExtras();
        String exKey=bundle.getString("exKey");
        segmentN = Integer.parseInt(String.valueOf(exKey.charAt(5))) ;

        Q = findViewById(R.id.Q) ;
        a1 = findViewById(R.id.a1) ;
        a2 = findViewById(R.id.a2) ;
        a3 = findViewById(R.id.a3) ;
        a4 = findViewById(R.id.a4) ;

        String[] QItems = getResources().getStringArray(getResources().getIdentifier(exKey, "array", getPackageName()));
        Q.setText(QItems[0]);
        a1.setText(QItems[1]);
        a2.setText(QItems[2]);
        a3.setText(QItems[3]);
        a4.setText(QItems[4]);


        exNum = findViewById(R.id.exNum) ;
            exNum.setText("Exercise "+segmentN);

        String temp = exKey.substring(0,4);
        temp=temp+"A";
        System.out.println(temp);
        String[] An =getResources().getStringArray(getResources().getIdentifier(temp, "array", getPackageName()));
        String ExtractA = An[segmentN-1] ;
        for(int i=1 ;i<QItems.length;i++){
            if(QItems[i].equals(ExtractA)){
                if(i==1)
                a1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a1.setBackgroundColor(Color.GREEN);
                    }
                });

            if(i==2)
                a2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a2.setBackgroundColor(Color.GREEN);
                    }
                });
            if(i==3)
                a3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a3.setBackgroundColor(Color.GREEN);
                    }
                });
            if(i==4)
                a4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a4.setBackgroundColor(Color.GREEN);
                    }
                });break;}}






    }


}

