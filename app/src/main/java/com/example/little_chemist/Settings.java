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
import android.widget.EditText;
import android.widget.TextView;

import com.example.little_chemist.Tables.Student;


public class Settings extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor ;
    DatabaseHelper helper = new DatabaseHelper(Settings.this);
    boolean arabicFlag ;


//    public void onResume(){
//        super.onResume();
//        //setContentView(R.layout.activity_settings);
////        String name = pref.getString("username", null);
////        System.out.println("name is hrnejknfjrfj "+name);
//
//        //pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//        //editor = pref.edit();
//        //boolean arabicFlag = pref.getBoolean("arabic",false);
//
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCustom);

        //setSupportActionBar(toolbar);
        //final String UserNameStr= getIntent().getStringExtra("UserName");
        Student student = (Student) getIntent().getSerializableExtra("student");
//        DatabaseHelper loginData;
//
//        loginData=new DatabaseHelper(this);
//        try {
//            loginData=loginData.open();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }



        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        String name = pref.getString("username", null); // getting String
        //pref.getInt("password", -1); // getting Integer
        //arabicFlag = helper.checkLang(name);
        String pass = pref.getString("password",null);

        String studentId=helper.getStudentId(name);

        //Log.v("00000000000000",studentId);

        TextView profileName = findViewById(R.id.profileName);
        //String userName = loginData.getLoggedInStudent("UserName");
        //String user = loginData.getLoggedInStudent(userName);
//        static String name;
//        name = student.GetUserName();

        profileName.setText(name);



        Toolbar toolbar = findViewById(R.id.toolbar);

//        ColorFilter colorf = new ColorFilter(R.color.white);
//
//        toolbar.getNavigationIcon().setColorFilter(R.color.white);


        Button booklet = findViewById(R.id.button5);
        Button En = findViewById(R.id.enBtn);
        Button Ara = findViewById(R.id.arBtn);
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

                editor.putBoolean("arabic",false);
                helper.changeLang(name,0);
                editor.commit();


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

//                String langPref = "Language";
//                SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString(langPref, "ar");
//                editor.commit();
                editor.putBoolean("arabic",true);
                helper.changeLang(name,1);
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
                                Home.alreadyRecreated = false;
                                Home.AlreadyGreeted = false;
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
                AlertDialog.Builder alert = new AlertDialog.Builder(Settings.this);
                final EditText edittext = new EditText(Settings.this);
                alert.setMessage(getText(R.string.confirmDeleteAcc));
                alert.setTitle(getText(R.string.delete_account));
                alert.setView(edittext);
                alert.setPositiveButton(getText(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String password = edittext.getText().toString();
                        if (password.equals(pass)){
                            helper.DeleteStudent(studentId);
                            Intent intent = new Intent(Settings.this,
                                    LoginPage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }

                        else{
                            //error masg
                            //TODO
                        }
                    }
                });

                alert.setNegativeButton(getText(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();

                /*new AlertDialog.Builder(Settings.this)
                        .setTitle(getText(R.string.delete_account))
                        .setMessage(getText(R.string.confirmDeleteAcc))
                        //.setView(edittext)
                        .setPositiveButton(getText(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // user want to delete
                                //String password = edittext.getText().toString();
                                //if (password.equals()){

                                //}

                                helper.DeleteStudent(studentId);

                                Intent intent = new Intent(Settings.this,
                                        LoginPage.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(getText(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // user doesn't want to delete
//
                            }
                        })
                        .show();*/
            }
        });

    }
}
