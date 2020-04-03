package com.example.little_chemist.View_lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;

import androidx.constraintlayout.widget.ConstraintLayout;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

import static com.google.sceneform_animation.cq.br;

public class ex_multiple_choice extends AppCompatActivity {


    public String chnum,lessonnum ,name;
    public int lessonid, lessonkey,segmentN,ex ;
    public TextView exNum, lessonName, Q;
    public CircularProgressButton[] a ;
    public Button finish ;
    private SharedPreferences pref;
    private  DatabaseHelper helper ;
    private Bundle bundle ;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ex_multiple_choice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String temp ;
        bundle=getIntent().getExtras();
        String exKey=bundle.getString("exKey");
        temp = String.valueOf(exKey.charAt(5)) ;
        segmentN = Integer.parseInt(temp) ;
        temp = String.valueOf(exKey.charAt(7)) ;
        ex = Integer.parseInt(temp) ;
        chnum = String.valueOf(exKey.charAt(1)) ;
        lessonnum = String.valueOf(exKey.charAt(3)) ;
        temp = chnum + lessonnum ;
        lessonkey = Integer.parseInt(temp) ;
        lessonName = findViewById(R.id.lessonTitle2) ;
        finish = findViewById(R.id.finish2) ;
        setLessontitle(lessonkey);

        helper = new DatabaseHelper(this);
        pref = this.getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        name = pref.getString("username", null); // getting String
        student = helper.getStudent(name);
        lessonid = bundle.getInt("lessonid") ;

        ConstraintLayout exlayout = findViewById(R.id.exlayout);
        switch (chnum){
            case "1":
                exlayout.setBackgroundResource(R.drawable.ch1lessonbackground);
                break;
            case "2":
                exlayout.setBackgroundResource(R.drawable.ch2lessonbackground);
                break;
            case "3":
                exlayout.setBackgroundResource(R.drawable.ch3lessonbackgrond);
                break;
            case "4":
                exlayout.setBackgroundResource(R.drawable.ch4lessonbackground);
                break;
            case "5":
                exlayout.setBackgroundResource(R.drawable.ch5lessonbackground);
                break;
            default:exlayout.setBackgroundResource(R.drawable.ch1lessonbackground);
        }



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(ex_multiple_choice.this, Lessons.class );
                n.putExtra("lesson",lessonkey) ;
                n.putExtra("lessonId",lessonid) ;
                n.putExtra("segmentId",segmentN-1) ;
                System.out.println("ex "+lessonid);
                startActivity(n);
                finish();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int exNum=bundle.getInt("exNum") ;
                helper.updateEx(name,exNum,"completed");//,Integer.toString(lessonkey).charAt(0));
                Intent n = new Intent(ex_multiple_choice.this, Lessons.class);
                n.putExtra("lesson",lessonkey) ;
                n.putExtra("lessonId",lessonid) ;
                n.putExtra("segmentId",segmentN-1) ;
                startActivity(n);
                finish();
            }
        });


        //extract strings from resources
        String[] QItems = getResources().getStringArray(getResources().getIdentifier(exKey, "array", getPackageName()));
        int len = getResources().getStringArray(getResources().getIdentifier(exKey, "array", getPackageName())).length ;

        Q = findViewById(R.id.Q) ;
        a=new CircularProgressButton[4];
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
            a[i-1].setText(QItems[i]); }

        exNum = findViewById(R.id.exNum) ;
        String exText =getResources().getString(getResources().getIdentifier("exercise", "string", getPackageName()));
        exNum.setText(exText+" "+ex);
        String temp1 = exKey.substring(0,4);
        temp1=temp1+"A";
        String[] An =getResources().getStringArray(getResources().getIdentifier(temp1, "array", getPackageName()));
        String ExtractA = An[ex-1] ;

        for(int i=1 ;i<QItems.length;i++){
            if(QItems[i].equals(ExtractA)){
                CircularProgressButton b = a[i-1] ;
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        b.startAnimation();
                        //b.startMorphAnimation();
                        b.doneLoadingAnimation(Color.GREEN ,  BitmapFactory.decodeResource(getResources(), R.drawable.done));
                        finish.setVisibility(View.VISIBLE);

                        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                        String name = pref.getString("username", null); // getting String
                        Student student = helper.getStudent(name);
                        int exNum=bundle.getInt("exNum") ;
                        helper.updateEx(name,exNum,"completed");//,Integer.toString(lessonkey).charAt(0));
                        }
                });break; } }

        for(int i=1 ;i<QItems.length;i++){
            if(QItems[i].equals(ExtractA)){
                continue; }
                CircularProgressButton b = a[i-1] ;
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        b.startAnimation();
                        //b.startMorphAnimation();
                        b.doneLoadingAnimation(Color.RED ,  BitmapFactory.decodeResource(getResources(), R.drawable.x));
                         }
                }); }}


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
                lessonName.setText(R.string.Ch5Lsn5);}


//[do some async task. When it finishes]
//You can choose the color and the image after the loading is finished
       // btn.doneLoadingAnimation(fillColor, bitmap);





}

