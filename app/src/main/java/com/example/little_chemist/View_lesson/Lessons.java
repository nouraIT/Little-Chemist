package com.example.little_chemist.View_lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    private Button nextBtn;
    private Button preBtn ;
    private int mCurrent ;
    private Button exercise ;

    LayoutInflater layoutInflater;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lessons);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent Homepage = new Intent(Lessons.this, getClass()); TODO get the name of the prevoius class
//                startActivity(Homepage);
//                //finish();
//            }
//        });

        Context con = Lessons.this;

        layoutInflater = (LayoutInflater) con.getSystemService(Chapters.LAYOUT_INFLATER_SERVICE) ;
        View view = layoutInflater.inflate(R.layout.chapters_slider,null,false) ;
        exercise = view.findViewById(R.id.ex) ;


        mSlidsView =  findViewById(R.id.slidePage) ;
        mDots = findViewById(R.id.dots) ;


        slideAdapter = new slideAdapter(this) ;

        mSlidsView.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mSlidsView.addOnPageChangeListener(viewListener);

        nextBtn = findViewById(R.id.nextBtn) ;
        preBtn =(Button) findViewById(R.id.preBtn) ;




        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("next btn");
                mSlidsView.setCurrentItem(mCurrent+1);
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("back btn");

                mSlidsView.setCurrentItem(mCurrent-1);

            }
        });

    }

    public void addDotsIndicator(int position ){

        mDotsText = new TextView[3] ;
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

    public void onBtnExClick(View v){

        if(v.getId()==R.id.ex) {
            System.out.println("yes");
            //Toast.makeText(getApplicationContext(), "The text you want to display", Toast.LENGTH_LONG);
        }else
            System.out.println("no");

//        Toast.makeText(getApplicationContext(),"Not working", Toast.LENGTH_LONG) ;



    }

}
