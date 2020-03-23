package com.example.little_chemist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


import com.example.little_chemist.Tables.Chapter;
import com.example.little_chemist.Tables.Lesson;
import com.example.little_chemist.Tables.Quiz;
import com.example.little_chemist.Tables.Student;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.SQLException;


public class DatabaseHelper extends SQLiteOpenHelper {



    public static class FeedEntry implements BaseColumns {

        //---------------------- Initialise tables ------------------------

        private static final String TABLE_STUDENT = "Student";
        private static final String COLUMN_ID = "Id";
        private static final String COLUMN_SCORE = "Scores";
        private static final String COLUMN_QZLOCKS = "QZLocks";
        private static final String COLUMN_CHLOCKS = "CHLocks";
        private static final String COLUMN_LSNLOCKS = "LSNLocks";
        private static final String COLUMN_USERNAME = "UserName";
        private static final String COLUMN_PASSWORD = "Password";
        private static final String COLUMN_SECQ = "SecQ";
        private static final String COLUMN_SECA = "SecA";
        private static final String COLUMN_LANG = "Arabic";
        private static final String COLUMN_IMG = "ImageId";

        //-----------------------------------------------

        private static final String TABLE_CHAPTER = "Chapter";
        private static final String COLUMN_CHID = "chapterID";
        private static final String COLUMN_CHNAME = "chapterName";
        private static final String COLUMN_CHLOCK = "lockChapter";

        //-----------------------------------------------

        private static final String TABLE_LESSON = "Lesson";
        private static final String COLUMN_LSNID = "lessonID";
        private static final String COLUMN_LSNNAME = "lessonName";
        private static final String COLUMN_LSNLOCK = "lockLesson";
        private static final String COLUMN_EXERCISE = "exercise";
        private static final String COLUMN_CH = "ChId";
        private static final String COLUMN_CONTENT = "content";

        //-----------------------------------------------

        private static final String TABLE_QUIZ = "Quiz" ;//quizIC quizName LockQuiz
        private static final String COLUMN_QUIZID = "quizID";
        private static final String COLUMN_QUIZNAME = "quizName";
        private static final String COLUMN_LOCKQUIZ = "LockQuiz";



        //-----------------------------------------------

    }
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LittleChemist.db";
    SQLiteDatabase db;



    //---------------------- create, add and delete tables ------------------------

    private static final String SQL_CREATE_STUDENT=
            "CREATE TABLE "+FeedEntry.TABLE_STUDENT +" ("+
                    FeedEntry.COLUMN_ID + "  INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_SCORE + " TEXT," +
                    FeedEntry.COLUMN_QZLOCKS + " TEXT," +
                    FeedEntry.COLUMN_CHLOCKS + " TEXT," +
                    FeedEntry.COLUMN_LSNLOCKS + " TEXT," +
                    FeedEntry.COLUMN_USERNAME + " TEXT,"+
                    FeedEntry.COLUMN_PASSWORD +" TEXT,"+
                    FeedEntry.COLUMN_SECQ +" TEXT,"+
                    FeedEntry.COLUMN_SECA +" TEXT,"+
                    FeedEntry.COLUMN_LANG +" INTEGER," +
                    FeedEntry.COLUMN_IMG +" INTEGER)" ;

    private static final String SQL_CREATE_CHAPTER=
            "CREATE TABLE "+FeedEntry.TABLE_CHAPTER +" ("+
                    FeedEntry.COLUMN_CHID + "  INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_CHNAME + " TEXT," +
                    FeedEntry.COLUMN_CHLOCK + " TEXT)" ;

    private static final String SQL_CREATE_LESSON=
            "CREATE TABLE "+FeedEntry.TABLE_LESSON +" ("+
                    FeedEntry.COLUMN_LSNID + "  INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_LSNNAME + " TEXT," +
                    FeedEntry.COLUMN_LSNLOCK + " TEXT," +
                    FeedEntry.COLUMN_EXERCISE + " TEXT," +
                    FeedEntry.COLUMN_CONTENT + " TEXT,"+
                    FeedEntry.COLUMN_CH + " INTEGER, FOREIGN KEY ("+FeedEntry.COLUMN_CH+") REFERENCES "+FeedEntry.TABLE_CHAPTER+"("+FeedEntry.COLUMN_CHID+"))";

    private static final String SQL_CREATE_QUIZ=
            "CREATE TABLE "+FeedEntry.TABLE_QUIZ +" ("+
                    FeedEntry.COLUMN_QUIZID + "  INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_QUIZNAME + " TEXT," +
                    FeedEntry.COLUMN_LOCKQUIZ + " TEXT)" ;


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_STUDENT;


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STUDENT);
        db.execSQL(SQL_CREATE_CHAPTER);
        db.execSQL(SQL_CREATE_LESSON);
        db.execSQL(SQL_CREATE_QUIZ);
        //db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    //-------------------------- inserts  ----------------------


    public void InsertStudents(Student student){



        db=getWritableDatabase();
        //To get , how many column in ur table
        String query="SELECT * FROM "+FeedEntry.TABLE_STUDENT;
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        String ch,ls,qz,scores;
        scores="c1:0,c2:0,c3:0,c4:0,c5:0,";
        qz="1:unlocked,2:unlocked,3:unlocked,4:unlocked,5:unlocked,";
        ch="1:unlocked,2:locked,3:locked,4:locked,5:locked,";
        ls="1:unlocked,2:locked,3:locked,4:locked,5:locked,6:locked,7:locked,8:locked,9:locked,10:locked," +
                "11:locked,12:locked,13:locked,14:locked,15:locked,16:locked,17:locked,18:locked,19:locked,20:locked," +
                "21:locked,22:locked,23:locked,24:locked,25:locked,";

        if(student.GetUserName().equals("admin") ||student.GetUserName().equals("Admin") ){
            scores="c1:100,c2:70,c3:40,c4:9,c5:0,";
            qz="1:completed,2:unlocked,3:unlocked,4:unlocked,5:unlocked,";
            ch="1:completed,2:unlocked,3:unlocked,4:unlocked,5:unlocked,";
            ls="1:completed,2:unlocked,3:unlocked,4:unlocked,5:unlocked,6:ununlocked,7:unlocked,8:unlocked,9:unlocked,10:unlocked," +
                    "11:unlocked,12:unlocked,13:unlocked,14:unlocked,15:unlocked,16:unlocked,17:unlocked,18:unlocked,19:unlocked,20:unlocked," +
                    "21:unlocked,22:unlocked,23:unlocked,24:unlocked,25:unlocked,";;
        }
//, (student.GetTotalScore())
        student = new Student( (count+1) ,scores,(qz),(ch),(ls),(student.GetUserName()),(student.GetPassword()),
                (student.GetSecQ()),(student.GetSecA()),(student.GetLang()), R.drawable.face1);

        ContentValues contentvalues=new ContentValues();
        contentvalues.put(FeedEntry.COLUMN_ID,count+1);
        contentvalues.put(FeedEntry.COLUMN_SCORE, scores);
        contentvalues.put(FeedEntry.COLUMN_QZLOCKS, qz);
        contentvalues.put(FeedEntry.COLUMN_CHLOCKS, ch);
        contentvalues.put(FeedEntry.COLUMN_LSNLOCKS, ls);
        contentvalues.put(FeedEntry.COLUMN_USERNAME, student.GetUserName());
        contentvalues.put(FeedEntry.COLUMN_PASSWORD, student.GetPassword());
        contentvalues.put(FeedEntry.COLUMN_SECQ, student.GetSecQ());
        contentvalues.put(FeedEntry.COLUMN_SECA, student.GetSecA());
        contentvalues.put(FeedEntry.COLUMN_LANG, student.GetLang());
        contentvalues.put(FeedEntry.COLUMN_IMG, R.drawable.face1 );


        db.insert(FeedEntry.TABLE_STUDENT,null,contentvalues);
        db.close();
    }

//    public void InsertChapters(Chapter chapter){
//
//        db=getWritableDatabase();
//        //To get , how many column in ur table
//        String query="SELECT * FROM "+FeedEntry.TABLE_CHAPTER;
//        Cursor cursor=db.rawQuery(query,null);
//        int count=cursor.getCount();
//
//        ContentValues contentvalues=new ContentValues();
//        contentvalues.put(FeedEntry.COLUMN_CHID,count+1);
//        contentvalues.put(FeedEntry.COLUMN_CHNAME, chapter.GetChapterName());
//        contentvalues.put(FeedEntry.COLUMN_CHLOCK, chapter.GetLockChapter());
//
//        db.insert(FeedEntry.TABLE_CHAPTER,null,contentvalues);
//        db.close();
//    }

//    public void InsertLessons(Lesson lesson){
//
//        db=getWritableDatabase();
//        //To get , how many column in ur table
//        String query="SELECT * FROM "+FeedEntry.TABLE_LESSON;
//        Cursor cursor=db.rawQuery(query,null);
//        int count=cursor.getCount();
//
//        ContentValues contentvalues=new ContentValues();
//        contentvalues.put(FeedEntry.COLUMN_LSNID,count+1);
//        contentvalues.put(FeedEntry.COLUMN_LSNNAME, lesson.GetLessonName());
//        contentvalues.put(FeedEntry.COLUMN_LSNLOCK, lesson.GetLockLesson());
//        contentvalues.put(FeedEntry.COLUMN_EXERCISE, lesson.GetExercise());
//        contentvalues.put(FeedEntry.COLUMN_CH, lesson.GetChID());
//        contentvalues.put(FeedEntry.COLUMN_CONTENT, lesson.GetContent());
//
//        db.insert(FeedEntry.TABLE_LESSON,null,contentvalues);
//        db.close();
//    }

    public void InsertQuizzes(Quiz quiz){

        db=getWritableDatabase();
        //To get , how many column in ur table
        String query="SELECT * FROM "+FeedEntry.TABLE_QUIZ;
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        ContentValues contentvalues=new ContentValues();
        contentvalues.put(FeedEntry.COLUMN_QUIZID,count+1);
        contentvalues.put(FeedEntry.COLUMN_QUIZNAME, quiz.GetQuizName());
        contentvalues.put(FeedEntry.COLUMN_LOCKQUIZ, quiz.GetLockQuiz());

        db.insert(FeedEntry.TABLE_QUIZ,null,contentvalues);
        db.close();
    }


    //-------------------------- Delete ----------------------

    public void DeleteStudent(String id) {

        db = getWritableDatabase();
        db.delete(FeedEntry.TABLE_STUDENT,   "Id = ?" , new String[] {id});
        db.close();
    }


    //-------------------------- outer methods ----------------------


    public String checkPassword(String Username){

        db = this.getReadableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_STUDENT;
        String query ="SELECT UserName,Password FROM Student";

        Cursor cursor=db.rawQuery(query,null);

        String username,password;
        password="Not found";

        if(cursor.moveToFirst()){
            do{
                username=cursor.getString(0);
                if(username.contentEquals(Username)){
                    password=cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return  password;
    }

    public boolean usernameExist(String Username){
        db = this.getReadableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_NAME;
        String query ="SELECT UserName FROM Student";

        Cursor cursor=db.rawQuery(query,null);

        String username;

        if(cursor.moveToFirst()){
            do{
                username=cursor.getString(0);
                if(username.contentEquals(Username)){
                    db.close();
                    cursor.close();
                    return true;
                }
            }while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return false;
    }

    public String[] checkquestion(String Username){

        db = this.getReadableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_NAME;
        String query ="SELECT UserName,SecQ,SecA FROM Student";


        Cursor cursor=db.rawQuery(query,null);
        String[] sec = new String[2];
        String username,secQ,secA;


        if(cursor.moveToFirst()){
            do{
                username=cursor.getString(0);
                if(username.contentEquals(Username)){
                    secQ=cursor.getString(1);
                    secA=cursor.getString(2);

                    sec[0]=secQ;
                    sec[1]=secA;
                    break;
                }
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return  sec;
    }

    public void recoverPassword(String password,String username){
        db = this.getWritableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_NAME;
        String query =" UPDATE Student SET Password = '"+password +"' WHERE UserName = '"+username+"' ";

        db.execSQL(query);
        db.close();

        //Cursor cursor=db.rawQuery(query,null);
        //return cursor.moveToFirst();

    }

    public boolean checkLang(String Username){
        db = this.getReadableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_NAME;
        String query ="SELECT UserName,Arabic FROM Student";

        Cursor cursor=db.rawQuery(query,null);

        String username;
        int arabic;

        if(cursor.moveToFirst()){
            do{
                username=cursor.getString(0);
                //System.out.println(Username);
                if(username.contentEquals(Username)){
                    arabic =cursor.getInt(1);
                    db.close();
                    cursor.close();
                    //System.out.println("name is "+username+" and the arabic is "+arabic);
                    if(arabic == 1)
                        return true;

                    break;
                }
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return false;
    }

    public void changeLang(String username, int arabic){
        db = this.getWritableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_NAME;
        String query =" UPDATE Student SET Arabic = '"+arabic +"' WHERE UserName = '"+username+"' ";

        db.execSQL(query);
        db.close();

        //Cursor cursor=db.rawQuery(query,null);
        //return cursor.moveToFirst();
//        String s = "1:comp,2lock";
//        s.split(",");

    }

    public void changeImg(String username ,int img) {
        db = this.getWritableDatabase();
        String query =" UPDATE Student SET ImageId = '"+img +"' WHERE UserName = '"+username+"' ";
        db.execSQL(query);
        db.close();
    }


    public String getStudentId(String username) {

        db = this.getReadableDatabase();

        String query = "SELECT UserName,Id FROM Student";
        String currentStudent=null;

        Cursor cursor = db.rawQuery(query, null);

        String Username, Id;
        Id = "Not found";

        if (cursor.moveToFirst()) {
            do {
                Username = cursor.getString(0);
                if (Username.contentEquals(username)) {
                    Id = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return Id;
    }

    public Student getStudent(String username){
        Student student = new Student();

        db = this.getReadableDatabase();

        String query = "SELECT * FROM Student";
        //String currentStudent=null;

        Cursor cursor = db.rawQuery(query, null);
        String un,QLocks,Chlocks,LesLocks,Pass,SQ,SA,score;
        int id,lang,img;


        if (cursor.moveToFirst()) {
            do {
                un = cursor.getString(cursor.getColumnIndex("UserName"));
                if (un.contentEquals(username)) {

                    id = cursor.getInt(0);
                    score= cursor.getString(1);
                    QLocks= cursor.getString(2);
                    Chlocks= cursor.getString(3);
                    LesLocks= cursor.getString(4);
                    un= cursor.getString(5);
                    Pass= cursor.getString(6);
                    SQ= cursor.getString(7);
                    SA= cursor.getString(8);
                    lang = cursor.getInt(9);
                    img = cursor.getInt(10);
                    student= new Student(id,score,QLocks,Chlocks,LesLocks,un,Pass,SQ,SA,lang,img);

                    break;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return student;

    }


    public void updateChapter(String username,int CHID,String status) {

        String [] ch = new String[5];
        String Username, CHLOCKS= "";

        db = this.getReadableDatabase();
        boolean flag=false;

        String q = "SELECT UserName,CHLOCKS FROM Student";
        Cursor cursor = db.rawQuery(q, null);

        if (cursor.moveToFirst()) {
            do {
                Username = cursor.getString(0);
                if (Username.contentEquals(username)) {
                    CHLOCKS = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }


        cursor.close();

        if(flag) {

            ch = CHLOCKS.split(",");
            CHID--;
            ch[CHID] = status;
            CHLOCKS = String.join("", ch);

            db = getWritableDatabase();
            String query = " UPDATE Student SET CHLOCKS = '" + CHLOCKS + "' WHERE UserName = '" + username + "' ";

            db.execSQL(query);
        }

        db.close();




    }

    public void updateLesson(String username, int Lid, String status) {

        String Username;
        String LLOCKS= "";

        db = this.getReadableDatabase();
        boolean flag=false;

        String q = "SELECT UserName,LSNLOCKS FROM Student";
        Cursor cursor = db.rawQuery(q, null);
        //this will find the specified student
        if (cursor.moveToFirst()) {
            do {
                Username = cursor.getString(0);
                if (Username.contentEquals(username)) {
                    LLOCKS = cursor.getString(1);
                    flag=true;
                    break;
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        //if the student is found the flag will be true
        if(flag) {
            int firstIndex =0;
            int endIndex =0;
            String oldStatus ="";
            String lsnNum = "1";

            //this loop will change the Lid and the one next to it
            for (int i=0;i<25;i++){
                if(i+1 == Lid) {
                    oldStatus += (i + 1) + ":" + status + ",";
                    lsnNum = String.valueOf(Integer.parseInt(lsnNum)+1);
                    i++;
                    oldStatus += (i + 1) + ":unlocked,";
                    continue;
                }
                firstIndex = LLOCKS.indexOf(lsnNum);
                lsnNum = String.valueOf(Integer.parseInt(lsnNum)+1);
                endIndex = LLOCKS.indexOf(",",firstIndex);
                oldStatus += (i+1)+LLOCKS.substring(firstIndex+2,endIndex)+",";
            }

            if(!oldStatus.isEmpty())
                LLOCKS=oldStatus;

            db = getWritableDatabase();
            String query = " UPDATE Student SET LSNLOCKS = '" + LLOCKS + "' WHERE UserName = '" + username + "' ";
            db.execSQL(query);
        }
        db.close();
    }

    public void updateQuiz(String username,int Qid,String status) {

        String [] ch = new String[5];
        String Username,QZLOCKS= "";
        db = this.getReadableDatabase();

        boolean flag=false;

        String q = "SELECT UserName,QZLOCKS FROM Student";
        Cursor cursor = db.rawQuery(q, null);

        if (cursor.moveToFirst()) {
            do {
                Username = cursor.getString(0);
                if (Username.contentEquals(username)) {
                    QZLOCKS = cursor.getString(1);
                    flag = true;
                    break;
                }
            } while (cursor.moveToNext());
        }


        cursor.close();

        if(flag) {
            ch = QZLOCKS.split(","); //[1:unlocked,
            Qid--;
            ch[Qid] = status;
            QZLOCKS = String.join("", ch);


            db = this.getWritableDatabase();
            String query =" UPDATE Student SET QZLOCKS = '"+ QZLOCKS +"' WHERE UserName = '"+username+"' ";

            db.execSQL(query);
        }

        db.close();

    }





}
