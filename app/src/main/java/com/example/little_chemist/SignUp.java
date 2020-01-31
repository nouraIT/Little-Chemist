package com.example.little_chemist;
import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.little_chemist.ui.login.Login;
import com.example.little_chemist.ui.login.LoginViewModel;
import com.example.little_chemist.ui.login.LoginViewModelFactory;
import android.os.Bundle;

public class SignUp extends AppCompatActivity {
    final ProgressBar loadingProgressBar = findViewById(R.id.loading);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        final EditText username = findViewById(R.id.username2);
        final EditText password = findViewById(R.id.password2);
        final EditText passwordConf = findViewById(R.id.password22);
        final Button signupButton = findViewById(R.id.button2);

        final String un = username.getText().toString();
        final String pass = password.getText().toString();
        final String passConf = passwordConf.getText().toString();


        TextWatcher afterTextChangedListener = new TextWatcher() {
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
                loadingProgressBar.setVisibility(View.VISIBLE);
                if(isUserNameValid(un) && isPasswordValid(pass,passConf)){
                    setContentView(R.layout.activity_home);
                }

            }
        });


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
        }*/
    }

    private boolean isPasswordValid(String password, String passwordConf) {
        return password != null && password.trim().length() > 5 && password.equals(passwordConf);
    }



}