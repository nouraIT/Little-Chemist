package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.little_chemist.R;

import com.example.little_chemist.SignUp;

import android.os.Bundle;

public class Login extends AppCompatActivity{


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
            //Passing userName
            EditText UserNameET=(EditText) findViewById(R.id.username);
            String UserNameStr=UserNameET.getText().toString();
            //Passing Password
            EditText PasswordET=(EditText)findViewById(R.id.password);
            String PasswordStr=PasswordET.getText().toString();


            String dbPassword=helper.LoginIn(UserNameStr);
            //Send UserName to Database to find it, and return Password
            //To compare it with Current Password from user input
            if(dbPassword.equals(PasswordStr)){
                Intent loginIntent=new Intent(this,Home.class);
                //Send Data
                loginIntent.putExtra("UserName",UserNameStr);
                loginIntent.putExtra("Password",PasswordStr);

                startActivity(loginIntent);
            }else {
                Toast.makeText(this, "UserName and Passwords dont match", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
