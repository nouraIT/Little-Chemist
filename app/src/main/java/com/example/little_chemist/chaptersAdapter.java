package com.example.little_chemist;

import android.content.Context;
import android.content.res.*;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class chaptersAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater ;

    public chaptersAdapter(Context context) {

        this.context = context;
        chapter_num = this.context.getResources().getStringArray(R.array.chapters_numbers);
        chapter_name = this.context.getResources().getStringArray(R.array.chapter_names);
    }


    //String n = context.getResources().getString(R.string.chapter_1);
    //    = context.getResources().getStringArray(R.array.chapter_names);

    String[] chapter_num,chapter_name ;

    public int[] chapter_bg={
            R.drawable.ch1_bg,
            R.drawable.ch2_bg,
            R.drawable.ch3_bg,
            R.drawable.ch4_bg,
            R.drawable.ch5_bg
    };

    public int[] chapter_Lock={
            R.drawable.padlock,
            R.drawable.lock,
            R.drawable.lock,
            R.drawable.lock,
            R.drawable.lock,
    };

    public int[] chapter_drawings={
            R.drawable.atom,
            R.drawable.element,
            R.drawable.flask,
            R.drawable.dry_tree,
            R.drawable.periodictable
    };

    public int[] chapter_Img={
            R.drawable.atom,
            R.drawable.element,
            R.drawable.flask,
            R.drawable.dry_tree,
            R.drawable.periodictable
    };

    @Override
    public int getCount() {
        return chapter_num.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE) ;
        View view = layoutInflater.inflate(R.layout.chapters_slider,container,false) ;


        //chNum
        TextView SlidechapterNum = view.findViewById(R.id.chNum) ;
        //chapterName
        TextView SlidechapterName = view.findViewById(R.id.chapterName) ;
        //chapterDrawing
        ImageView SlidechapterDraw = view.findViewById(R.id.chapterDrawing) ;
        //chapterCard
        LinearLayout SlideChapterCard = view.findViewById(R.id.chapterCard) ;
        //lockImg
        ImageView SlideLockImg = view.findViewById(R.id.lockImg) ;
        //chName
        TextView inCardChapterName = view.findViewById(R.id.chName) ;
        //chImg
        ImageView inCardChapterImg = view.findViewById(R.id.chImg) ;



        SlidechapterNum.setText(chapter_num[position]);
        SlidechapterName.setText(chapter_name[position]);
        SlidechapterDraw.setImageResource(chapter_drawings[position]);
        SlideChapterCard.setBackgroundResource(chapter_bg[position]);
        SlideLockImg.setImageResource(chapter_Lock[position]);
        inCardChapterName.setText(chapter_name[position]);
        inCardChapterImg.setImageResource(chapter_Img[position]);

//        ImageView slideImgView = view.findViewById(R.id.slide_img) ;
//        TextView slideheader = view.findViewById(R.id.slide_header) ;
//        TextView slideDes = view.findViewById(R.id.slide_description) ;
//
//        slideImgView.setImageResource(slide_img[position]);
//        slideheader.setText(slide_header[position]);
//        slideDes.setText(slide_description[position]);

        container.addView(view);
        return view ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }
}
