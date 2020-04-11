package com.example.little_chemist.Chapters_dir;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class chaptersAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater ;

    private int[] chapter_Lock = new int[5];
    private String[] chapter_num,chapter_name ;

    private int[] chapter_bg={
            R.drawable.ch1_bg,
            R.drawable.ch2_bg,
            R.drawable.ch3_bg,
            R.drawable.ch4_bg,
            R.drawable.ch5_bg
    };

    private int[] chapter_drawings={
            R.drawable.ch1character,
            R.drawable.ch2character,
            R.drawable.ch3character,
            R.drawable.ch4character,
            R.drawable.ch5character
    };

    private int[] chapter_Img={
            R.drawable.atom,
            R.drawable.element,
            R.drawable.flask,
            R.drawable.dry_tree,
            R.drawable.periodictable
    };

    public chaptersAdapter(){

    }
    public chaptersAdapter(Context context) {
        //super();
        DatabaseHelper helper = new DatabaseHelper(context);

        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name = pref.getString("username", null); // getting String
        Student student = helper.getStudent(name);

        this.context = context;
        chapter_num = this.context.getResources().getStringArray(R.array.chapters_numbers);
        chapter_name = this.context.getResources().getStringArray(R.array.chapter_names);


        for(int i =0;i<5;i++){
            String lock = student.getChLock(String.valueOf(i+1));
            if(lock.equals("unlocked"))
                chapter_Lock[i] = R.drawable.padlock;
            else if(lock.equals("locked"))
                chapter_Lock[i] = R.drawable.lock;
            else
                chapter_Lock[i] = R.drawable.star;
        }

    }

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
        SlidechapterDraw.setImageResource(chapter_drawings[position]);
        SlideChapterCard.setBackgroundResource(chapter_bg[position]);
        SlideLockImg.setImageResource(chapter_Lock[position]);
        inCardChapterName.setText(chapter_name[position]);
        inCardChapterImg.setImageResource(chapter_Img[position]);

        container.addView(view);
        return view ;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }
}
