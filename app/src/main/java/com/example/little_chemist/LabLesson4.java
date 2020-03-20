package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.ModelRenderable;

public class LabLesson4 extends AppCompatActivity {
    int count=0;
    private CustomARfragment arFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lesson4);
        arFragment = (CustomARfragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        textView = findViewById(R.id.textview);
        textView.setText(R.string.tap_call4);
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            createModel(hitResult.createAnchor(),arFragment);
        });
    }
    private void createModel(Anchor anchor, CustomARfragment arFragment) {

        ModelRenderable.builder()
                .setSource(this, Uri.parse("gas.sfb"))
                .build()
                .thenAccept(modelRenderable -> {
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    SkeletonNode skeletonNode = new SkeletonNode();
                    skeletonNode.setParent(anchorNode);
                    skeletonNode.setRenderable(modelRenderable);
                    arFragment.getArSceneView().getScene().addChild(anchorNode);
                    //textView.setText(R.string.co2);


                })
                .exceptionally(
                        throwable -> {

                            return null;
                        });
    }

}
