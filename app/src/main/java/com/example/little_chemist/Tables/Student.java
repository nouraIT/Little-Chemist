package com.example.little_chemist.Tables;
import androidx.appcompat.app.AppCompatActivity;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.LoginPage;
import com.example.little_chemist.MainActivity;

import java.io.Serializable;


public class Student extends AppCompatActivity implements Serializable {
    int Id;
    String Scores;
    double TotalScore, ScoresA[]= new double[5];
    String UserName,Password, QZLocks, CHLocks, LSNLocks, SecQ, SecA;
    int Arabic;
    DatabaseHelper helper = new DatabaseHelper(Student.this);

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
    public Student(int id,String scores,String QL,String CHl,String LL,String username,String Pass,String SQ,String SA,int lang) {
        this.Id=id;

        //scores = "c1:0,c2:0..."
        int ch = 1;

        for(int i =0;i<5;i++){
//            scores="c1:100,c2:70,c3:40,c4:9,c5:0,";
//            String ch = 'c'+(i+1)+"" ;
            System.out.println(scores);
            int first = scores.indexOf("c"+(ch)+":");
            ch++;
            System.out.println(first);
            int last = scores.indexOf(",",first);
            System.out.println(last);

            System.out.println(scores.substring(first+4,last));
            ScoresA[i] = Double.parseDouble(scores.substring(first+3,last));
        }

        this.TotalScore =0;
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

    public void SetTotalScore(int quizID , double score) {
//TODO should save it in a string too and save it in the database
        this.ScoresA[quizID-1] = score; //minus 1 because it's an array
        this.TotalScore+=score;
    }
    public double[] GetTotalScore() {
//        Scores="";
//        for(int i =0;i<5;i++)
//            Scores+="c"+(i+1)+this.ScoresA[i]+",";
        return ScoresA;
    }


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

    public void saveProgress(int lessonId){

    }

//    public void changeLang(int arabic){
//
//        Arabic = arabic;
//        helper.changeLang(UserName,arabic);
//
//    }

    public int GetLang(){

        return Arabic; //1 means arabic
    }


    public String getLsnLock(String num){

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
//        System.out.println(QZLocks+ " " );

         //statue = null;

        int lsnIndex = QZLocks.indexOf(num);
        int endIndex = QZLocks.indexOf(",",lsnIndex);

//        System.out.println(lsnIndex+ " " + endIndex);

        String statue = QZLocks.substring(lsnIndex+2,endIndex);

        return statue;

    }

    public String viewScore() {

        //TODO fix this, it's not done

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


}

