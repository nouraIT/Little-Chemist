package com.example.little_chemist.View_lesson;

import android.app.Activity;
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
    char chapterNum,lessonNum ;
    Button slideEx ;
    String[] cleanContent,cleanEx,cleanImg;
    int countCountent =0,countEx =0 ;
    Animation btnAmim ;
    String tempEx ;
    Intent[] AcEx ;
    Button[] BtnEx ;
    int indexEx ;
    int lessonid=0 ;
    int segment ;



    public slideAdapter(){

    }

    public slideAdapter(Context context , int value , int id ) {
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
        this.lessonid = id ;





        int indexx = 0 ;
        for (int i=0 ; i< content.length;i++){
            if((chapterNum==content[i].charAt(1))){
                if(lessonNum==content[i].charAt(3)){
                    cleanContent[indexx]=content[i] ;
                    cleanImg[indexx]=content[i].toLowerCase() ;
                    indexx ++;}}
        }



    }


    public String[] content ={
             "C1L1S1","C1L1S2","C1L1S3","C1L1S4","C1L1S5", "C1L1S6"
            ,"C1L3S1","C1L3S2","C1L3S3","C1L3S4"
            ,"C1L4S1","C1L4S2" , "C1L4S3" , "C1L4S4","C1L4S5"
            ,"C2L1S1","C2L1S2"
            ,"C2L3S1","C2L3S2","C2L3S3","C2L3S4","C2L3S5","C2L3S6"
            ,"C2L4S1","C2L4S2","C2L4S3","C2L4S4","C2L4S5","C2L4S6","C2L4S7","C2L4S8"
            ,"C2L5S1","C2L5S2" , "C2L5S3" ,"C2L5S4" , "C2L5S5"
            ,"C3L1S1","C3L1S2","C3L1S3","C3L1S4","C3L1S5","C3L1S6","C3L1S7","C3L1S8"
            ,"C3L2S1","C3L2S2","C3L2S3","C3L2S4","C3L2S5","C3L2S6","C3L2S7","C3L2S8","C3L2S9"
            ,"C3L5S1","C3L5S2","C3L5S3"
            ,"C4L1S1","C4L1S2","C4L1S3","C4L1S4"
            ,"C4L2S1","C4L2S2","C4L2S3","C4L2S4"
            ,"C4L3S1","C4L3S2","C4L3S3","C4L3S4","C4L3S5","C4L3S6"
            ,"C4L5S1","C4L5S2","C4L5S3","C4L5S4"
            ,"C5L1S1","C5L1S2","C5L1S3","C5L1S4"



    };

    public String[] ex ={
            "C1L1S6E2"
            ,"C1L3S3E1M"
            ,"C1L4S2E1M","C1L4S5E2M"
            ,"C2L1S2E1M"
            ,"C2L3S4E1M","C2L3S5E2M"
            ,"C2L4S2E1M","C2L4S4E2M","C2L4S5E3M","C2L4S8E4M"
            ,"C2L5S3E1M","C2L5S5E2M"
            ,"C3L1S4E1M","C3L2S4E1M"
            ,"C4L5S1E1M","C4L5S3E2M"
            ,"C5L1S2E1M","C5L1S4E2M"

    };




    @Override
    public int getCount() {
        if (countCountent!= 0)
            return countCountent ;
        countCountent =0 ;
        for (int i=0 ; i< content.length;i++){
            if(chapterNum==content[i].charAt(1)){
                if(lessonNum==content[i].charAt(3)){
                    countCountent ++ ;}}
        }

        return countCountent  ;
    }

    public int getNumContent(String Conid){
        for (int i=0 ;i<content.length;i++){
            if(Conid==content[i])
                return i+1 ;
        }
        return 0 ;
    }

    public int getNumEx(String Exid){
        for (int i=0 ;i<ex.length;i++){
            if(Exid==ex[i])
                return i+1 ;
        }
        return 0 ;
    }

    public int[] getExByLessonChapter(int Lessonchapterid){
        int[] exKeys = new int[getCountEx()] ;
        int index = 0 ;
        for(int i=0 ;i<ex.length;i++){
            if(chapterNum==ex[i].charAt(1)){
                if(lessonNum==ex[i].charAt(3)){
                    exKeys[index]=i+1 ;
                    index++ ;
                }}

        }
        return exKeys ;
    }

    public int getCountEx(){
        if (countEx!= 0)
            return countEx ;
        countEx =0 ;
        for (int i=0 ; i< ex.length;i++){
            if(chapterNum==ex[i].charAt(1)){
                if(lessonNum==ex[i].charAt(3)){
                    countEx ++ ;}}
        }

        return countEx  ;
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
        indexEx=0 ;

        for(int i=0;i<ex.length;i++){
            //System.out.println(ex[i]);
           tempEx = ex[i].substring(0,6) ;
            //System.out.println(tempEx);

            if (cleanContent[position].equals(tempEx)){
//                if(ex[i].equals("C1L1S1E1")){
//                    AcEx[indexEx] = new Intent(view.getContext(), ex_DragAndDrop1.class);
////                    AcEx[index].putExtra("exKey", tempEx);
////                    BtnEx[index] = view.findViewById(R.id.ex);
//                    tempEx = ex[i];
//                    String tem = ex[i] ;
//                    AcEx[indexEx].putExtra("exKey", tempEx);
//                    AcEx[indexEx].putExtra("exNum", getNumEx(tem));
//                    AcEx[indexEx].putExtra("lessonid", 1);
//                    BtnEx[indexEx] = view.findViewById(R.id.ex) ;
//                    Intent n = AcEx[indexEx];
//                    BtnEx[indexEx].setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            view.getContext().startActivity(n);
//                            ((Activity) view.getContext()).finish();
//
//                        }
//
//                    });
//
//                }
                if(ex[i].equals("C1L1S6E2")){
                    AcEx[indexEx] = new Intent(view.getContext(), ex_DragAndDrop3.class);
                    tempEx = ex[i];
                    AcEx[indexEx].putExtra("exKey", tempEx);
                    AcEx[indexEx].putExtra("exNum", getNumEx(tempEx));
                    AcEx[indexEx].putExtra("lessonid", 1);
                    BtnEx[indexEx] = view.findViewById(R.id.ex) ;
                    Intent n = AcEx[indexEx];
                    BtnEx[indexEx].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            view.getContext().startActivity(n);
                            ((Activity) view.getContext()).finish();

                        }
                    });

                }
                else {
                    tempEx = ex[i];
                    AcEx[indexEx] = new Intent(view.getContext(), ex_multiple_choice.class);

                    AcEx[indexEx].putExtra("exKey", tempEx);
                    AcEx[indexEx].putExtra("exNum", getNumEx(tempEx));
                    AcEx[indexEx].putExtra("lessonid", lessonid);
                    BtnEx[indexEx] = view.findViewById(R.id.ex) ;
                    Intent n = AcEx[indexEx];
                    BtnEx[indexEx].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            view.getContext().startActivity(n);
                            ((Activity) view.getContext()).finish();
                        }
                    });


                }



                slideEx.setVisibility(View.VISIBLE);
                slideEx.setAnimation(btnAmim) ;

                indexEx++ ;
               break ;

           }
        }



                int resID = context.getResources().getIdentifier(cleanContent[position], "string", context.getPackageName());
                slideDes.setText(resID);
                resID = context.getResources().getIdentifier(cleanImg[position], "drawable", context.getPackageName());
                slideImgView.setImageResource(resID);




        container.addView(view);
        return view ;
    }




    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }





}

