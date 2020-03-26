package com.example.little_chemist.Tables;
import androidx.appcompat.app.AppCompatActivity;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.LoginPage;
import com.example.little_chemist.MainActivity;

import java.io.Serializable;


public class Student extends AppCompatActivity implements Serializable {
    int Id, ImageId;
    String Scores;
    double TotalScore, ScoresA[]= new double[5];
    String UserName,Password, QZLocks, CHLocks, LSNLocks, SecQ, SecA;
    int Arabic;
    DatabaseHelper helper = new DatabaseHelper(Student.this);

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
    public Student(int id,String scores,String QL,String CHl,String LL,String username,String Pass,String SQ,String SA,int lang, int img) {
        this.Id=id;

        //scores = "c1:0,c2:0..."
        int ch = 1,first,last;

        for(int i =0;i<5;i++){
            first = scores.indexOf("c"+(ch)+":");
            ch++;
            last = scores.indexOf(",",first);
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

        ImageId = img;
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

        this.ScoresA[quizID-1] = score; //minus 1 because it's an array
        this.TotalScore+=score;
    }
    public double[] GetTotalScore() {
//        Scores="";
//        for(int i =0;i<5;i++)
//            Scores+="c"+(i+1)+this.ScoresA[i]+",";
        return ScoresA;
    }


    // a QZLOCK [1:completed, 2:Unlocked, 3:Locked]
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

    public void SetImageId(int img){    ImageId=img; }
    public int GetImageId(){ return ImageId;}

    //functions

    public void saveProgress(int lessonId){

    }

    public int GetLang(){

        return Arabic; //1 means arabic
    }


    public String getLsnLock(String num){

        String status ;
//        System.out.println("inside student "+LSNLocks);

        int lsnIndex = LSNLocks.indexOf(num);
        int endIndex = LSNLocks.indexOf(",",lsnIndex);

        if(num.length()==2)
            status = LSNLocks.substring(lsnIndex+3,endIndex);
        else
            status = LSNLocks.substring(lsnIndex+2,endIndex);


        return status;
    }

    public String getChLock(String num) {
        int lsnIndex = CHLocks.indexOf(num);
        int endIndex = CHLocks.indexOf(",",lsnIndex);
        return CHLocks.substring(lsnIndex+2,endIndex);

    }

    public String getQzLock(String num) {
        int lsnIndex = QZLocks.indexOf(num);
        int endIndex = QZLocks.indexOf(",",lsnIndex);
        return QZLocks.substring(lsnIndex+2,endIndex);
    }

    public int viewProgress() {
        int pro=0;

        //there are 25 lesson, 5 lessons for each chapter
        for(int i =0;i<25;i++){

            //count each completed lesson with 3 point
            if(getLsnLock(String.valueOf(i+1)).equals("completed")){
                pro+=3;
            }
        }

        //there 5 quizzes, one in each chapter
        for(int i =0;i<5;i++){
            if(getQzLock(String.valueOf(i+1)).equals("completed")){
                pro+=5;
            }
        }

        return pro;
    }


}

