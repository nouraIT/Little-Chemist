package com.example.little_chemist.Chapters_dir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.Home;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;


public class Chapters extends AppCompatActivity {

    private CardView  card00 ;
    private ViewPager mSlidsView ;
    private LinearLayout mCardShower, mDots ;

    LayoutInflater layoutInflater;
    ConstraintLayout cl;
    private  Bundle bundle ;
    TextView chaptertxt ;
    public int chapterIndicator =0 ,mCurrent =0;

    private TextView[] mDotsText ;
    private chaptersAdapter adapter ;

    SharedPreferences pref;
    DatabaseHelper helper = new DatabaseHelper(Chapters.this);
    String statue;
    Student student;
    //public static boolean alreadyRecreated = false , AlreadyGreeted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chapters);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);

        //////////////////////////////////////////////////////////////////////

        if(isFirstTime()){
//            student.AlreadyGreeted = true;
                String s1, s2, s3, s4,s5;
                s1 = getString(R.string.instruction1);
                s2 = getString(R.string.instruction2);
                s3 = getString(R.string.instruction3);
                s4 = getString(R.string.instruction4);
                s5 = getString(R.string.instruction5);

                AlertDialog alertDialog = new AlertDialog.Builder(Chapters.this).create();
                alertDialog.setTitle(getText(R.string.welcome));
                alertDialog.setMessage( "\n"  +s1 + "\n"  + "\n" + s2 + "\n" + "\n" + s3+ "\n"  + "\n"+s4+ "\n"  + "\n"+ s5);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getText(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }

        /////////////////////////////////////////////////////////////////////




        Context con = Chapters.this;

        layoutInflater = (LayoutInflater) con.getSystemService(Chapters.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.chapters_slider, null, false);

        card00 = view.findViewById(R.id.cardviewch1);
        cl = view.findViewById(R.id.bigslider);
        mSlidsView = findViewById(R.id.chaptersSlidePage);
        mDots = findViewById(R.id.dots);
        mCardShower = findViewById(R.id.cardShower);

        //=============================================
        bundle=getIntent().getExtras();
        chapterIndicator =bundle.getInt("segmentId") ;
        adapter = new chaptersAdapter(this);
        mSlidsView.setAdapter(adapter);
        //mCurrent = segmentId ;
        addDotsIndicator(chapterIndicator);
        mSlidsView.setCurrentItem(chapterIndicator);
        switch(chapterIndicator){
            case 0:
                card00.setId(R.id.cardviewch1);
                break;
            case 1:
                card00.setId(R.id.cardviewch2);
                break;
            case 2:
                card00.setId(R.id.cardviewch3);
                break;
            case 3:
                card00.setId(R.id.cardviewch4);
                break;
            case 4:
                card00.setId(R.id.cardviewch5);
                break;
        }

//        ConstraintLayout cl1 = findViewById(R.id.ConstraintLayout);


//        if(student.GetLang() == 1) {
//            cl1.setRotationY(180);
//            mSlidsView.setRotationY(180);
//        }


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

    private boolean isFirstTime() {
//        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = pref.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }
        return !ranBefore;
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {


            addDotsIndicator(position);
//            if(mCurrent==0)
                mCurrent = position ;

            switch(mCurrent){
                case 0:
                    card00.setId(R.id.cardviewch1);
                    break;
                case 1:
                    card00.setId(R.id.cardviewch2);
                    break;
                case 2:
                    card00.setId(R.id.cardviewch3);
                    break;
                case 3:
                    card00.setId(R.id.cardviewch4);
                    break;
                case 4:
                    card00.setId(R.id.cardviewch5);
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
            if (statue.equals("unlocked") || statue.equals("completed") ) {
                n = new Intent(Chapters.this, Ch3.class);
                startActivity(n);
            }
            else
                Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
        }else if(R.id.cardviewch4==card00.getId()){
            statue = student.getChLock("4");
            if (statue.equals("unlocked") || statue.equals("completed") ) {
                n = new Intent(Chapters.this, Ch4.class);
                startActivity(n);
            }else
                Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
        }else if(R.id.cardviewch5==card00.getId()){
            statue = student.getChLock("5");
            if (statue.equals("unlocked") || statue.equals("completed") ) {
                n = new Intent(Chapters.this, Ch5.class);
                startActivity(n);
            }else
                Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_LONG).show();
        }
    }


}





