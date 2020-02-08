package com.example.little_chemist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.little_chemist.Tables.Student;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static class FeedEntry implements BaseColumns {

        private static final String TABLE_NAME = "Student";
        private static final String COLUMN_ID = "Id";
        private static final String COLUMN_SCORE = "TotalScore";
        private static final String COLUMN_QZLOCKS = "QZLocks";
        private static final String COLUMN_CHLOCKS = "CHLocks";
        private static final String COLUMN_LSNLOCKS = "LSNLocks";
        private static final String COLUMN_USERNAME = "UserName";
        private static final String COLUMN_PASSWORD = "Password";
        private static final String COLUMN_SECQ = "SecQ";
        private static final String COLUMN_SECA = "SecA";

    }
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LittleChemist.db";
    SQLiteDatabase db;

    private static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE "+FeedEntry.TABLE_NAME +" ("+
                    FeedEntry.COLUMN_ID + "  INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_SCORE + " INTEGER," +
                    FeedEntry.COLUMN_QZLOCKS + " TEXT," +
                    FeedEntry.COLUMN_CHLOCKS + " TEXT," +
                    FeedEntry.COLUMN_LSNLOCKS + " TEXT," +
                    FeedEntry.COLUMN_USERNAME + " TEXT,"+
                    FeedEntry.COLUMN_PASSWORD +" TEXT,"+
                    FeedEntry.COLUMN_SECQ +" TEXT,"+
                    FeedEntry.COLUMN_SECA +" TEXT)" ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

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

    public void InsertUsers(Student student){

        db=getWritableDatabase();
        //To get , how many column in ur table
        String query="SELECT * FROM "+FeedEntry.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        ContentValues contentvalues=new ContentValues();
        contentvalues.put(FeedEntry.COLUMN_ID,count+1);
        contentvalues.put(FeedEntry.COLUMN_SCORE, student.GetTotalScore());
        contentvalues.put(FeedEntry.COLUMN_QZLOCKS, student.GetQZLocks());
        contentvalues.put(FeedEntry.COLUMN_CHLOCKS, student.GetCHLocks());
        contentvalues.put(FeedEntry.COLUMN_LSNLOCKS, student.GetLSNLocks());
        contentvalues.put(FeedEntry.COLUMN_USERNAME, student.GetUserName());
        contentvalues.put(FeedEntry.COLUMN_PASSWORD, student.GetPassword());
        contentvalues.put(FeedEntry.COLUMN_SECQ, student.GetSecQ());
        contentvalues.put(FeedEntry.COLUMN_SECA, student.GetSecA());



        db.insert(FeedEntry.TABLE_NAME,null,contentvalues);
        db.close();
    }


    public String checkPassword(String Username){

        db = this.getReadableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_NAME;
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
        return  password;
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
                    secA=cursor.getString(1);
                    sec[0]=secQ;
                    sec[1]=secA;

                    break;

                }

            }while (cursor.moveToNext());


        }


        return  sec;
    }
    public void recoverPassword(String Password,String username){
        db = this.getReadableDatabase();
        //query="SELECT UserName,Password FROM  "+FeedEntry.TABLE_NAME;
        String query =" UPDATE Student SET Password = Password  WHERE UserName = username";

        Cursor cursor=db.rawQuery(query,null);


    }

}
