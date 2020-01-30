package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



public class Home extends AppCompatActivity {
private ImageView set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
