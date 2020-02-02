package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, bottomAnim;
    androidx.appcompat.widget.AppCompatImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //realm init

        /*
        RealmConfiguration config = new RealmConfiguration.Builder()
          .name("myrealm.realm")
          .encryptionKey(getKey())
          .schemaVersion(42)
          .modules(new MySchemaModule())
          .migration(new MyMigration())
          .build();
        // Use the config
        Realm realm = Realm.getInstance(config);
        */

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        image = findViewById(R.id.imageview);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom);
        image.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(MainActivity.this, Login.class);
                                          startActivity(intent);
                                          finish();

                                      }
                                  }, //Pass time here
                SPLASH_SCREEN);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.+OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
