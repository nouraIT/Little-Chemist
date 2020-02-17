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

    public void signup(){

    }

    public void logOut(){

    }


    public void saveProgress(int lessonId){

    }

    public void submitQuiz(){

    }


    public void viewContent(){

    }

    public void takeQuiz(int id){

    }


    public void changeLang(int arabic){
        //TODO save the language setting here ?
        Arabic = arabic;
        helper.changeLang(UserName,arabic);

    }

    public int GetLang(){

        return Arabic;
    }

    public void viewScore(){

    }
    public void ViewLesson(int id){

    }

    public boolean deleteAccountReq(){

        return false;
    }

    public void recoverPassword(){


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

