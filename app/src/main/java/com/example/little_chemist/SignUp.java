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

import com.example.little_chemist.R;
import com.example.little_chemist.SignUp;
import com.example.little_chemist.ui.login.LoginViewModel;
import com.example.little_chemist.ui.login.LoginViewModelFactory;
import android.os.Bundle;

public class SignUp extends AppCompatActivity {
    final Button signup = findViewById(R.id.button2);
    final EditText usernameEditText = findViewById(R.id.username);
    final EditText passwordEditText = findViewById(R.id.password);
    final ProgressBar loadingProgressBar = findViewById(R.id.loading);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }
}