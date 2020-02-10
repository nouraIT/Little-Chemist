package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.little_chemist.Tables.Student;
import com.google.android.material.textfield.TextInputLayout;

public class RecoverPassword extends AppCompatActivity {
    TextInputLayout ET_Password ,ET_ConfirmPassword,SecurityAn,ET_UserName;
    String PasswordStr, ConfirmPasswordStr,SecurityA,spinnerSelected,UserNameStr;
    Spinner spinner;
    Student student = new Student();
    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        final Button Recover = findViewById(R.id.chngPassBtn);
        spinner = (Spinner) findViewById(R.id.security_questions);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SecurityQs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinnerSelected = spinner.getSelectedItem().toString();



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
                spinnerSelected = spinner.getSelectedItem().toString();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                spinnerSelected = spinner.getSelectedItem().toString();
                SecurityA = SecurityAn.getEditText().getText().toString().trim();
                validateSecurity();


            }

            @Override
            public void afterTextChanged(Editable editable) {
                spinnerSelected = spinner.getSelectedItem().toString();
                SecurityA = SecurityAn.getEditText().getText().toString().trim();
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

                SecurityA=SecurityAn.getEditText().getText().toString().trim();
                PasswordStr=ET_Password.getEditText().getText().toString().trim();
                ConfirmPasswordStr=ET_ConfirmPassword.getEditText().getText().toString().trim();
                UserNameStr=ET_UserName.getEditText().getText().toString().trim();

            if(validateSecurity() && validatePass() && validateN())
            {

                //Send UserName to Database to find it, and return Password
                String [] question= helper.checkquestion(UserNameStr);

                if(!question[0].equals(spinnerSelected))
                    //TODO make the error under the spinner
                    SecurityAn.setError(getText(R.string.SecurityQwrong));
                else
                    if(!question[1].equals(SecurityA))
                        SecurityAn.setError(getText(R.string.SecurityAwrong));
                    else {
                        helper.recoverPassword(PasswordStr, UserNameStr);
                        Intent loginIntent=new Intent(RecoverPassword.this,Home.class);

                        startActivity(loginIntent);
                        finish();

                    }
            }
            }
        });
    }





    /*private void CheckData(){
        String question= helper.checkPassword(UserNameStr);
        //To compare it with Current Password from user input
        if(dbPassword.equals(PasswordStr)){


        }else {

        }

    }*/

    private Boolean validateSecurity(){

        if (SecurityA.isEmpty()) {
            SecurityAn.setError(getText(R.string.nullSecAnswer));
            return false;
        }
        if (spinnerSelected.isEmpty()) {
            SecurityAn.setError(getText(R.string.nullSecQuestion));
            return false;
        }

        return true;


    }
    private Boolean validatePass() {

        if (PasswordStr.isEmpty()) {
            ET_Password.setError(getText(R.string.null_password));
            return false;
        } else if (PasswordStr.length() < 6) {
            ET_Password.setError(getText(R.string.shortPass));
            return false;
        }
        else
            ET_Password.setError(null);

        return true;

    }

    private boolean validateN(){
        if (UserNameStr.isEmpty() || PasswordStr.isEmpty() || ConfirmPasswordStr.isEmpty() || SecurityA.isEmpty()) {
            //ET_UserName.setError(getText(R.string.null_username));
            //ET_Password.setError(getText(R.string.null_password));
            //ET_ConfirmPassword.setError(getText(R.string.null_password));
            //SecurityAn.setError(getText(R.string.nullSecAnswer));
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

