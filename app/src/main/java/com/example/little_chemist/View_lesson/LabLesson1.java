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

import com.example.little_chemist.AR.ARfragment2;
import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.AR.PointerDrawable;
import com.example.little_chemist.R;
import com.example.little_chemist.Tables.Student;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;

import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;

public class LabLesson1 extends AppCompatActivity {

    private TextView textView;
    private ModelAnimator modelAnimator;
    private Button button;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private int i=0;
    private ARfragment2 arFragment;
    private SharedPreferences pref;
    private  Bundle bundle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lesson1);
        arFragment = (ARfragment2) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        textView = findViewById(R.id.textview);
        bundle = getIntent().getExtras();
        textView.setText(R.string.tap_call);
        Button Reset = findViewById(R.id.Reset);
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
                if(!student.getLsnLock(String.valueOf(Lid)).equals("completed"))
                    helper.updateLesson(name,Lid,"completed");
                Intent h = new Intent(LabLesson1.this, Ch1.class);
                startActivity(h);
                finish();

            }
        });
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            createModel(hitResult.createAnchor(),arFragment);
        });
    }
    private void createModel(Anchor anchor, ARfragment2 arFragment) {

        ModelRenderable.builder()
                .setSource(this, Uri.parse("splitatom.sfb"))
                .build()
                .thenAccept(modelRenderable -> {
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    SkeletonNode skeletonNode = new SkeletonNode();
                    skeletonNode.setParent(anchorNode);
                    skeletonNode.setRenderable(modelRenderable);
                    arFragment.getArSceneView().getScene().addChild(anchorNode);
                    button= findViewById(R.id.button);
                    textView.setText(R.string.split_atom);
                    button.setOnClickListener(view ->  animate(modelRenderable));


                })
                .exceptionally(
                        throwable -> {

                            return null;
                        });
    }

    private void animate(ModelRenderable modelRenderable) {
        //check the modelAnimator status
        if(modelAnimator != null && modelAnimator.isRunning())
            modelAnimator.end();
        //Get animation data from the modelRenderable
        int a = modelRenderable.getAnimationDataCount();

        if(i == a)
            i=0;
        //Create AnimationData object to get Animation Data
        AnimationData animationData = modelRenderable.getAnimationData(i);
        //Create ModelAnimator object to start animating
        modelAnimator = new ModelAnimator(animationData,modelRenderable);
        modelAnimator.start();
        i++;

    }


}
