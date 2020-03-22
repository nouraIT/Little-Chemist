package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class character_gallery extends AppCompatActivity {

    CircleImageView face1,face2,face3,face4,face5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_char_gal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        Intent n = new Intent(character_gallery.this, Settings.class);
                        startActivity(n);
                    }
                });
                thread.start();
            }
        });


        face1 = findViewById(R.id.profile_image1);
        face2 = findViewById(R.id.profile_image2);
        face3 = findViewById(R.id.profile_image3);
        face4 = findViewById(R.id.profile_image4);
        face5 = findViewById(R.id.profile_image5);


        face1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(character_gallery.this,Settings.class);
                n.putExtra("faceID",R.drawable.face1);
                n.putExtra("name","img");
                startActivity(n);
                finish();
            }
        });

        face2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(character_gallery.this,Settings.class);
                n.putExtra("faceID",R.drawable.face2);
                n.putExtra("name","img");
                startActivity(n);
                finish();
            }
        });

        face3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(character_gallery.this,Settings.class);
                n.putExtra("faceID",R.drawable.face3);
                n.putExtra("name","img");
                startActivity(n);
                finish();
            }
        });

        face4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(character_gallery.this,Settings.class);
                n.putExtra("faceID",R.drawable.face4);
                n.putExtra("name","img");
                startActivity(n);
                finish();
            }
        });

        face5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(character_gallery.this,Settings.class);
                n.putExtra("faceID",R.drawable.face5);
                n.putExtra("name","img");
                startActivity(n);
                finish();
            }
        });




    }
}
