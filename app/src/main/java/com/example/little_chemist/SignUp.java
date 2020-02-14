package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.little_chemist.Tables.Student;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);
    int pos;

    TextInputLayout ET_UserName ,ET_Password ,ET_ConfirmPassword,SecurityAn;

    String UserNameStr, PasswordStr, ConfirmPasswordStr,SecurityA,spinnerSelected;
     Spinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        final Button signupButton = findViewById(R.id.button2);

        spinner = (Spinner) findViewById(R.id.security_questions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SecurityQs, R.layout.spinner_text);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        ET_UserName = findViewById(R.id.username2);
        ET_Password = findViewById(R.id.password2);
        ET_ConfirmPassword= findViewById(R.id.password22);
        SecurityAn= findViewById(R.id.securityA);



        UserNameStr=ET_UserName.getEditText().getText().toString().trim();
        PasswordStr=ET_Password.getEditText().getText().toString().trim();
        ConfirmPasswordStr=ET_ConfirmPassword.getEditText().getText().toString().trim();
        SecurityA=SecurityAn.getEditText().getText().toString().trim().toLowerCase();



        //-------------------------------------------------

        ET_UserName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                UserNameStr = ET_UserName.getEditText().getText().toString().trim();
                if(validateName())
                    validateExistence();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                UserNameStr = ET_UserName.getEditText().getText().toString().trim();
                if (validateN()){
                    if(validateName() && validateExistence()) {

                        signupButton.setEnabled(true);
                        signupButton.setAlpha(1f);
                    }
                }else {

                    signupButton.setEnabled(false);
                    signupButton.setAlpha(0.6f);
                }


            }
        });

        //-------------------------------------------------

        ET_Password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                PasswordStr = ET_Password.getEditText().getText().toString().trim();
                    validatePass();
//

            }

            @Override
            public void afterTextChanged(Editable editable) {
                PasswordStr = ET_Password.getEditText().getText().toString().trim();
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

        ET_ConfirmPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ConfirmPasswordStr = ET_ConfirmPassword.getEditText().getText().toString().trim();
                if (!PasswordStr.equals(ConfirmPasswordStr)) {
                    ET_Password.setError(getText(R.string.passNotMatch));
                    //return false;
                }else
                    ET_Password.setError(null);


            }

            @Override
            public void afterTextChanged(Editable editable) {
                ConfirmPasswordStr = ET_ConfirmPassword.getEditText().getText().toString().trim();
                if (validateN()){
                    signupButton.setEnabled(true);
                    signupButton.setAlpha(1f);
                }else {
                    signupButton.setEnabled(false);
                    signupButton.setAlpha(0.6f);
                }

                if (!PasswordStr.equals(ConfirmPasswordStr)) {
                    ET_Password.setError(getText(R.string.passNotMatch));
                    //return false;
                }else
                    ET_Password.setError(null);
            }
        });


        //-------------------------------------------------

        SecurityAn.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pos = spinner.getSelectedItemPosition();
                SecurityAn.setError(null);


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pos = spinner.getSelectedItemPosition();

                spinnerSelected = spinner.getSelectedItem().toString();
                SecurityA = SecurityAn.getEditText().getText().toString().trim().toLowerCase();
                validateSecurity();


            }

            @Override
            public void afterTextChanged(Editable editable) {
                pos = spinner.getSelectedItemPosition();

                spinnerSelected = spinner.getSelectedItem().toString();
                SecurityA = SecurityAn.getEditText().getText().toString().trim().toLowerCase();
                if (validateSecurity()){

                    signupButton.setEnabled(true);
                    signupButton.setAlpha(1f);
                }else {
                    signupButton.setEnabled(false);
                    signupButton.setAlpha(0.6f);
                }


            }
        });


        //-------------------------------------------------


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //System.out.println("heeeeeeeeeeeeeeeeere "+pos);
                spinnerSelected = spinner.getSelectedItem().toString();

                pos = spinner.getSelectedItemPosition();

                UserNameStr=ET_UserName.getEditText().getText().toString().trim();
                PasswordStr=ET_Password.getEditText().getText().toString().trim();
                ConfirmPasswordStr=ET_ConfirmPassword.getEditText().getText().toString().trim();
                SecurityA=SecurityAn.getEditText().getText().toString().trim().toLowerCase();


                //-------------------------------------------------


                if(validateN() && validateName() && validatePass() && validateSecurity() && validateExistence()) {
                    //Insert into Database
                    Student student = new Student(UserNameStr,PasswordStr,0);
                    //student.SetUserName(UserNameStr);
                    //student.SetPassword(PasswordStr);

                    student.SetSecQ(String.valueOf(pos));
                    student.SetSecA(SecurityA);
                    helper.InsertStudents(student);


                    Intent loginIntent=new Intent(SignUp.this,Home.class);
                    //Send Data
                    loginIntent.putExtra("student",student);
                    //loginIntent.putExtra("Password",PasswordStr);

                    startActivity(loginIntent);
                    finish();

                }

            }//on click
        });

    }



    private boolean validateN(){
        if (UserNameStr.isEmpty() || PasswordStr.isEmpty() || ConfirmPasswordStr.isEmpty() || SecurityA.isEmpty()) {
           // ET_UserName.setError(getText(R.string.null_username));
            //ET_Password.setError(getText(R.string.null_password));
           // ET_ConfirmPassword.setError(getText(R.string.null_password));
            //SecurityAn.setError(getText(R.string.nullSecAnswer));
            return false;
        }
        return true;
    }

    private Boolean validateSecurity(){

        if (SecurityA.isEmpty()) {
            SecurityAn.setError(getText(R.string.nullSecAnswer));
            return false;
        }
        if (spinnerSelected.isEmpty()) {
            SecurityAn.setError(getText(R.string.nullSecQuestion));
            return false;
        }
        if (SecurityA.length() >= 3 ) {
            SecurityAn.setError(null);
            return true;

        }else {
            SecurityAn.setError(getText(R.string.shortAnswer));
            return false;
        }

    }


    private boolean validateExistence(){
        if (helper.usernameExist(UserNameStr)) {
            ET_UserName.setError(getText(R.string.nameExist));
            return false;
        }else
            ET_UserName.setError(null);
        return true;
    }

    private Boolean validateName(){

        if (UserNameStr.isEmpty()) {
            ET_UserName.setError(getText(R.string.null_username));
            return false;
        }else if (UserNameStr.length() < 5 ){
            ET_UserName.setError(getText(R.string.shortName));
            return false;
        }
        else
            ET_UserName.setError(null);
        return true;

    }

    private Boolean validatePass() {

        if (PasswordStr.isEmpty()) {
            ET_Password.setError(getText(R.string.null_password));
            return false;
        } else if (PasswordStr.length() < 6) {
            ET_Password.setError(getText(R.string.shortPass));
            return false;
        } else
            ET_Password.setError(null);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//
//        switch(item.getItemId()){
//            case android.R.:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


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
