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
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidviewhover.BlurLayout;
import com.example.little_chemist.Tables.Student;

import de.hdodenhof.circleimageview.CircleImageView;


public class Settings extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor ;
    DatabaseHelper helper = new DatabaseHelper(Settings.this);
    CircleImageView change;
    Student student ;
    Button booklet,En,Ara, logout;
    CardView Delete;
    int img =-1;
    String Intentname = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        //============================= Student =============================

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        String name = pref.getString("username", null); // getting String
        String pass = pref.getString("password",null);
        String studentId=helper.getStudentId(name);
        student = helper.getStudent(name);
        TextView profileName = findViewById(R.id.profileName);
        profileName.setText(name.toUpperCase());

        //============================= Student =============================


        //============================= Toolbar =============================

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
                        Intent Homepage = new Intent(Settings.this, Home.class);
                        startActivity(Homepage);
                    }
                });
                thread.start();
            }
        });

        //============================= Toolbar =============================


        //============================= Initializing =============================

        booklet = findViewById(R.id.button5);
        En = findViewById(R.id.enBtn);
        Ara = findViewById(R.id.arBtn);
        Delete = findViewById(R.id.deleteBtn);
        change = findViewById(R.id.profile_image);
        logout = findViewById(R.id.logoutBtn);
        Bundle bundle = getIntent().getExtras();

        //============================= Initializing =============================


        //============================= Student profile img =============================

        assert bundle.getString("name") != null;
        Intentname = bundle.getString("name");

        assert Intentname != null;
        if(Intentname.equals("img")) {
            img = pref.getInt("faceID", 0);
            helper.changeImg(student.GetUserName(),img);
            changeImage(img);
        }else
            changeImage(student.GetImageId());

        BlurLayout sampleLayout = findViewById(R.id.sample);
        View hover = LayoutInflater.from(Settings.this).inflate(R.layout.hover, null);
        sampleLayout.setHoverView(hover);
        TextView change_msg = hover.findViewById(R.id.description);

        change_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent characterGallery = new Intent(Settings.this, character_gallery.class);
                startActivity(characterGallery);
            }
        });

        //============================= Student profile img =============================



        //============================= Languages =============================

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
                editor.apply();


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


        //============================= Languages =============================



        //============================= Logout and Delete =============================


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

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(Settings.this);
                AlertDialog.Builder alert2 = new AlertDialog.Builder(Settings.this);

                final EditText edittext = new EditText(Settings.this);
                edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                edittext.setHint(getText((R.string.prompt_password)));
                edittext.setTextSize(13);
                edittext.setSingleLine();
                FrameLayout container = new FrameLayout(Settings.this);
                FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMarginStart(60);//Margin= (50); // remember to scale correctly
                params.setMarginEnd(80);//= (50);
                edittext.setLayoutParams(params);
                container.addView(edittext);

                alert.setMessage(getText(R.string.confirmDeleteAcc));
                alert.setTitle(getText(R.string.delete_account));
//                alert.setView(edittext);
                alert.setPositiveButton(getText(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        alert2.setMessage(getText(R.string.enterDeleteAcc));
                        alert2.setTitle(getText(R.string.delete_account));
                        alert2.setView(container);
                        alert2.setPositiveButton(getText(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String password = edittext.getText().toString();

                                if (password.equals(pass)) {
                                    helper.DeleteStudent(studentId);
                                    Intent intent = new Intent(Settings.this,
                                            LoginPage.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                } else {

//                                        alert2.setMessage(getText(R.string.passNotMatch));
//                                        alert2.setTitle(getText(R.string.delete_account));
////                                        TODO set a wrong pass msg
//
//                                        alert2.show();
                                }
                            }
                        });


                        alert2.setNegativeButton(getText(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alert2.show();


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

        //============================= Logout and Delete  =============================


        //============================= Booklet  =============================

        booklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://drive.google.com/file/d/1Etqy36ZlNiWqlAxbAj63Nnkfmk0_-NhP/view?usp=sharing
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://drive.google.com/file/d/1Etqy36ZlNiWqlAxbAj63Nnkfmk0_-NhP/view?usp=sharing"));

                startActivity(httpIntent);
            }
        });
        //============================= Booklet  =============================


    }

    public void changeImage(int face){
        switch(face){
            case 700115:
                change.setImageResource(R.drawable.face1);
                break;
            case 700116:
                change.setImageResource(R.drawable.face2);
                break;
            case 700113:
                change.setImageResource(R.drawable.face3);
                break;
            case 700114:
                change.setImageResource(R.drawable.face4);
                break;
            case 700105:
                change.setImageResource(R.drawable.face5);
                break;
            default:
                change.setImageResource(R.drawable.face1);
        }

    }
}
