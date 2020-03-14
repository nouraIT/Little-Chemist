package com.example.little_chemist.Tables;
import androidx.appcompat.app.AppCompatActivity;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.MainActivity;

import java.io.Serializable;


public class Student implements Serializable {
    int Id, TotalScore;
    String UserName,Password, QZLocks, CHLocks, LSNLocks, SecQ, SecA;
    int Arabic;
    DatabaseHelper helper;// = new DatabaseHelper();

    //TODO save the score of each chapter in the DB but the total score will be saved here

    //static int i = 0 ;
    public Student(String name, String pass, int arabic){
        UserName = name;
        Password = pass;
        Arabic = arabic;
        QZLocks = "1:unlocked,2:unlocked,3:unlocked,4:unlocked,5:unlocked,";
        CHLocks ="1:unlocked,2:locked,3:locked,4:locked,5:locked,";
        LSNLocks ="1:unlocked,2:locked,3:locked,4:locked,5:locked,6:locked,7:locked,8:locked,9:locked,10:locked," +
                "11:locked,12:locked,13:locked,14:locked,15:locked,16:locked,17:locked,18:locked,19:locked,20:locked," +
                "21:locked,22:locked,23:locked,24:locked,25:locked,";
    }
    public Student(int id,int score,String QL,String CHl,String LL,String username,String Pass,String SQ,String SA,int lang) {
        this.Id=id;
        this.TotalScore =score;
        this.UserName = username;
        this.Password= Pass;
        this.QZLocks=QL;
        this.CHLocks=CHl;
        this.LSNLocks=LL;
        this.SecQ=SQ;
        this.SecA=SA;
        this.Arabic= lang;


    }

    public Student(){

        Arabic = 0;
    }

    // public  void SetId(int id){this.Id=id; }
    public  int GetId(){return Id;}

    public void SetUserName(String username){
        this.UserName=username;
    }
    public String GetUserName(){
        return UserName;
    }

    public void SetPassword(String password){
        this.Password=password;
    }
    public String GetPassword(){
        return Password;
    }

    public void SetTotalScore(int totalscore) { this.TotalScore=totalscore;}
    public int GetTotalScore() { return TotalScore;}


    // a QZLOCK is gonna look something like this [ch1:completed, ch2:Unlocked, ch3:Locked]
    public void SetQZLocks(String qzlocks) { this.QZLocks=qzlocks;}
    public String GetQZLocks() {return QZLocks;}

    public void SetCHLocks(String chlocks) { this.CHLocks=chlocks;}
    public String GetCHLocks() {return CHLocks;}

    public void SetLSNLocks(String lsnlocks) { this.LSNLocks=lsnlocks;}
    public String GetLSNLocks() {return LSNLocks;}


    public void SetSecQ(String secq) { this.SecQ=secq;}
    public String GetSecQ() {return SecQ;}

    public void SetSecA(String seca) { this.SecA=seca;}
    public String GetSecA() {return SecA;}

    //functions

//    public void signup(){
//
//    }
//
//    public void logOut(){
//
//    }
//
//
//    public void saveProgress(int lessonId){
//
//    }
//
//    public void submitQuiz(){
//
//    }
//
//
//    public void viewContent(){
//
//    }
//
//    public void takeQuiz(int id){
//
//    }


    public void changeLang(int arabic){
        //TODO save the language setting here ?
        Arabic = arabic;
        helper.changeLang(UserName,arabic);

    }

    public int GetLang(){

        return Arabic; //1 means arabic
    }
//
//    public void viewScore(){
//
//    }
//    public void ViewLesson(int id){
//
//    }
//
//    public boolean deleteAccountReq(){
//
//        return false;
//    }
//
//    public void recoverPassword(){
//
//
//    }

    public String getLsnLock(String num){

        //TODO fix this, this doesn't tell us which lsn belong to which chapter

        //completed
//        LSNLocks ="1:unlocked,2:locked,3:locked,4:locked,5:locked";

//        System.out.println(LSNLocks+ " " );


        int lsnIndex = LSNLocks.indexOf(num);
        int endIndex = LSNLocks.indexOf(",",lsnIndex);

//        System.out.println(lsnIndex+ " " + endIndex);

        String statue = LSNLocks.substring(lsnIndex+2,endIndex);
//        if(statue!=null)
            return statue;


//        return null;
    }

    public String getChLock(String num) {
//        System.out.println(LSNLocks+ " " );

        int lsnIndex = CHLocks.indexOf(num);
        int endIndex = CHLocks.indexOf(",",lsnIndex);

//        System.out.println(lsnIndex+ " " + endIndex);

        String statue = CHLocks.substring(lsnIndex+2,endIndex);
//        if(statue!=null)
        return statue;


//        return null;
    }

    public String getQzLock(String num) {
//        System.out.println(LSNLocks+ " " );

         //statue = null;

        int lsnIndex = QZLocks.indexOf(num);
        int endIndex = QZLocks.indexOf(",",lsnIndex);

//        System.out.println(lsnIndex+ " " + endIndex);

        String statue = QZLocks.substring(lsnIndex+2,endIndex);

        return statue;

    }

    public String getProgress() {

        //these are chapters
        String progress ;//= "c1:0,c2:0,c3:0,c4:0,c5:0,";
        int ch[] = new int[5];

        int lsnPro[] = new int[25];
        int qzPro[] =new int [5];

        int chpaterCounter=0;
        int chapterNumber=0;

        //there are 25 lesson, 5 lessons for each chapter
        for(int i =0;i<25;i++){

            //count each completed lesson with 1 point
            if(getLsnLock(String.valueOf(i+1)).equals("completed")){
                lsnPro[i]++;
            }
            chpaterCounter++;
//            System.out.println("i is "+i);
            if(chpaterCounter==5) {
//                System.out.println("i is "+i+" the rest is "+lsnPro[i]+" "+lsnPro[i-4]+" "+lsnPro[i-3]+" "+lsnPro[i-2]+" "+lsnPro[i-1]);
                ch[chapterNumber++]= lsnPro[i]+lsnPro[i-4]+lsnPro[i-3]+lsnPro[i-2]+lsnPro[i-1] ;
                chpaterCounter=0;
            }
        }


        chapterNumber=0;
        //there 5 quizzes, one in each chapter
        for(int i =0;i<5;i++){
            if(getQzLock(String.valueOf(i+1)).equals("completed")){
                qzPro[i]+=5;
                ch[chapterNumber++]= qzPro[i] ;

            }
        }
        //"1:0,2:0,3:0,4:0,5:0,"
        progress = "c1:"+ch[0]+",c2:"+ch[1]+",c3:"+ch[2]+",c4:"+ch[3]+",c5:"+ch[4]+",";

        return progress;
    }

   /* public boolean LogIn(String Username, String Password){

        String query ="SELECT UserName,Password FROM Student";

        //String dbPassword = helper.checkPassword(Username, query);
        //Send UserName to Database to find it, and return Password
            //To compare it with Current Password from user input
            if(dbPassword.equals(Password))
                return true;
            else
                return false;
    }

    */


}

