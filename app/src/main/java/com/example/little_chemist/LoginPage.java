package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.little_chemist.Tables.Student;
import com.google.android.material.textfield.TextInputLayout;


public class LoginPage extends AppCompatActivity{

        Student student = new Student();
        DatabaseHelper helper=new DatabaseHelper(this);

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        }
        public void onBtnSignUpClick(View v){
            if(v.getId()==R.id.button){
                Intent SignUpIntent=new Intent(this,SignUp.class);
                startActivity(SignUpIntent);
            }
        }
        public  void onBtnLoginInClick(View v){
        if(v.getId()==R.id.login){

            TextInputLayout UserNameET, PasswordET;
            //Passing userName
            UserNameET= findViewById(R.id.username);
            String UserNameStr=UserNameET.getEditText().getText().toString().trim();
            //Passing Password
            PasswordET= findViewById(R.id.password);
            String PasswordStr=PasswordET.getEditText().getText().toString().trim();

            if(UserNameStr.equals(null)){
                UserNameET.setError("الرجاء ادخال اسم المستخدم");

            }
            else if(PasswordStr.equals(null)){
                PasswordET.setError("الرجاء ادخال الرقم السري");
            }


            //Send UserName to Database to find it, and return Password
            String dbPassword= helper.checkPassword(UserNameStr);
            //To compare it with Current Password from user input
            if(dbPassword.equals(PasswordStr)){
                Intent loginIntent=new Intent(this,Home.class);
                //Send Data
                loginIntent.putExtra("UserName",UserNameStr);
                loginIntent.putExtra("Password",PasswordStr);

                startActivity(loginIntent);

            }else {
                UserNameET.setError("الاسم المستخدم او الرقم السري غير صحيح");
                PasswordET.setError("الاسم المستخدم او الرقم السري غير صحيح");
            }


        }
    }


}
