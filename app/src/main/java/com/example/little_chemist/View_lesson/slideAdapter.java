package com.example.little_chemist.View_lesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch2;
import com.example.little_chemist.Chapters_dir.Ch3;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.Chapters_dir.Ch5;
import com.example.little_chemist.R;

public class slideAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater ;
    String lessonKey ;
    char chapterNum ;
    char lessonNum ;
    Button slideEx ;
    String[] cleanContent ;
    String[] cleanEx ;
    String[] cleanImg;
    int count =0 ;
     Animation btnAmim ;
    String tempEx ;
    Intent[] AcEx ;
    Button[] BtnEx ;



    public slideAdapter(Context context , int value) {
        this.context = context;
        lessonKey=Integer.toString(value) ;
        chapterNum = lessonKey.charAt(0) ;
        lessonNum= lessonKey.charAt(1) ;
        cleanContent = new String[content.length] ;
        cleanImg = new String[content.length] ;
        AcEx = new Intent[ex.length] ;
        BtnEx = new Button[ex.length] ;




        int index = 0 ;
        for (int i=0 ; i< content.length;i++){
            if((chapterNum==content[i].charAt(1))){
                if(lessonNum==content[i].charAt(3)){
                    cleanContent[index]=content[i] ;
                    cleanImg[index]=content[i].toLowerCase() ;
                    index ++;}}
        }


    }


    public String[] content ={
            "C1L1S1","C1L1S2","C1L1S3","C1L1S4","C1L1S5","C1L1S6","C1L1S7","C1L3S1","C1L3S2","C1L3S3","C1L3S4","C1L4S1","C1L4S2"
            ,"C4L3S1","C4L3S2","C4L3S3","C4L3S4","C4L3S5"
            ,"C4L5S1","C4L5S2","C4L5S3","C4L5S4"

    };

    public String[] ex ={
            "C1L1S1ED","C1L1S2ED","C1L1S4ED","C1L3S3E1M", "C1L3S4E2M" , "C1L4S1E1M","C1L4S2E2M"
            ,"C4L5S1E1M","C4L5S3E2M"
    };




    @Override
    public int getCount() {
        if (count!= 0)
            return count ;
        count =0 ;
        for (int i=0 ; i< content.length;i++){
            if(chapterNum==content[i].charAt(1)){
                if(lessonNum==content[i].charAt(3)){
                    count ++ ;}}
        }

        return count  ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE) ;
        View view = layoutInflater.inflate(R.layout.activity_slide_adapter,container,false) ;

        ImageView slideImgView = view.findViewById(R.id.slide_img) ;
        //TextView slideheader = view.findViewById(R.id.slide_header) ;
        TextView slideDes = view.findViewById(R.id.slide_description) ;
        slideEx = view.findViewById(R.id.ex) ;
        btnAmim = AnimationUtils.loadAnimation(this.context, R.anim.button_anim) ;


        //set the button ex visible
        int index=0 ;
        for(int i=0;i<ex.length;i++){
           tempEx = ex[i].substring(0,ex.length-3) ;
            System.out.println(tempEx) ;
            if (cleanContent[position].equals(tempEx)){
                tempEx=ex[i] ;
                AcEx[index] = new Intent(view.getContext(), ex_multiple_choice.class);
                AcEx[index].putExtra("exKey",tempEx) ;
                BtnEx[index]=view.findViewById(R.id.ex) ;
                Intent n = AcEx[index] ;

                BtnEx[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view.getContext().startActivity(n);
                    }
                });

                System.out.println(tempEx);
                slideEx.setVisibility(View.VISIBLE);

                slideEx.setAnimation(btnAmim) ;

                index++ ;
               break ;

           }
        }



                int resID = context.getResources().getIdentifier(cleanContent[position], "string", context.getPackageName());
                slideDes.setText(resID);
                resID = context.getResources().getIdentifier(cleanImg[position], "drawable", context.getPackageName());
                slideImgView.setImageResource(resID);





//        slideheader.setText(slide_header[position]);
//        slideDes.setText(slide_description[position]);

        container.addView(view);
        return view ;
    }


    public String getEx(){
        return tempEx ;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }




}

