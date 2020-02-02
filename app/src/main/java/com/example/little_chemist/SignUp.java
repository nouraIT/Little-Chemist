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

public class SignUp extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //SignUp();

        final Button signupButton = findViewById(R.id.button2);

    signupButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

           // EditText ET_Name=(EditText) findViewById(R.id.ET_Name);
            //EditText ET_Email=(EditText) findViewById(R.id.ET_Email);
            EditText ET_UserName= findViewById(R.id.username2);
            EditText ET_Password= findViewById(R.id.password2);
            EditText ET_ConfirmPassword= findViewById(R.id.password22);

           // final EditText username = findViewById(R.id.username2);
           // final EditText password = findViewById(R.id.password2);
           // final EditText passwordConf = findViewById(R.id.password22);

          //  final String un = username.getText().toString();
           // final String pass = password.getText().toString();
            //final String passConf = passwordConf.getText().toString();

          //  String NameStr=ET_Name.getText().toString();
           // String EmailStr=ET_Email.getText().toString();
            String UserNameStr=ET_UserName.getText().toString();
            String PasswordStr=ET_Password.getText().toString();
            String ConfirmPasswordStr=ET_ConfirmPassword.getText().toString();


            if(PasswordStr.equals(null))
            {
                Toast.makeText(getApplicationContext(), "Should not be Null", Toast.LENGTH_SHORT).show();
            }

            if(!PasswordStr.equals(ConfirmPasswordStr)){
                Toast.makeText(getApplicationContext(), "Passwords dont match", Toast.LENGTH_SHORT).show();
            }else {
                //Insert into Database
                User user = new User();
                user.SetUserName(UserNameStr);
                user.SetPassword(PasswordStr);
                helper.InsertUsers(user);


                Intent loginIntent=new Intent(SignUp.this,Home.class);
                //Send Data
                loginIntent.putExtra("UserName",UserNameStr);
                loginIntent.putExtra("Password",PasswordStr);

                startActivity(loginIntent);
                finish();

            }

        }
    });

}
}
    /*
    //final ProgressBar loadingProgressBar = findViewById(R.id.loading);
    AppDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


         db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "LittleChemDB").build();


        final EditText username = findViewById(R.id.username2);
        final EditText password = findViewById(R.id.password2);
        final EditText passwordConf = findViewById(R.id.password22);
        final Button signupButton = findViewById(R.id.button2);

        final String un = username.getText().toString();
        final String pass = password.getText().toString();
        final String passConf = passwordConf.getText().toString();

        //runroom("esraa","1234567");


        /*TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                signupButton.setEnabled(true);

            }
        };

        username.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    return true;
                }
                return false;
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loadingProgressBar.setVisibility(View.VISIBLE);

                Log.i(" test", "hello hi" );

                if(isUserNameValid(un) && isPasswordValid(pass,passConf)){
                    runroom(un,pass);
                    Intent n = new Intent(SignUp.this, Home.class);
                    startActivity(n);
                    finish();
                }

            }
        });


    }

    public void runroom(String un, String pass){
        User us = new User(un , pass);
        db.userDao().insertAll(us);

        Log.i("db test", db.userDao().findByName(un).toString() );

    }
    //public void onChanged(){

    //}

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }

        return true;
        /*
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password, String passwordConf) {
        return password != null && password.trim().length() > 5 && password.equals(passwordConf);
    }



}
*/
