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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.little_chemist.Home;
import com.example.little_chemist.R;
import com.example.little_chemist.SignUp;
import com.example.little_chemist.ui.login.LoginViewModel;
import com.example.little_chemist.ui.login.LoginViewModelFactory;
public class LoginReal extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);



        final EditText usernameEditText = findViewById(R.id.username);

        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button signupButton = findViewById(R.id.button);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        //String name = usernameEditText.getText().toString();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                Intent b = new Intent(LoginReal.this, Home.class);
                startActivity(b);
                finish();


            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(LoginReal.this, SignUp.class);
                startActivity(n);
                finish();

                //setContentView(R.layout.sign_up);
            }
        });
    }
}
