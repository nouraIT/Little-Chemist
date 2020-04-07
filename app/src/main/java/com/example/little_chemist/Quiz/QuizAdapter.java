package com.example.little_chemist.Quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.little_chemist.R;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class QuizAdapter extends PagerAdapter {
    private ArrayList<model> Models;
    private LayoutInflater layoutInflater;
    Context context;
    private String option [] = new String[5];
    private Button selected;
    private String Sselected;
    private int opsSel;
    MyViewHolder myViewHolder;

    public QuizAdapter(Context context, ArrayList<model> Models) {
        this.context = context;
        this.Models = Models;
    }

    @Override
    public int getCount() {
        return Models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.itm, container, false);
        myViewHolder = new MyViewHolder(view);

        TextView Question;
        Button op1, op2, op3, op4;

//        Question = view.findViewById(R.id.item_text);
//        op1 = view.findViewById(R.id.chos1btn);
//        op2 = view.findViewById(R.id.chos2btn);
//        op3 = view.findViewById(R.id.chos3btn);
//        op4 = view.findViewById(R.id.chos4btn);

        Question = myViewHolder.getTitle();
        op1 = myViewHolder.getBtn1();
        op2 = myViewHolder.getBtn2();
        op3 = myViewHolder.getBtn3();
        op4 = myViewHolder.getBtn4();

        Question.setText(Models.get(position).getQuestion());
        op1.setText(Models.get(position).getOp1());
        op2.setText(Models.get(position).getOp2());
        op3.setText(Models.get(position).getOp3());
        op4.setText(Models.get(position).getOp4());

        op1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selected = op1;
                opsSel=1;

                //arr[detailOnPageChangeListener.getCurrentPage()]=opsSel;
                Sselected= (String) op1.getText();
                option[position] = (String) op1.getText();
                op1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                op1.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

//                op4.setEnabled(false);
//                op2.setEnabled(false);
//                op3.setEnabled(false);
                op2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op2.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op3.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op4.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            }
        });

        op2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selected = op2;
                opsSel=2;
                //arr[detailOnPageChangeListener.getCurrentPage()]=opsSel;
                Sselected= (String) op2.getText();
                option[position] = (String) op2.getText();
                op2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                op2.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

//                op1.setEnabled(false);
//                op4.setEnabled(false);
//                op3.setEnabled(false);
                op1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op1.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op3.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op4.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            }
        });

        op3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selected = op3;
                opsSel=3;
                //arr[detailOnPageChangeListener.getCurrentPage()]=opsSel;
                Sselected= (String) op3.getText();
                option[position] = (String) op3.getText();
                op3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                op3.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

//                op1.setEnabled(false);
//                op2.setEnabled(false);
//                op4.setEnabled(false);
                op1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op1.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op2.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op4.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            }
        });

        op4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selected = op4;
                opsSel=4;
                //arr[detailOnPageChangeListener.getCurrentPage()]=opsSel;
                Sselected= (String) op4.getText();
                option[position] = (String) op4.getText();
                op4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                op4.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

//                op1.setEnabled(false);
//                op2.setEnabled(false);
//                op3.setEnabled(false);
                op1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op1.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op2.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                op3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                op3.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            }
        });

        opsSel=0;
        container.addView(view, 0);
        return view;
    }

   /* public void makeItZero(){
        opsSel=0;
    }*/

    public void Button(int [] pos, int sul) {
        //opsSel=0;
        Log.d("position", String.valueOf(sul));
        Log.d("saved option Arrays", Arrays.toString(pos));
        for (int i = 0; i < option.length; i++) {
            if (option[i] != null && i==sul) {
                if(pos[sul] ==1) {
                    myViewHolder.getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                    myViewHolder.getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    myViewHolder.getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    myViewHolder.getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    myViewHolder.getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    myViewHolder.getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    myViewHolder.getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    myViewHolder.getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));

                }

                if (pos[sul] ==2) {
                    myViewHolder.getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                    myViewHolder.getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    myViewHolder.getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    myViewHolder.getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    myViewHolder.getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    myViewHolder.getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    myViewHolder.getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    myViewHolder.getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));

                       }
                        if (pos[sul] ==3 ) {
                            myViewHolder.getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                            myViewHolder.getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                            myViewHolder.getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                            myViewHolder.getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                            myViewHolder.getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                            myViewHolder.getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                            myViewHolder.getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                            myViewHolder.getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));

                        }

                        if(pos[sul] ==4) {
                            myViewHolder.getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                            myViewHolder.getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                            myViewHolder.getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                            myViewHolder.getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                            myViewHolder.getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                            myViewHolder.getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                            myViewHolder.getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                            myViewHolder.getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));

                        }
                    }
        }
    }


    public Button getSelected() {
        return selected;
    }
    public String getSSelected() {
        return Sselected;
    }

    public int getOpsSel() {
        return opsSel;
    }

    public String[] getOption() {
        return option;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
    public final class MyViewHolder {
        @NonNull
        private TextView title;
        private Button btn1, btn2, btn3, btn4;

        @NonNull
        public final TextView getTitle() {
            return this.title;
        }

        @NonNull
        public final Button getBtn1(){return this.btn1;}

        @NonNull
        public final Button getBtn2(){return this.btn2;}

        @NonNull
        public final Button getBtn3(){return this.btn3;}

        @NonNull
        public final Button getBtn4(){return this.btn4;}

        public final void setTitle(@NonNull TextView var1) {
            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            this.title = var1;
        }

        public final void setBtn1(@NonNull Button var2){
            Intrinsics.checkParameterIsNotNull(var2, "<set-?>");
            this.btn1 = var2;
        }

        public final void setBtn2(@NonNull Button var3){
            Intrinsics.checkParameterIsNotNull(var3, "<set-?>");
            this.btn2 = var3;
        }

        public final void setBtn3(@NonNull Button var4){
            Intrinsics.checkParameterIsNotNull(var4, "<set-?>");
            this.btn3 = var4;
        }

        public final void setBtn4(@NonNull Button var5){
            Intrinsics.checkParameterIsNotNull(var5, "<set-?>");
            this.btn3 = var5;
        }

//        public void Button(int pos, String sul){
//
//            for (int i=0;i<option.length;i++){
//                if (option[i] != null){
//                    switch (pos){
//                case 1:
//                    btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn1.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//
//                    btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn2.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                    btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn3.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                    btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn4.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//
//                    break;
//                case 2:
//                    btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn2.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//
//                    btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn1.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                    btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn3.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                    btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn4.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//
//                    break;
//                case 3:
//                    btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn3.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//
//                    btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                btn1.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                btn2.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                btn4.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//
//                    break;
//
//                case 4:
//                    btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn4.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//
//                    btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn1.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                    btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn2.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//                    btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
//                    btn3.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
//
//                    break;
//            }
//                }
//            }

//            switch (option.length){
//                case option[0]:
//                    btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn1.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//                    break;
//                case 2:
//                    btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn2.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//                    break;
//                case 3:
//                    btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn3.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//                    break;
//
//                case 4:
//                    btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
//                    btn4.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
//                    break;
//            }
//            Sselected=n

     //   }

        public MyViewHolder(@NonNull View itemView) {
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            TextView textview = itemView.findViewById(R.id.item_text);
            Intrinsics.checkExpressionValueIsNotNull(textview, "itemView.item_text");
            Button btnc1 = itemView.findViewById(R.id.chos1btn);
            Button btnc2 = itemView.findViewById(R.id.chos2btn);
            Button btnc3 = itemView.findViewById(R.id.chos3btn);
            Button btnc4 = itemView.findViewById(R.id.chos4btn);

            this.title = textview;
            this.btn1 = btnc1;
            this.btn2 = btnc2;
            this.btn3 = btnc3;
            this.btn4 = btnc4;



        }
        //hello
    }
}
