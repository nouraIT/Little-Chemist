package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch2;
import com.example.little_chemist.Chapters_dir.Ch3;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.Chapters_dir.Ch5;
import com.example.little_chemist.Tables.Student;


public class Chapters extends AppCompatActivity {

    private CardView  card00 ;
    private ViewPager mSlidsView ;
    private LinearLayout mCardShower ;
    private LinearLayout mDots ;
    private int mCurrent ;
    LayoutInflater layoutInflater;
    ConstraintLayout cl;

    private TextView[] mDotsText ;
    private com.example.little_chemist.chaptersAdapter adapter ;

    SharedPreferences pref;
    DatabaseHelper helper = new DatabaseHelper(Chapters.this);
    String statue;
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_chapters);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);


        Context con = Chapters.this;
        //ViewGroup container = (ViewGroup) new View(con);

        layoutInflater = (LayoutInflater) con.getSystemService(Chapters.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.chapters_slider, null, false);

        card00 = view.findViewById(R.id.cardviewch1);
//        cl = view.findViewById(R.id.clslider);
        mSlidsView = findViewById(R.id.chaptersSlidePage);
        mDots = findViewById(R.id.dots);
        mCardShower = findViewById(R.id.cardShower);



        //=============================================

        adapter = new chaptersAdapter(this);

        mSlidsView.setAdapter(adapter);
//        mSlidsView.setRotationY(540);

//        if(student.GetLang() == 1) {
////            mSlidsView.setRotationY(180);
//
//            mSlidsView.setRotationY(180);
//            cl.setRotationY(180);
//
//        }

        addDotsIndicator(0);

        mSlidsView.addOnPageChangeListener(viewListener);

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

    }//on create





    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            if(student.GetLang() == 1)
//                cl.setRotationY(180);

        }

        @Override
        public void onPageSelected(int position) {


            addDotsIndicator(position);
            mCurrent = position ;
//            System.out.println(position);
            switch(position){
                case 0:
                    card00.setId(R.id.cardviewch1);
                    //card0.setId(R.id.cardviewch1);
                    break;
                case 1:
                    card00.setId(R.id.cardviewch2);
//                    card0.setId(R.id.cardviewch2);
                    break;
                case 2:
                    card00.setId(R.id.cardviewch3);
//                    card0.setId(R.id.cardviewch3);
                    break;
                case 3:
                    card00.setId(R.id.cardviewch4);
//                    card0.setId(R.id.cardviewch4);
                    break;
                case 4:
                    card00.setId(R.id.cardviewch5);
//                    card0.setId(R.id.cardviewch5);
                    break;
            }


        };

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void addDotsIndicator(int position ){


        mDotsText = new TextView[5] ;
        mDots.removeAllViews();
//        mDots.setBackground(R.color.TransparentWhite);

        for(int i=0;i<mDotsText.length;i++){
            mDotsText[i] = new TextView(this) ;
            mDotsText[i].setText(Html.fromHtml("&#8226"));
            mDotsText[i].setTextSize(35);
            mDotsText[i].setTextColor(getResources().getColor(R.color.Locked));

            mDots.addView(mDotsText[i]);



        }
        if (mDotsText.length>0){
            mDotsText[position].setTextColor(getResources().getColor(R.color.Black));
        }
    }


    public void onBtnChapterClick(View v){

        Intent n ;
        //System.out.println(card00.getId()+" id is over here "+v.getId() +" and "+R.id.cardviewch1);


        if(R.id.cardviewch1==card00.getId()){
            n = new Intent(Chapters.this, Ch1.class);
            startActivity(n);

        }else if(R.id.cardviewch2==card00.getId()){
            statue = student.getChLock("2");
            if (statue.equals("unlocked") || statue.equals("completed") ) {
                n = new Intent(Chapters.this, Ch2.class);
                startActivity(n);
            }
            else
                Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

        }else if(R.id.cardviewch3==card00.getId()){
            statue = student.getChLock("3");
           // if (statue.equals("unlocked") || statue.equals("completed") ) {
                n = new Intent(Chapters.this, Ch3.class);
                startActivity(n);
           // }
           // else
                Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

        }else if(R.id.cardviewch4==card00.getId()){
            statue = student.getChLock("4");
            //if (statue.equals("unlocked") || statue.equals("completed") ) {
                n = new Intent(Chapters.this, Ch4.class);
                startActivity(n);
            //}else
                Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();


        }else if(R.id.cardviewch5==card00.getId()){
            statue = student.getChLock("5");
            if (statue.equals("unlocked") || statue.equals("completed") ) {
                n = new Intent(Chapters.this, Ch5.class);
                startActivity(n);
            }else
                Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();

        }
//        else{
//            n = new Intent(Chapters.this, Chapters.class);
//        }


    }

    public void dirc (View v){


    }

}



//
//card1 = findViewById(R.id.cardviewch1);
//        card1.setOnClickListener(new View.OnClickListener() {
//public void onClick(View view) {
//        Intent n = new Intent(Chapters.this, Ch1.class);
//        startActivity(n);
//        // finish();
//        }
//        });
//
//        card2 = findViewById(R.id.cardviewch2);
//        card2.setOnClickListener(new View.OnClickListener(){
//@Override
//public void onClick(View v) {
//        Intent n = new Intent(Chapters.this, Ch2.class);
//        startActivity(n);
//        // finish();
//        }
//        });
//
//        card3 = findViewById(R.id.cardviewch3);
//        card3.setOnClickListener(new View.OnClickListener() {
//public void onClick(View view) {
//        Intent n = new Intent(Chapters.this, Ch3.class);
//        startActivity(n);
//        // finish();
//        }
//        });
//
//        card4 = findViewById(R.id.cardviewch4);
//        card4.setOnClickListener(new View.OnClickListener() {
//public void onClick(View view) {
//        Intent n = new Intent(Chapters.this, Ch4.class);
//        startActivity(n);
//        //  finish();
//        }
//        });
//
//        card5 = findViewById(R.id.cardviewch5);
//        card5.setOnClickListener(new View.OnClickListener() {
//public void onClick(View view) {
//        Intent n = new Intent(Chapters.this, Ch5.class);
//        startActivity(n);
//        // finish();
//        }
//        });


