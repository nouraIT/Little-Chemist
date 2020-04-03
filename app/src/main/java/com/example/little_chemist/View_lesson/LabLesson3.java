package com.example.little_chemist.View_lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
//import android.se.omapi.Session;
import android.widget.Button;
import android.widget.TextView;

import com.example.little_chemist.AR.ARfragment4;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.AR.PointerDrawable;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;

import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.ModelRenderable;


import android.view.View;

public class LabLesson3 extends AppCompatActivity {
    int count=0;
    private ARfragment4 arFragment;
    private TextView textView;
    private ModelAnimator modelAnimator;
    private PointerDrawable pointer = new PointerDrawable();
    private boolean isTracking;
    private boolean isHitting;
    private boolean appeared = false;
    private Button button;
    private String string;
    private ModelRenderable andyRenderable;
    private ModelRenderable mObjRenderable;
    private int i=0;
    private SharedPreferences pref;
    private  Bundle bundle ;
    private DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lesson3);
        Button Reset = findViewById(R.id.reset);
        bundle = getIntent().getExtras();
        arFragment = (ARfragment4) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        textView = findViewById(R.id.textview);
        textView.setText(R.string.tap_call3);
        //Button Reset = findViewById(R.id.reset);
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

                pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String name = pref.getString("username", null); // getting String
                Student student = helper.getStudent(name);
                int Lid=bundle.getInt("lessonId") ;
                helper.updateLesson(name,Lid,"completed");
                Intent h = new Intent(LabLesson3.this, Ch4.class);
                startActivity(h);
                finish();

            }
        });
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            createModel(hitResult.createAnchor(),arFragment);
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Reset Code
                recreate();
            }
        });
    }
    private void createModel(Anchor anchor, ARfragment4 arFragment) {

        ModelRenderable.builder()
                .setSource(this, Uri.parse("cyclodextrin.sfb"))
                .build()
                .thenAccept(modelRenderable -> {
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    SkeletonNode skeletonNode = new SkeletonNode();
                    skeletonNode.setParent(anchorNode);
                    skeletonNode.setRenderable(modelRenderable);
                    arFragment.getArSceneView().getScene().addChild(anchorNode);
                    textView.setText(R.string.cyclodextrin);

                })
                .exceptionally(
                        throwable -> {

                            return null;
                        });
    }
}
