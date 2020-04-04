package com.example.little_chemist.View_lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
//import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.little_chemist.AR.ARfragment3;
import com.example.little_chemist.Chapters_dir.Ch2;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.AR.PointerDrawable;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;

import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.ModelRenderable;

public class LabLesson2 extends AppCompatActivity {

    private ARfragment3 arFragment;
    private TextView textView;
    private SharedPreferences pref;
    private  Bundle bundle ;
    private DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lesson2);
        arFragment = (ARfragment3) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        textView = findViewById(R.id.textview);
        bundle = getIntent().getExtras();
        textView.setText(R.string.tap_call2);
        Button Reset = findViewById(R.id.reset);
        Button next = findViewById(R.id.next);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // Reset Code
                recreate();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //System.out.println(m);

                pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String name = pref.getString("username", null); // getting String
                Student student = helper.getStudent(name);
                int Lid=bundle.getInt("lessonId") ;
                if(!student.getLsnLock(String.valueOf(Lid)).equals("completed"))
                    helper.updateLesson(name,Lid,"completed");
                Intent h = new Intent(LabLesson2.this, Ch2.class);
                startActivity(h);
                finish();

            }
        });
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            createModel(hitResult.createAnchor(),arFragment);
        });
    }
    private void createModel(Anchor anchor, ARfragment3 arFragment) {

        ModelRenderable.builder()
                .setSource(this, Uri.parse("molecule.sfb"))
                .build()
                .thenAccept(modelRenderable -> {
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    SkeletonNode skeletonNode = new SkeletonNode();
                    skeletonNode.setParent(anchorNode);
                    skeletonNode.setRenderable(modelRenderable);
                    arFragment.getArSceneView().getScene().addChild(anchorNode);
                    textView.setText(R.string.co2);


                })
                .exceptionally(
                        throwable -> {

                            return null;
                        });
    }

}
