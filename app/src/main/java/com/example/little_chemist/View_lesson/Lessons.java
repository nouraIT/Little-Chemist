package com.example.little_chemist.View_lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.little_chemist.R;

public class Lessons extends AppCompatActivity {

    private ViewPager mSlidsView ;
    private LinearLayout mDots ;
    private TextView[] mDotsText ;
    private slideAdapter slideAdapter ;

    private Button nextBtn;
    private Button preBtn ;
    private int mCurrent ;
    private Button exercise ;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);


        mSlidsView = (ViewPager) findViewById(R.id.slidePage) ;
        mDots = (LinearLayout) findViewById(R.id.dots) ;


        slideAdapter = new slideAdapter(this) ;

        mSlidsView.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mSlidsView.addOnPageChangeListener(viewListener);

        exercise = (Button)findViewById(R.id.ex) ;
        nextBtn = (Button)findViewById(R.id.nextBtn) ;
        preBtn =(Button) findViewById(R.id.preBtn) ;

//        exercise.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(),"The text you want to display", Toast.LENGTH_LONG) ;
//            }
//        });


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

                nextBtn.setText("Next");
                preBtn.setText("");

            }else if (position == mDotsText.length - 1){
                nextBtn.setEnabled(true);
                preBtn.setEnabled(true);
                preBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("Finish");
                preBtn.setText("Back");
            }else{
                nextBtn.setEnabled(true);
                preBtn.setEnabled(true);
                preBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("Next");
                preBtn.setText("Back");
            }

        };

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}
