package com.example.little_chemist.Tables;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz extends AppCompatActivity {

    int quizID;
    String quizName,LockQuiz;

    //public  void SetId(int id){this.chapterID=id; }
    public  int GetId(){return quizID;}

    public void SetQuizName(String quizname){
        this.quizName=quizname;
    }
    public String GetQuizName(){
        return quizName;
    }

    public void SetLockQuiz(String lockQuiz){
        this.LockQuiz=lockQuiz;
    }
    public String GetLockQuiz(){
        return LockQuiz;
    }

protected void Quiz(Bundle savedInstanceState)
{
    //super.Quiz(savedInstanceState);
//    setContentView(R.layout.quizes);
//    Toolbar toolbar = findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    getSupportActionBar().setDisplayShowHomeEnabled(true);
//    String chpaterNumber = getIntent().getStringExtra("ChapterNumber");
//    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            //switch to go back to chapter
//            Intent Homepage = new Intent(Quiz.this, Chapters.class);
//            startActivity(Homepage);
//            //finish();
//        }
//    });
}

//call database
}

