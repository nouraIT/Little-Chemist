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
    public int ex ;
    public TextView Q;
    public Button[] a ;
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
        String temp = String.valueOf(exKey.charAt(5)) ;
        segmentN = Integer.parseInt(temp) ;
        temp = String.valueOf(exKey.charAt(7)) ;
        ex = Integer.parseInt(temp) ;

        String[] QItems = getResources().getStringArray(getResources().getIdentifier(exKey, "array", getPackageName()));
        int len = getResources().getStringArray(getResources().getIdentifier(exKey, "array", getPackageName())).length ;

        Q = findViewById(R.id.Q) ;
        a=new Button[4];
        Q.setText(QItems[0]);

        for(int i=1 ; i<5;i++){
            if(i>len-1){
                for(int j=i;j<5;j++){
                int re = getResources().getIdentifier("a"+j , "id", getPackageName()) ;
                a[j-1]=findViewById(re) ;
                a[j-1].setVisibility(View.INVISIBLE);}
                break;}
            int re = getResources().getIdentifier("a"+i , "id", getPackageName()) ;
            a[i-1]=findViewById(re) ;
            a[i-1].setText(QItems[i]);

        }

//        for (int i=len-2; i<4 ;i++){
//            System.out.println(i) ;
//            int re = getResources().getIdentifier("a"+i+1 , "id", getPackageName()) ;
//            a[i]=findViewById(re) ;
//            if(a[i]==null)
//                System.out.println("eeee") ;
//            a[i].setVisibility(View.INVISIBLE);}


        exNum = findViewById(R.id.exNum) ;
        exNum.setText("Exercise "+ex);


        String temp1 = exKey.substring(0,4);
        temp1=temp1+"A";
        System.out.println(temp1);
        String[] An =getResources().getStringArray(getResources().getIdentifier(temp1, "array", getPackageName()));
        String ExtractA = An[ex-1] ;
        for(int i=1 ;i<QItems.length;i++){
            if(QItems[i].equals(ExtractA)){
                Button b = a[i-1] ;
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      b.setBackgroundColor(Color.GREEN);
                    }
                });break;}}






    }


}

