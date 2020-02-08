package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.little_chemist.Tables.Student;
import com.google.android.material.textfield.TextInputLayout;

public class RecoverPassword extends AppCompatActivity {
    TextInputLayout ET_Password ,ET_ConfirmPassword,SecurityAn;
    String PasswordStr, ConfirmPasswordStr,SecurityA,spinnerSelected;
    Spinner spinner;
    Student student = new Student();
    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        final Button Recover = findViewById(R.id.button2);
        spinner = (Spinner) findViewById(R.id.security_questions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SecurityQs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinnerSelected = spinner.getSelectedItem().toString();
        ET_Password = findViewById(R.id.newpass);
        ET_ConfirmPassword= findViewById(R.id.confirmpass);
        SecurityAn= findViewById(R.id.Answer);
        SecurityA=SecurityAn.getEditText().getText().toString().trim();
        PasswordStr=ET_Password.getEditText().getText().toString().trim();
        ConfirmPasswordStr=ET_ConfirmPassword.getEditText().getText().toString().trim();

        Recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(validateSecurity() && validatePass())
            {
                //Send UserName to Database to find it, and return Password
                String [] question= helper.checkquestion(student.GetUserName());
                if(!question[0].equals(spinnerSelected))
                    SecurityAn.setError(getText(R.string.SecurityQwrong));
                else
                    if(!!question[1].equals(SecurityA))
                        SecurityAn.setError(getText(R.string.SecurityAwrong));
                    else
                        helper.recoverPassword(PasswordStr,student.GetUserName());


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
            SecurityAn.setError(getText(R.string.SecError2));
            return false;
        }
        if (spinnerSelected.isEmpty()) {
            SecurityAn.setError(getText(R.string.SecError1));
            return false;
        }
        if (SecurityA.length() >= 3 ) {
            SecurityAn.setError(null);
            return true;

        }else {
            SecurityAn.setError(getText(R.string.SecError3));
            return false;
        }

    }
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

