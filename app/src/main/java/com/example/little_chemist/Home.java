package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class Home extends AppCompatActivity {
private ImageView set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //the users info
        String UserNameStr=getIntent().getStringExtra("UserName");
        String PasswordStr=getIntent().getStringExtra("Password");

        String welcome = getString(R.string.welcome) + UserNameStr ;
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

        set = findViewById(R.id.settings);
        set.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent n = new Intent(Home.this, Settings.class);
                startActivity(n);
                finish();
            }
        });

    }

}
