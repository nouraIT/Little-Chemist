package com.example.little_chemist.Tables;

public class Chapter {

    int chapterID;
    String chapterName, lockChapter;

    //public  void SetId(int id){this.chapterID=id; }
    public  int GetId(){return chapterID;}

    public void SetChapterName(String chaptername){
        this.chapterName=chaptername;
    }
    public String GetChapterName(){
        return chapterName;
    }

    public void SetLockChapter(String lockchapter){
        this.lockChapter=lockchapter;
    }
    public String GetLockChapter(){
        return lockChapter;
    }

}
