package com.example.little_chemist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ResultAdapter extends PagerAdapter {

    private ArrayList<ResultModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public ResultAdapter(Context context, ArrayList<ResultModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.resultitem, container, false);

        TextView Question, Option, currect;
        ImageView optionimage;

        Question = view.findViewById(R.id.Question);
        Option = view.findViewById(R.id.wronganswer);
        currect = view.findViewById(R.id.rightanswer);
        optionimage = view.findViewById(R.id.optionimage);

        optionimage.setImageResource(models.get(position).getOptionimage());
        Question.setText(models.get(position).getQuestion());
        Option.setText(models.get(position).getOption());
        currect.setText(models.get(position).getCurrect());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
