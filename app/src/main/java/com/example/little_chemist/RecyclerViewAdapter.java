package com.example.little_chemist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    //var
    private ArrayList<Integer> mImage = new ArrayList<Integer>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Integer> image) {
        mImage = image;
        mContext = context;

    }

    public RecyclerViewAdapter(adapter.MyViewHolder myViewHolder, ArrayList<Integer> mImage) {
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImage.get(position))
//                .into(holder.image);

        GlideApp.with(mContext)
                .load(mImage.get(position))
                .placeholder(R.drawable.graycircle)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imagerecycler);

        }
    }
}
