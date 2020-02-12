package com.example.little_chemist.Tables;

public class Quiz {

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


}

