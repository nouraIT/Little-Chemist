package com.example.little_chemist.View_lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.little_chemist.R;

public class slideAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater ;

    public slideAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_img={
            R.drawable.test1 ,
            R.drawable.test2 ,
            R.drawable.test3 ,
    };

    public String[] slide_header={
            "Fluorine","Chlorine ","Bromine "
    };

    public String[] slide_description={
            "Fluorine is the ninth element of the periodic table. It has the symbol 'F'. It is also categorized as a halogen."
            ,"The Chlorine atomic number is 17 and its atomic symbol is Cl. It is also categorized as a halogen. "
            ,"Bromine is known by the symbol Br for the first two letters of its name. It has the atomic number 35. "
    };

    @Override
    public int getCount() {
        return slide_header.length;
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
        TextView slideheader = view.findViewById(R.id.slide_header) ;
        TextView slideDes = view.findViewById(R.id.slide_description) ;

        slideImgView.setImageResource(slide_img[position]);
        slideheader.setText(slide_header[position]);
        slideDes.setText(slide_description[position]);

        container.addView(view);
        return view ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }
}

