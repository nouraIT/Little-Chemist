package com.example.little_chemist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.little_chemist.kotlin.Intrinsics;

import alirezat775.lib.carouselview.CarouselAdapter;
import alirezat775.lib.carouselview.CarouselModel;

public class adapter extends CarouselAdapter {

    private final int EMPTY_ITEM = 0;
    private final int NORMAL_ITEM = 1;
    private CarouselViewHolder vh;

    @Nullable
    private adapter.OnClick onClick;

// ------------------------------------------------------------------------------------

    public interface OnClick {
        void click(@NonNull model var1);
    }

//    ------------------------------------------------------------------------------------

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
        CarouselViewHolder holder;
        View v;
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

            ((adapter.MyViewHolder)var5).getTitle().setText((CharSequence)String.valueOf(model.getId()));


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


//    ------------------------------------------------------------------------------------



    public final class MyViewHolder extends CarouselViewHolder {
        @NonNull
        private TextView title;

        @NonNull
        public final TextView getTitle() {
            return this.title;
        }

        public final void setTitle(@NonNull TextView var1) {
            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            this.title = var1;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            TextView textview = itemView.findViewById(R.id.item_text);
            Intrinsics.checkExpressionValueIsNotNull(textview, "itemView.item_text");

            this.title = textview;

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
