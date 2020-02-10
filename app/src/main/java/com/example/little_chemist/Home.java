package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Home extends AppCompatActivity {
private ImageView set;
    //private Button button ;
    private CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //the users info
        String UserNameStr= getIntent().getStringExtra("UserName");
        String PasswordStr=getIntent().getStringExtra("Password");

        //static String name = UserNameStr;


        set = findViewById(R.id.settings);
        set.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent n = new Intent(Home.this, Settings.class);
                //n.putExtra("username",UserNameStr);
                startActivity(n);
                //finish();
            }
        });

        cv = findViewById(R.id.cardviewchapters);
       // cv.setOnClickListener();
        //button = findViewById(R.id.cardviewchapters);

        cv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent n = new Intent(Home.this, Chapters.class);
                startActivity(n);
                //finish();
            }
        });

//        Button logout = findViewById(R.id.logoutBtn);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences myPrefs = getSharedPreferences("Activity",
//                        MODE_PRIVATE);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();
//                editor.commit();
//
//                Intent intent = new Intent(Home.this,
//                        LoginPage.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

}
