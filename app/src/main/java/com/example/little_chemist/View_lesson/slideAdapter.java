package com.example.little_chemist.View_lesson;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

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
        //System.out.println(chapterNum);
        lessonNum= lessonKey.charAt(1) ; //it was 1
        //System.out.println(lessonNum);
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
             "C1L1S1","C1L1S2","C1L1S3","C1L1S4","C1L1S5", "C1L1S6"
            ,"C1L3S1","C1L3S2","C1L3S3","C1L3S4"
            ,"C1L4S1","C1L4S2" , "C1L4S3" , "C1L4S4"
            ,"C2L1S1","C2L1S2"
            ,"C2L4S1","C2L4S2","C2L4S3","C2L4S4","C2L4S5","C2L4S6","C2L4S7","C2L4S8"
            ,"C2L5S1","C2L5S2" , "C2L5S3" ,"C2L5S4" , "C2L5S5"
            ,"C3L1S1","C3L1S2","C3L1S3","C3L1S4","C3L1S5","C3L1S6","C3L1S7","C3L1S8"
            ,"C3L2S1","C3L2S2","C3L2S3","C3L2S4","C3L2S5","C3L2S6","C3L2S7","C3L2S8","C3L2S9"
            ,"C3L5S1","C3L5S2","C3L5S3"
            ,"C4L1S1","C4L1S2","C4L1S3","C4L1S4"
            ,"C4L2S1","C4L2S2","C4L2S3","C4L2S4"
            ,"C4L3S1","C4L3S2","C4L3S3","C4L3S4","C4L3S5"
            ,"C4L5S1","C4L5S2","C4L5S3","C4L5S4"
            ,"C5L1S1","C5L1S2","C5L1S3","C5L1S4"



    };

    public String[] ex ={
            "C1L1S1ED","C1L1S2ED","C1L1S6ED"
            ,"C1L3S3E1M", "C1L3S4E2M"
            ,"C1L4S2E1M","C1L4S4E2M"
            ,"C2L1S2E1M"
            ,"C2L4S2E1M","C2L4S4E2M","C2L4S5E3M","C2L4S8E4M"
            ,"C2L5S3E1M","C2L5S5E2M"
            ,"C3L1S4E1M"
            ,"C4L5S1E1M","C4L5S3E2M"
            ,"C5L1E1M","C5L1E2M"

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
            //System.out.println(ex[i]);
           tempEx = ex[i].substring(0,6) ;
            //System.out.println(tempEx);

            if (cleanContent[position].equals(tempEx)){
                if(tempEx.equals("C1L1S1")){
                    AcEx[index] = new Intent(view.getContext(), ex_DragAndDrop1.class);
//                    AcEx[index].putExtra("exKey", tempEx);
//                    BtnEx[index] = view.findViewById(R.id.ex);
                }
                else if(tempEx.equals("C1L1S6")){
                    AcEx[index] = new Intent(view.getContext(), ex_DragAndDrop3.class);

                }
                else {
                    tempEx = ex[i];
                    AcEx[index] = new Intent(view.getContext(), ex_multiple_choice.class);
                }
                    AcEx[index].putExtra("exKey", tempEx);
                    BtnEx[index] = view.findViewById(R.id.ex);
                    Intent n = AcEx[index];


                BtnEx[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view.getContext().startActivity(n);
                    }
                });

               //System.out.println("hi");
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

    public Context getContext(){
        return this.context ;
    }




}

