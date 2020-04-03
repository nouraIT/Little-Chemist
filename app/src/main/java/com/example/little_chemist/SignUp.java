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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.little_chemist.Tables.Student;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);
    int pos;

    TextInputLayout ET_UserName ,ET_Password ,ET_ConfirmPassword,SecurityAn;
    Button signupButton , backLogin;
    String UserNameStr, PasswordStr, ConfirmPasswordStr,SecurityA,spinnerSelected;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sign_up);


        signupButton = findViewById(R.id.signup);
        backLogin = findViewById(R.id.backToLogin);

        spinner = findViewById(R.id.security_questions);
        adapter = ArrayAdapter.createFromResource(this, R.array.SecurityQs, R.layout.spinner_text);
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

                    student.SetSecQ(String.valueOf(pos));
                    student.SetSecA(SecurityA);
                    helper.InsertStudents(student);

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("username", UserNameStr); // Storing string
                    editor.putString("password", PasswordStr); // Storing string


                    editor.apply();

                    Intent loginIntent=new Intent(SignUp.this,Home.class);
                    startActivity(loginIntent);
                    finish();

                }

            }//on click
        });

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(SignUp.this,LoginPage.class);
                startActivity(n);
                finish();
            }
        });
    }



    private boolean validateN(){
        if (UserNameStr.isEmpty() || PasswordStr.isEmpty() || ConfirmPasswordStr.isEmpty() || SecurityA.isEmpty()) {
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
        } else if(!ConfirmPasswordStr.equals(PasswordStr)) {
            ET_ConfirmPassword.setError(getText(R.string.passNotMatch));
            return false;
        } else
            ET_Password.setError(null);
        return true;
    }


}