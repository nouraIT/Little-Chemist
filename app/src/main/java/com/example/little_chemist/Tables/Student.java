package com.example.little_chemist.Tables;
import androidx.appcompat.app.AppCompatActivity;
import com.example.little_chemist.DatabaseHelper;


public class Student extends AppCompatActivity{
    int Id, TotalScore;
    String UserName,Password, QZLocks, CHLocks, LSNLocks, SecQ, SecA;
    //DatabaseHelper helper=new DatabaseHelper(this);

    //static int i = 0 ;


    // public  void SetId(int id){this.Id=id; }
    //public  int GetId(){return Id;}
    //TODO add security question field

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

