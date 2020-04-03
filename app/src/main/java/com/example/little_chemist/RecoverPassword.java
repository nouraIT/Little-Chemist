package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
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

import static java.lang.Integer.parseInt;

public class RecoverPassword extends AppCompatActivity {
    TextInputLayout ET_Password ,ET_ConfirmPassword,SecurityAn,ET_UserName;
    String PasswordStr, ConfirmPasswordStr,SecurityA,UserNameStr;
    Spinner spinner;
    Student student ;
    int spinnerSelected;
    DatabaseHelper helper=new DatabaseHelper(this);
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recover_password);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(RecoverPassword.this, LoginPage.class);
                startActivity(Homepage);
                finish();
            }
        });

        //-------------------------------------------------

        final Button Recover = findViewById(R.id.chngPassBtn);
        spinner = findViewById(R.id.security_questions);
        adapter = ArrayAdapter.createFromResource(this,  R.array.SecurityQs, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinnerSelected = spinner.getSelectedItemPosition();

        ET_Password = findViewById(R.id.newpass);
        ET_UserName = findViewById(R.id.username2);
        ET_ConfirmPassword= findViewById(R.id.confirmpass);
        SecurityAn= findViewById(R.id.Answer);


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

                        Recover.setEnabled(true);
                        Recover.setAlpha(1f);
                    }
                }else {

                    Recover.setEnabled(false);
                    Recover.setAlpha(0.6f);
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
                    Recover.setEnabled(true);
                    Recover.setAlpha(1f);
                }else {
                    Recover.setEnabled(false);
                    Recover.setAlpha(0.6f);
                }

                if(PasswordStr.length()>=6) {
                    ET_Password.setError(null);
                    ET_ConfirmPassword.setError(null);
                    validatePass();
                }
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
                    ET_ConfirmPassword.setError(getText(R.string.passNotMatch));
                    //return false;
                }else
                    ET_ConfirmPassword.setError(null);


            }

            @Override
            public void afterTextChanged(Editable editable) {
                ConfirmPasswordStr = ET_ConfirmPassword.getEditText().getText().toString().trim();
                if (validateN()){
                    Recover.setEnabled(true);
                    Recover.setAlpha(1f);
                }else {
                    Recover.setEnabled(false);
                    Recover.setAlpha(0.6f);
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
                spinnerSelected = spinner.getSelectedItemPosition();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                spinnerSelected = spinner.getSelectedItemPosition();
                SecurityA = SecurityAn.getEditText().getText().toString().trim().toLowerCase();
                validateSecurity();


            }

            @Override
            public void afterTextChanged(Editable editable) {
                spinnerSelected = spinner.getSelectedItemPosition();
                SecurityA = SecurityAn.getEditText().getText().toString().trim().toLowerCase();
                if (validateSecurity()){

                    Recover.setEnabled(true);
                    Recover.setAlpha(1f);
                }else {
                    Recover.setEnabled(false);
                    Recover.setAlpha(0.6f);
                }


            }
        });



        //-------------------------------------------------


        Recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecurityAn.setError(null);

                SecurityA=SecurityAn.getEditText().getText().toString().trim().toLowerCase();
                PasswordStr=ET_Password.getEditText().getText().toString().trim();
                ConfirmPasswordStr=ET_ConfirmPassword.getEditText().getText().toString().trim();
                UserNameStr=ET_UserName.getEditText().getText().toString().trim();

            if(validateSecurity() && validatePass() && validateN())
            {

                //Send UserName to Database to find it, and return Password
                String [] question= helper.checkquestion(UserNameStr);

                int q = parseInt(question[0]);
                if(!(q == spinnerSelected))

                    SecurityAn.setError(getText(R.string.SecurityQwrong));
                else
                    if(!question[1].equals(SecurityA))
                        SecurityAn.setError(getText(R.string.SecurityAwrong));
                    else {
                        //TODO save the new pass
                        helper.recoverPassword(PasswordStr, UserNameStr);
                        Intent loginIntent=new Intent(RecoverPassword.this,Home.class);
                        startActivity(loginIntent);
                        finish();

                    }
            }
            }
        });
    }


    private Boolean validateSecurity(){

        if (SecurityA.isEmpty()) {
            SecurityAn.setError(getText(R.string.nullSecAnswer));
            return false;
        }
        if (spinnerSelected ==-1) {
            SecurityAn.setError(getText(R.string.nullSecQuestion));
            return false;
        }

        return true;


    }

    private Boolean validatePass() {

        if (PasswordStr.isEmpty()) {
            ET_Password.setError(getText(R.string.null_password));
            return false;
        } else if (!(PasswordStr.length() > 5)) {
            ET_Password.setError(getText(R.string.shortPass));
            return false;
        } else if(!ConfirmPasswordStr.equals(PasswordStr)) {
            ET_ConfirmPassword.setError(getText(R.string.passNotMatch));
            return false;
        }

        ET_Password.setError(null);
        ET_ConfirmPassword.setError(null);
        return true;
    }

    private boolean validateN(){
        if (UserNameStr.isEmpty() || PasswordStr.isEmpty() || ConfirmPasswordStr.isEmpty() || SecurityA.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validateExistence(){
        if (!helper.usernameExist(UserNameStr)) {
            ET_UserName.setError(getText(R.string.nameNotExist));
            return false;
        }else
            ET_UserName.setError(null);
        return true;
    }

    private Boolean validateName(){

        if (UserNameStr.isEmpty()) {
            ET_UserName.setError(getText(R.string.null_username));
            return false;
        } else
            ET_UserName.setError(null);
        return true;

    }
}

