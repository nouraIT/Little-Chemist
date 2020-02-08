package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.little_chemist.Tables.Student;
import com.google.android.material.textfield.TextInputLayout;


public class LoginPage extends AppCompatActivity{

        Student student = new Student();
        DatabaseHelper helper=new DatabaseHelper(this);
        TextInputLayout UserNameET, PasswordET;
        String UserNameStr,PasswordStr;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            Button Recover = findViewById(R.id.button8);
            Recover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent recover=new Intent(LoginPage.this,RecoverPassword.class);
                    startActivity(recover);
                }
            });



           // UserNameET.setError(null);
        }

    public void onBtnSignUpClick(View v){
        if(v.getId()==R.id.button){
            Intent SignUpIntent=new Intent(this,SignUp.class);
            startActivity(SignUpIntent);
        }
    }
        public  void onBtnLoginInClick(View v){


            if(v.getId()==R.id.login){

                UserNameET= findViewById(R.id.username);
                UserNameStr=UserNameET.getEditText().getText().toString().trim();
                //Passing Password
                PasswordET= findViewById(R.id.password);
                PasswordStr=PasswordET.getEditText().getText().toString().trim();

               // UserNameET.setError(null);

            if(UserNameStr.isEmpty()) {
                UserNameET.setError(getText(R.string.Error1));
                return;
            }

            if(PasswordStr.isEmpty()){
                PasswordET.setError(getText(R.string.Error2));
                return;
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
                UserNameET.setError(getText(R.string.Error3));
                PasswordET.setError(getText(R.string.Error3));
            }


        }
    }


}
