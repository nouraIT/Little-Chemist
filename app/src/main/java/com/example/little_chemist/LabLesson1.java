package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.se.omapi.Session;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebStorage;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch5;
import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;

import com.google.ar.core.Session;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import android.os.Bundle;

public class LabLesson1 extends AppCompatActivity {
    int count=0;
    static int m =0;
    private TextView textView;
    private ModelAnimator modelAnimator;
    private PointerDrawable pointer = new PointerDrawable();
    private boolean isTracking;
    private boolean isHitting;
    private boolean appeared = false;
    private Button button;
    private String string;
    private DatabaseHelper databaseHelper;
    private ModelRenderable andyRenderable;
    private ModelRenderable mObjRenderable;
    private int i=0;
    private ARfragment2 arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lesson1);
        arFragment = (ARfragment2) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        textView = findViewById(R.id.textview);
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

                //System.out.println(m);


                    Intent h = new Intent(LabLesson1.this, Ch1.class);
                    startActivity(h);

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

                    button.setOnClickListener(view ->  animate(modelRenderable));


                })
                .exceptionally(
                        throwable -> {

                            return null;
                        });
    }

    private void animate(ModelRenderable modelRenderable) {
        textView.setText(R.string.split_atom);
        if(modelAnimator != null && modelAnimator.isRunning())
            modelAnimator.end();

        int a = modelRenderable.getAnimationDataCount();

        if(i == a)
            i=0;

        AnimationData animationData = modelRenderable.getAnimationData(i);

        modelAnimator = new ModelAnimator(animationData,modelRenderable);
        modelAnimator.start();
        i++;

    }


}
