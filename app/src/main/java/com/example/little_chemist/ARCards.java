package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebStorage;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.Timer;

import com.google.ar.core.Session;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import android.os.Bundle;


public class ARCards extends AppCompatActivity {
    int count = 0;
    private CustomARfragment arFragment;
    private TextView textView;
    private PointerDrawable pointer = new PointerDrawable();
    private boolean isTracking;
    private boolean isHitting;
    private boolean appeared = false;
    private String string;
    private ModelRenderable andyRenderable;
    private ModelRenderable mObjRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_a_r_cards);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_r_cards);
        arFragment = (CustomARfragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        textView = findViewById(R.id.textview);
        Button Reset = findViewById(R.id.reset);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdate);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Reset Code
                recreate();
            }
        });
    }


    private void onUpdate(FrameTime frameTime) {

        Frame frame = arFragment.getArSceneView().getArFrame();
        Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage image : images) {
            textView.setText(R.string.scan);
            if (image.getTrackingMethod() == AugmentedImage.TrackingMethod.FULL_TRACKING) {

                RelativeLayout gallery = findViewById(R.id.gallery);
                ArFragment arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
                String name = image.getName();

                switch (name) {
                    case "argon.png":
                        string = "argon.sfb";
                        callAR();
                        break;
                    case "bromine.png":
                        string = "bariumatom.sfb";
                        callAR();
                        break;
                    case "calcium.png":
                        string = "calcium.sfb";
                        callAR();
                        break;
                    case "chlorine.png":
                        string = "chlorineatom.sfb";
                        callAR();
                        break;
                    case "fluorine.png":
                        string = "fluorineatom.sfb";
                        callAR();
                        break;
                    case "helium.png":
                        string = "heliumatom.sfb";
                        callAR();
                        break;
                    case "hydrogen.png":
                        string = "hydrogen.sfb";
                        callAR();
                        break;
                    case "lithium.png":
                        string = "Lithiumatom.sfb";
                        callAR();
                        break;
                    case "magnesium.png":
                        string = "magnesiumatomm.sfb";
                        callAR();
                        break;
                    case "neon.png":
                        string = "neonn.sfb";
                        callAR();
                        break;
                    case "sodium.png":
                        string = "sodium.sfb";
                        callAR();
                        break;
                }


            }
        }
    }
    public void callAR(){

        if (!appeared){

            //System.out.println("it works fine here ");
            setARFragment(string);
            if(count++==100)
                appeared = true;

            textView.setText(R.string.Look);
//Timer t = new Timer();



        }
    }
    public void loadDB(Session session, Config config)
    {
        InputStream dbStream = getResources().openRawResource(R.raw.mycard);
        try{
            AugmentedImageDatabase aid= AugmentedImageDatabase.deserialize(session,dbStream);
            config.setAugmentedImageDatabase(aid);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void setARFragment(String obj) {
        assert arFragment != null;

//        arFragment.getArSceneView().getScene().addOnUpdateListener((Scene.OnUpdateListener) this); //You can do this anywhere. I do it on activity creation post inflating the fragment
        //mARFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        // When you build a Renderable, Sceneform loads its resources in the background while returning
        // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().

        ModelRenderable.builder()
                .setSource(this, Uri.parse(obj))
                .build()
                .thenAccept(renderable -> andyRenderable = renderable)
                .exceptionally(
                        throwable -> {

                            return null;
                        });
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());

        node.getScaleController().setMaxScale(10.19f);
        node.getScaleController().setMinScale(0.009f);
        node.setRenderable(andyRenderable);
        node.setParent(arFragment.getArSceneView().getScene());
        node.select();

//        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdate);

    }
}