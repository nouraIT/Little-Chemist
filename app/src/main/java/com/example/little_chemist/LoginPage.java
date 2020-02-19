package com.example.little_chemist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.little_chemist.Tables.Student;
import com.google.android.material.textfield.TextInputLayout;
//import com.scwang.wave.MultiWaveHeader;

import java.io.Serializable;


public class LoginPage extends AppCompatActivity implements Serializable {

        Student student = new Student();
       DatabaseHelper helper=new DatabaseHelper(this);
        TextInputLayout UserNameET, PasswordET;
        String UserNameStr,PasswordStr;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.activity_login);
            Button Recover = findViewById(R.id.forgotPassBtn);
            Recover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Thread thread = new Thread(new Runnable(){
                       @Override
                       public void run(){
                            Intent recover=new Intent(LoginPage.this,RecoverPassword.class);
                            startActivity(recover);
                        }
                   });
                   thread.start();

                }
            });

            final Button loginButton = findViewById(R.id.login);
            UserNameET= findViewById(R.id.userName);
            PasswordET= findViewById(R.id.password);

            //UserNameET.setError(null);

            //_____________________________________
            UserNameET.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    UserNameET.setError(null);

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    UserNameET.setError(null);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    UserNameStr = UserNameET.getEditText().getText().toString().trim();
                    if (!UserNameStr.isEmpty()){
                        loginButton.setEnabled(true);
                        loginButton.setAlpha(1f);
                    }else {
                        UserNameET.setError(getText(R.string.null_username));
                        loginButton.setEnabled(false);
                        loginButton.setAlpha(0.6f);
                    }


                }
            });


            PasswordET.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    PasswordET.setError(null);
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    PasswordET.setError(null);

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    PasswordStr = PasswordET.getEditText().getText().toString().trim();
                    if(!PasswordStr.isEmpty()){
                        loginButton.setEnabled(true);
                        loginButton.setAlpha(1f);
                    }else {
                        PasswordET.setError(getText(R.string.null_password));
                        loginButton.setEnabled(false);
                        loginButton.setAlpha(0.6f);
                    }


                }
            });

            //____________________________________


            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserNameStr=UserNameET.getEditText().getText().toString().trim();
                    //Passing Password

                    PasswordStr=PasswordET.getEditText().getText().toString().trim();

                    //UserNameET.setError(null);
                    //PasswordET.setError(null);


                   String dbPassword= helper.checkPassword(UserNameStr);
                    //To compare it with Current Password from user input
                    if(dbPassword.equals(PasswordStr)){

                        student.SetUserName(UserNameStr);
                        student.SetPassword(PasswordStr);

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();

                        editor.putString("username", UserNameStr); // Storing string
                        editor.putString("password", PasswordStr); // Storing string


                        editor.commit();




                        Intent loginIntent=new Intent(LoginPage.this,Home.class);
                        //Send Data
                        //loginIntent.stu
                        //loginIntent.putExtra("student", student);
                        //loginIntent.putExtra("Password",PasswordStr);
                        //loginIntent.putExtra("Welcome",)
//                        String welcome = getString(R.string.welcome) +" "+ UserNameStr ;
//                        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

                        startActivity(loginIntent);

                    }else {
                        UserNameET.setError(getText(R.string.IncorrectAccount));
                        PasswordET.setError(getText(R.string.IncorrectAccount));
                    }


                }//on click
            });

        }//on create

    public void onBtnSignUpClick(View v){
        UserNameET.setError(null);
        PasswordET.setError(null);
        if(v.getId()==R.id.signupBtn){
            Intent SignUpIntent=new Intent(this,SignUp.class);
            startActivity(SignUpIntent);
        }
    }



//
//        public  void onBtnLoginInClick(View v){
//
//
//            if(v.getId()==R.id.login){
//
//                UserNameET= findViewById(R.id.username);
//                UserNameStr=UserNameET.getEditText().getText().toString().trim();
//                //Passing Password
//                PasswordET= findViewById(R.id.password);
//                PasswordStr=PasswordET.getEditText().getText().toString().trim();
//
//               // UserNameET.setError(null);
//
//            if(UserNameStr.isEmpty()) {
//                UserNameET.setError(getText(R.string.null_username));
//                return;
//            }
//
//            if(PasswordStr.isEmpty()){
//                PasswordET.setError(getText(R.string.null_password));
//                return;
//            }
//
//
//            //Send UserName to Database to find it, and return Password
//            String dbPassword= helper.checkPassword(UserNameStr);
//            //To compare it with Current Password from user input
//            if(dbPassword.equals(PasswordStr)){
//                Intent loginIntent=new Intent(this,Home.class);
//                //Send Data
//                loginIntent.putExtra("UserName",UserNameStr);
//                loginIntent.putExtra("Password",PasswordStr);
//                //loginIntent.putExtra("Welcome",)
//                String welcome = getString(R.string.welcome) +" "+ UserNameStr ;
//                Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
//
//                startActivity(loginIntent);
//
//            }else {
//                UserNameET.setError(getText(R.string.IncorrectAccount));
//                PasswordET.setError(getText(R.string.IncorrectAccount));
//            }
//
//
//        }
//    }// on btn login




}
