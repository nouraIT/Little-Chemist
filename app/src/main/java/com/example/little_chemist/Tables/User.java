package com.example.little_chemist.Tables;

public class User {
    int Id;
    String UserName,Password;


    // public  void SetId(int id){this.Id=id; }
    //public  int GetId(){return Id;}

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
}

