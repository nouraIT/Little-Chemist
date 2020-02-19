package com.example.little_chemist.Tables;

public class Lesson {

    int lessonID, ChId;
    String lessonName, lockLesson, exercise, content;

    //public  void SetId(int id){this.lessonID=id; }
    public  int GetId(){return lessonID;}

    public void SetLessonName(String lessonname){
        this.lessonName=lessonname;
    }
    public String GetLessonName(){
        return lessonName;
    }

    public void SetLockLesson(String locklesson){
        this.lockLesson=locklesson;
    }
    public String GetLockLesson(){
        return lockLesson;
    }

    public void SetExercise(String exercise){
        this.exercise=exercise;
    }
    public String GetExercise(){
        return exercise;
    }

    public int GetChID(){   return ChId;}
    public void SetChID(int id){ChId = id;  }

    public void SetContent(String content){
        this.content=content;
    }
    public String GetContent(){
        return content;
    }

}
