package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.little_chemist.Tables.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);


    TextInputEditText ET_UserName ,ET_Password ,ET_ConfirmPassword;

    String UserNameStr, PasswordStr, ConfirmPasswordStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //SignUp();

        final Button signupButton = findViewById(R.id.button2);



    signupButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            ET_UserName = findViewById(R.id.username2);
            ET_Password = findViewById(R.id.password2);
            ET_ConfirmPassword= findViewById(R.id.password22);

            UserNameStr=ET_UserName.getText().toString().trim();
            PasswordStr=ET_Password.getText().toString().trim();
            ConfirmPasswordStr=ET_ConfirmPassword.getText().toString().trim();


           // EditText ET_Name=(EditText) findViewById(R.id.ET_Name);
            //EditText ET_Email=(EditText) findViewById(R.id.ET_Email);


           // final EditText username = findViewById(R.id.username2);
           // final EditText password = findViewById(R.id.password2);
           // final EditText passwordConf = findViewById(R.id.password22);

          //  final String un = username.getText().toString();
           // final String pass = password.getText().toString();
            //final String passConf = passwordConf.getText().toString();

          //  String NameStr=ET_Name.getText().toString();
           // String EmailStr=ET_Email.getText().toString();


            //--------------------------------------------------
            //username



            if(UserNameStr.length()>=3)
                validateName();

            ET_UserName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    UserNameStr = ET_UserName.getText().toString().trim();
                    if(UserNameStr.length()>=3)
                        validateName();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    UserNameStr = ET_UserName.getText().toString().trim();
                    if (validateN()){

                        signupButton.setEnabled(true);
                        signupButton.setAlpha(1f);
                    }else {
                        signupButton.setEnabled(false);
                        signupButton.setAlpha(0.6f);
                    }

                    if(UserNameStr.length()>=3)
                        validateName();
                }
            });

            //-------------------------------------------------

            ET_Password.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    PasswordStr = ET_Password.getText().toString().trim();
                    if(PasswordStr.length()>=7)
                        validatePass();

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    PasswordStr = ET_Password.getText().toString().trim();
                    if (validateN()){
                        signupButton.setEnabled(true);
                        signupButton.setAlpha(1f);
                    }else {
                        signupButton.setEnabled(false);
                        signupButton.setAlpha(0.6f);
                    }

                    if(PasswordStr.length()>=6)
                        validatePass();
                }
            });




            //-------------------------------------------------


            if(validateN() && validateName() && validatePass()) {
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



    private boolean validateN(){
        if (UserNameStr.isEmpty() || PasswordStr.isEmpty() || ConfirmPasswordStr.isEmpty()) {
            return false;
        }
        return true;
    }
    //TODO add the messages to the string thing
    //TODO we have to check the database for if the username is used

    private Boolean validateName(){

        if (UserNameStr.isEmpty()) {
            ET_UserName.setError("الرجاء ادخال اسم المستخدم");
            return false;
        }
        if (UserNameStr.length() >= 3 ) {
            ET_UserName.setError(null);
            return true;

        }else {
            ET_UserName.setError("أدخل اسم مستخدم مكون من 3 خانات أو اكثر");
            return false;
        }

    }
    //todo add the messages to the string thing
    private Boolean validatePass() {

        if (PasswordStr.isEmpty()) {
            ET_Password.setError("الرجاء ادخال كلمة المرور");
            return false;
        }

        else if (PasswordStr.length() < 6) {
            ET_Password.setError("أدخل كلمة مرور من 6 خانات أو اكثر");
            return false;
        }
        else if (!PasswordStr.equals(ConfirmPasswordStr)) {
            ET_Password.setError("كلمه المرور لا تطابق");
            return false;
        }
        else
            ET_Password.setError(null);


        return true;
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
