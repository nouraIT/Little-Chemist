package com.example.little_chemist.View_lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.little_chemist.Chapters;
import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch2;
import com.example.little_chemist.Chapters_dir.Ch3;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.Chapters_dir.Ch5;
import com.example.little_chemist.Home;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Lesson;

public class Lessons extends AppCompatActivity {

    private ViewPager mSlidsView ;
    private LinearLayout mDots ;
    private TextView[] mDotsText ;
    private slideAdapter slideAdapter ;

    private int lessonkey ;
    private TextView lessonName ;
    private Button nextBtn;
    private Button preBtn ;
    private int mCurrent ;
    private Button exercise ;
    private Animation btnAmim ;
    LayoutInflater layoutInflater;
    private int NumDots ;
    private String IDEx ;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lessons);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Lessons.this, Ch1.class);
                startActivity(Homepage);
                finish();
            }
        });


        Context con = Lessons.this;

        layoutInflater = (LayoutInflater) con.getSystemService(Chapters.LAYOUT_INFLATER_SERVICE) ;
        View view = layoutInflater.inflate(R.layout.chapters_slider,null,false) ;

        exercise = view.findViewById(R.id.ex) ;
        mSlidsView =  findViewById(R.id.slidePage) ;
        mDots = findViewById(R.id.dots) ;

        Bundle bundle=getIntent().getExtras();
        lessonkey=bundle.getInt("lesson");
        slideAdapter = new slideAdapter(this, lessonkey) ;

        mSlidsView.setAdapter(slideAdapter) ;

        addDotsIndicator(0);

        mSlidsView.addOnPageChangeListener(viewListener);

        lessonName = findViewById(R.id.lessonTitle) ;

        lessonName.setText(getString(R.string.lesson1));
        setLessontitle(lessonkey);
        nextBtn = findViewById(R.id.nextBtn) ;
        preBtn = findViewById(R.id.preBtn) ;
        exercise = findViewById(R.id.ex) ;


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidsView.setCurrentItem(mCurrent+1);
            }
        });


        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlidsView.setCurrentItem(mCurrent-1);

            }
        });

     // btnAmim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_anim) ;

    }

    public void addDotsIndicator(int position ){

        mDotsText = new TextView[slideAdapter.getCount()] ;
        mDots.removeAllViews();

        for(int i=0;i<mDotsText.length;i++){
            mDotsText[i] = new TextView(this) ;
            mDotsText[i].setText(Html.fromHtml("&#8226"));
            mDotsText[i].setTextSize(35);
            mDotsText[i].setTextColor(getResources().getColor(R.color.TransparentWhite));

            mDots.addView(mDotsText[i]);

        }
        if (mDotsText.length>0){
            mDotsText[position].setTextColor(getResources().getColor(R.color.Black));
        }


    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            mCurrent = position ;

            if(position==0){
                nextBtn.setEnabled(true);
                preBtn.setEnabled(false);
                preBtn.setVisibility(View.INVISIBLE);

                nextBtn.setText(getText(R.string.nextBtn));
                preBtn.setText("");

            }else if (position == mDotsText.length - 1){
                nextBtn.setEnabled(true);
                preBtn.setEnabled(true);
                preBtn.setVisibility(View.VISIBLE);

                nextBtn.setText(getText(R.string.finishBtn));
                preBtn.setText(getText(R.string.backBtn));

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    Intent n ;
                    @Override
                    public void onClick(View v) {
                        if(Integer.toString(lessonkey).charAt(0)=='1')
                            n = new Intent(Lessons.this, Ch1.class);
                        if(Integer.toString(lessonkey).charAt(0)=='2')
                            n = new Intent(Lessons.this, Ch2.class);
                        if(Integer.toString(lessonkey).charAt(0)=='3')
                            n = new Intent(Lessons.this, Ch3.class);
                        if(Integer.toString(lessonkey).charAt(0)=='4')
                            n = new Intent(Lessons.this, Ch4.class);
                        if(Integer.toString(lessonkey).charAt(0)=='5')
                            n = new Intent(Lessons.this, Ch5.class);
                            startActivity(n);
                        finish();
                    }
                });

            }else{
                nextBtn.setEnabled(true);
                preBtn.setEnabled(true);
                preBtn.setVisibility(View.VISIBLE);

                nextBtn.setText(getText(R.string.nextBtn));
                preBtn.setText(getText(R.string.backBtn));
            }

        };

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void onBtnExClick(View v) {
        Intent n ;
        String ExID ;
        if (v.getId() == R.id.ex) {
//            System.out.println("yes");
//             n = new Intent(Lessons.this, ex_multiple_choice.class);
//             ExID=slideAdapter.getEx() ;
//             System.out.println(ExID);
//            n.putExtra("exKey",ExID) ;
//             startActivity(n);

        }

//        Toast.makeText(getApplicationContext(),"Not working", Toast.LENGTH_LONG) ;
    }




    public void loadButton(){
        exercise.setVisibility(View.VISIBLE);
        exercise.setAnimation(btnAmim) ;

    }
    //we should change it to string

    public void setLessontitle(int lessonKey ){

        if (lessonKey==11)
            lessonName.setText(R.string.Ch1Lsn1);
        if (lessonKey==12)
            lessonName.setText(R.string.Ch1Lsn2);
        if (lessonKey==13)
            lessonName.setText(R.string.Ch1Lsn3);
        if (lessonKey==14)
            lessonName.setText(R.string.Ch1Lsn4);
        if (lessonKey==15)
            lessonName.setText(R.string.Ch1Lsn5);
        if (lessonKey==21)
            lessonName.setText(R.string.Ch2Lsn1);
        if (lessonKey==22)
            lessonName.setText(R.string.Ch2Lsn2);
        if (lessonKey==23)
            lessonName.setText(R.string.Ch2Lsn3);
        if (lessonKey==24)
            lessonName.setText(R.string.Ch2Lsn4);
        if (lessonKey==25)
            lessonName.setText(R.string.Ch2Lsn5);
        if (lessonKey==31)
            lessonName.setText(R.string.Ch3Lsn1);
        if (lessonKey==32)
            lessonName.setText(R.string.Ch3Lsn2);
        if (lessonKey==33)
            lessonName.setText(R.string.Ch3Lsn3);
        if (lessonKey==34)
            lessonName.setText(R.string.Ch3Lsn4);
        if (lessonKey==35)
            lessonName.setText(R.string.Ch3Lsn5);
        if (lessonKey==41)
            lessonName.setText(R.string.Ch4Lsn1);
        if (lessonKey==42)
            lessonName.setText(R.string.Ch4Lsn2);
        if (lessonKey==43)
            lessonName.setText(R.string.Ch4Lsn3);
        if (lessonKey==44)
            lessonName.setText(R.string.Ch4Lsn4);
        if (lessonKey==45)
            lessonName.setText(R.string.Ch4Lsn5);
        if (lessonKey==51)
            lessonName.setText(R.string.Ch5Lsn1);
        if (lessonKey==52)
            lessonName.setText(R.string.Ch5Lsn2);
        if (lessonKey==53)
            lessonName.setText(R.string.Ch4Lsn3);
        if (lessonKey==54)
            lessonName.setText(R.string.Ch5Lsn4);
        if (lessonKey==55)
            lessonName.setText(R.string.Ch5Lsn5);


    }

}
