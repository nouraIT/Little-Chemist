package com.example.little_chemist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterResult extends RecyclerView.Adapter<RecyclerViewAdapterResult.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapterResult";
    private ArrayList<String> Qinresult = new ArrayList<>();
    private ArrayList<String> wrongan = new ArrayList<>();
    private ArrayList<String> rightanswer = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterResult(Context context, ArrayList<String> Qinresult, ArrayList<String> wrongan, ArrayList<String> rightanswer){
        this.Qinresult = Qinresult;
        this.wrongan = wrongan;
        this.rightanswer = rightanswer;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.Qinresult.setText(Qinresult.get(position));
        holder.wrongan.setText(wrongan.get(position));
        holder.rightanswer.setText(rightanswer.get(position));
    }

    @Override
    public int getItemCount() {
        return Qinresult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Qinresult;
        TextView wrongan;
        TextView rightanswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Qinresult = itemView.findViewById(R.id.Question);
            wrongan = itemView.findViewById(R.id.wronganswer);
            rightanswer = itemView.findViewById(R.id.rightanswer);
        }
    }
}

