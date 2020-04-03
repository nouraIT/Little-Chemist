package com.example.little_chemist.Quiz;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.little_chemist.R;

import alirezat775.lib.carouselview.CarouselAdapter;
import alirezat775.lib.carouselview.CarouselModel;

public class adapter extends CarouselAdapter {

    private static final String TAG = quizQ.class.getSimpleName();
    private final int EMPTY_ITEM = 0;
    private final int NORMAL_ITEM = 1;
    private CarouselViewHolder vh;
    private String[] option = new String[5];
    private String [] correctanswer = new String [5];
    Context context ;
    private int currentposition;

    @Nullable
    private adapter.OnClick onClick;

// ------------------------------------------------------------------------------------

    public interface OnClick {
        void click(@NonNull model var1);
    }

//    ------------------------------------------------------------------------------------

    public adapter(){

    }

    public adapter(Context context, int key){
        this.context = context;
//        Qcontent = new String[5];
//        String stringkey= String.valueOf(key);
//        Qkey= stringkey.charAt(0);

    }

    public final adapter.OnClick getOnClick() {
        return this.onClick;
    }

    public final void setOnClick(@Nullable adapter.OnClick var1) {
        this.onClick = var1;
    }

    public final void setOnClickListener(@Nullable adapter.OnClick onClick) {
        this.onClick = onClick;
    }

    public int getItemViewType(int position) {
        CarouselModel var2 = this.getItems().get(position);
        return var2 instanceof EmptyModel ? this.EMPTY_ITEM : this.NORMAL_ITEM;
    }

    @NonNull //here
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itm, parent, false);
        CarouselViewHolder holder;
        View v;

//        v = inflater.inflate(R.layout.itm, parent, false);
//        Intrinsics.checkExpressionValueIsNotNull(v, "v");
//        this.vh = (new adapter.MyViewHolder(v));
//        holder = this.vh;

        if (viewType == this.NORMAL_ITEM) {
            v = inflater.inflate(R.layout.itm, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(v, "v");
            this.vh = (new adapter.MyViewHolder(v));
            holder = this.vh;
            if (holder == null) {
                try {
                    throw new Exception("null cannot be cast to non-null type alirezat775.carouselview.adapter.MyViewHolder");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //holder = (CarouselViewHolder)((adapter.MyViewHolder)holder);
        } else {
            v = inflater.inflate(R.layout.empty_itm, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(v, "v");
//            this.vh = (new adapter.EmptyMyViewHolder(v));
            holder = this.vh;
            if (holder == null) {
                try {
                    throw new Exception("null cannot be cast to non-null type alirezat775.carouselview.adapter.EmptyMyViewHolder");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

//            holder = (CarouselViewHolder)((adapter.EmptyMyViewHolder)holder);
        }

        return holder;
    }


    // $FF: synthetic method
    // $FF: bridge method


    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {

        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Object holder2;
        CarouselViewHolder var5;
        if (holder instanceof adapter.MyViewHolder) {
            this.vh = (CarouselViewHolder) holder;
            holder2 = this.getItems().get(position);
            if (holder2 == null) {
                try {
                    throw new Exception("null cannot be cast to non-null type alirezat775.carouselview.model");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            model model = (model) holder2;
            var5 = this.vh;
            if (var5 == null) {
                try {
                    throw new Exception("null cannot be cast to non-null type alirezat775.carouselview.adapter.MyViewHolder");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
// here change item color

            ((adapter.MyViewHolder)var5).getTitle().setText((CharSequence)String.valueOf(model.getQuestion()));
            ((adapter.MyViewHolder)var5).getBtn1().setText((CharSequence)String.valueOf(model.getOp1()));
            ((adapter.MyViewHolder)var5).getBtn2().setText((CharSequence)String.valueOf(model.getOp2()));
            ((adapter.MyViewHolder)var5).getBtn3().setText((CharSequence)String.valueOf(model.getOp3()));
            ((adapter.MyViewHolder)var5).getBtn4().setText((CharSequence)String.valueOf(model.getOp4()));
            correctanswer[var5.getAdapterPosition()] = model.getCorrectop();

            currentposition = var5.getAdapterPosition();



            ((adapter.MyViewHolder)var5).getBtn1().setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int pos = var5.getAdapterPosition();
                    option[pos] = (String) ((MyViewHolder)var5).getBtn1().getText();
                    ((adapter.MyViewHolder)var5).getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                    ((adapter.MyViewHolder)var5).getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    ((adapter.MyViewHolder)var5).getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                }
            });

            ((adapter.MyViewHolder)var5).getBtn2().setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int pos = var5.getAdapterPosition();
                    option[pos] = (String) ((MyViewHolder)var5).getBtn2().getText();
                    ((adapter.MyViewHolder)var5).getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                    ((adapter.MyViewHolder)var5).getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));


                    ((adapter.MyViewHolder)var5).getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                }
            });

            ((adapter.MyViewHolder)var5).getBtn3().setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int pos = var5.getAdapterPosition();
                    option[pos] = (String) ((MyViewHolder)var5).getBtn3().getText();
                    ((adapter.MyViewHolder)var5).getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                    ((adapter.MyViewHolder)var5).getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    ((adapter.MyViewHolder)var5).getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                }
            });

            ((adapter.MyViewHolder)var5).getBtn4().setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int pos = var5.getAdapterPosition();
                    option[pos] = (String) ((MyViewHolder)var5).getBtn4().getText();
                    ((adapter.MyViewHolder)var5).getBtn4().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a47a7")));
                    ((adapter.MyViewHolder)var5).getBtn4().setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

                    ((adapter.MyViewHolder)var5).getBtn2().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn2().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn3().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn3().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                    ((adapter.MyViewHolder)var5).getBtn1().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCCCCCC")));
                    ((adapter.MyViewHolder)var5).getBtn1().setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                }
            });


        } else {
            this.vh = holder;
            holder2 = this.getItems().get(position);

            if (holder2 == null) {
                try {
                    throw new Exception("null cannot be cast to non-null type alirezat775.carouselview.EmptySampleModel");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


//            EmptyModel model = (EmptyModel)holder2;
//            var5 = this.vh;
//            if (var5 == null) {
//                try {
//                    throw new Exception("null cannot be cast to non-null type alirezat775.carouselview.SampleAdapter.EmptyMyViewHolder");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            ((adapter.EmptyMyViewHolder)var5).getTitleEmpty().setText((CharSequence)model.getText());


        }

    }

    public int getCurrentposition() {
        return currentposition;
    }

    public String[] getOption(){
        return option;
    }


//    ------------------------------------------------------------------------------------



    public final class MyViewHolder extends CarouselViewHolder {
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

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

//            this.title.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
//                public final void onClick(View it) {
//                    adapter.OnClick holder = adapter.this.getOnClick();
//                    if (holder != null) {
//                        Object textview = adapter.this.getItems().get(MyViewHolder.this.getAdapterPosition());
//                        if (textview == null) {
//                            try {
//                                throw new Exception("null cannot be cast to non-null type alirezat775.carouselview.model");
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        holder.click((model)textview);
//                    }
//
//                }
//            }));
        }
    }



//    ------------------------------------------------------------------------------------



//    public final class EmptyMyViewHolder extends CarouselViewHolder {
//        @NonNull
//        private TextView titleEmpty;
//
//        @NonNull
//        public final TextView getTitleEmpty() {
//            return this.titleEmpty;
//        }
//
//        public final void setTitleEmpty(@NonNull TextView var1) {
//            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
//            this.titleEmpty = var1;
//        }
//
//        public EmptyMyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
//            TextView empty = (TextView)itemView.findViewById(R.id.item_empty_text);
//            Intrinsics.checkExpressionValueIsNotNull(empty, "itemView.item_empty_text");
//            this.titleEmpty = empty;
//        }
//    }

    //    ------------------------------------------------------------------------------------


}
