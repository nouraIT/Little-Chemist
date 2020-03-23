package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.little_chemist.kotlin.Intrinsics;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizResult extends AppCompatActivity {

    private static final String TAG = QuizResult.class.getSimpleName();
    private ArrayList<String> Qinresult = new ArrayList<>();
    private ArrayList<String> wrongan = new ArrayList<>();
    private ArrayList<String> rightanswer = new ArrayList<>();
    private quizQ Quiz;
    private String[] Currectanswer;
    private String[] option;
    private String[] Question;

    public QuizResult(){
        Intrinsics.checkExpressionValueIsNotNull(TAG, "QuizResult.class.getSimpleName()");
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        Bundle b=this.getIntent().getExtras();
        Currectanswer = b.getStringArray("answer");
        option = b.getStringArray("option");
        Question = b.getStringArray("content");
        Log.d(TAG,"QuizResult onCreate");

//        Log.d(TAG, Arrays.toString(Currectanswer));
//        Log.d(TAG, Arrays.toString(option));
//        Log.d(TAG, Arrays.toString(Question));

        //RecyclerView
        getQuest();

    }

    private void getQuest(){
        Log.d(TAG, "initQuestionBitmaps: preparing bitmaps.");

        for (int i=0; i<Question.length;i++){
            Qinresult.add(Question[i]);
            wrongan.add(option[i]);
            rightanswer.add(Currectanswer[i]);
        }

        initRecyclerView();
    }

    private void initRecyclerView(){

        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewresult);
        RecyclerViewAdapterResult adapter = new RecyclerViewAdapterResult(this,  Qinresult, wrongan, rightanswer);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }
}
