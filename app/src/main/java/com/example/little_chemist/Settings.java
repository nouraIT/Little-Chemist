package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

import android.app.Activity;
import android.content.res.Configuration;
import android.widget.Button;
import android.widget.TextView;

import com.example.little_chemist.Tables.Student;


public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCustom);

        //setSupportActionBar(toolbar);
        //final String UserNameStr= getIntent().getStringExtra("UserName");
        Student student = (Student) getIntent().getSerializableExtra("student");
        DatabaseHelper loginData;

        loginData=new DatabaseHelper(this);
        try {
            loginData=loginData.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TextView profileName = findViewById(R.id.profileName);
        //String userName = loginData.getLoggedInStudent("UserName");
        //String user = loginData.getLoggedInStudent(userName);
//        static String name;
//        name = student.GetUserName();

        profileName.setText(student.GetUserName());



        Toolbar toolbar = findViewById(R.id.toolbar);

//        ColorFilter colorf = new ColorFilter(R.color.white);
//
//        toolbar.getNavigationIcon().setColorFilter(R.color.white);


        Button booklet = findViewById(R.id.button5);
        Button En = findViewById(R.id.button3);
        Button Ara = findViewById(R.id.button4);
        CardView Delete = findViewById(R.id.deleteBtn);

        setSupportActionBar(toolbar);
        //TextView textView = (TextView)toolbar.findViewById(R.id.toolbarTextView);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(Settings.this, Home.class);
                startActivity(Homepage);
                //finish();
            }
        });
        En.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();


            }
        });
        Ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("ar");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
                String langPref = "Language";
                SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(langPref, "ar");
                editor.commit();

            }
        });



        Button logout = findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(Settings.this)
                        .setTitle(getText(R.string.log_out))
                        .setMessage(getText(R.string.confirmLogginOut))
                        .setPositiveButton(getText(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences myPrefs = getSharedPreferences("Activity",
                                        MODE_PRIVATE);
                                SharedPreferences.Editor editor = myPrefs.edit();
                                editor.clear();
                                editor.commit();

                                Intent intent = new Intent(Settings.this,
                                        LoginPage.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(getText(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // user doesn't want to logout
//
                            }
                        })
                        .show();

            }
        });


        booklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Direct to booklet
            }
        });


        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Delete account

                new AlertDialog.Builder(Settings.this)
                        .setTitle(getText(R.string.delete_account))
                        .setMessage(getText(R.string.confirmDeleteAcc))
                        .setPositiveButton(getText(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // user want to delete


//                                Intent intent = new Intent(Settings.this,
//                                        LoginPage.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);
//                                finish();
                            }
                        })
                        .setNegativeButton(getText(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // user doesn't want to delete
//
                            }
                        })
                        .show();
            }
        });

    }
}
