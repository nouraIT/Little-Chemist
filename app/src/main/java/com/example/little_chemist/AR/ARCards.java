package com.example.little_chemist.AR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.Chapters_dir.Ch5;
import com.example.little_chemist.DatabaseHelper;
import com.example.little_chemist.R;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.sceneform.FrameTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.google.ar.core.Session;
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
    private  String text;
    //private  int  m=0;
    static int m =1;
    DatabaseHelper helper = new DatabaseHelper(this);
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_r_cards);

        arFragment = (CustomARfragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        textView = findViewById(R.id.textview);
        Button Reset = findViewById(R.id.reset);
        Button next = findViewById(R.id.next);
        textView.setText(R.string.scan);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdate);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String name = pref.getString("username", null);
        Bundle bundle=getIntent().getExtras();
        int Lid =  bundle.getInt("lessonId");



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

                if (m < 3) {
                    m++;
                    recreate();
                }
                else{
                    helper.updateLesson(name, Lid , "completed");
                    Intent h;
                    if(Lid != 2){
                        h = new Intent(ARCards.this, Ch5.class);
                    m=1;}
                    else {
                        h = new Intent(ARCards.this, Ch1.class);
                        m=1;
                    }
                    startActivity(h);
                    finish();
                }

            }
        });
    }


    private void onUpdate(FrameTime frameTime) {
// First call the image database
        Frame frame = arFragment.getArSceneView().getArFrame();
        Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);
        //Iterate through the image database
        for (AugmentedImage image : images) {
            // Check if scanned image existed in the image database
            if (image.getTrackingMethod() == AugmentedImage.TrackingMethod.FULL_TRACKING) {
                //Set the scene
                RelativeLayout gallery = findViewById(R.id.gallery);
                ArFragment arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
                String name = image.getName();
                //check which image was scanned
                switch (name) {
                    case "argon.png":
                        //Set string
                        string = "argon.sfb";
                        //Set the text that appears to the student
                        text=getString(R.string.argon2);
                        //call the 3D object
                        callAR();
                        break;
                    case "bromine.png":
                        string = "bariumatom.sfb";
                        text=getString(R.string.bromine2);
                        callAR();
                        break;
                    case "calcium.png":
                        string = "calcium.sfb";
                        text=getString(R.string.calcium2);
                        callAR();
                        break;
                    case "chlorine.png":
                        string = "chlorineatom.sfb";
                        text=getString(R.string.chlorine2);
                        callAR();
                        break;
                    case "fluorine.png":
                        string = "fluorineatom.sfb";
                        text=getString(R.string.fluorine2);
                        callAR();
                        break;
                    case "helium.png":
                        string = "heliumatom.sfb";
                        text=getString(R.string.helium2);
                        callAR();
                        break;
                    case "hydrogen.png":
                        string = "hydrogen.sfb";
                        text=getString(R.string.hydrogen2);
                        callAR();
                        break;
                    case "lithium.png":
                        string = "Lithiumatom.sfb";
                        text=getString(R.string.lithium2);
                        callAR();
                        break;
                    case "magnesium.png":
                        string = "magnesiumatomm.sfb";
                        text=getString(R.string.magnesium2);
                        callAR();
                        break;
                    case "neon.png":
                        string = "neonn.sfb";
                        text=getString(R.string.neon2);
                        callAR();
                        break;
                    case "sodium.png":
                        string = "sodium.sfb";
                        text=getString(R.string.sodium2);
                        callAR();
                        break;
                }


            }
        }
    }

    public void callAR(){
//Set Flag
        if (!appeared){

            // Set the 3D object in the scene
            setARFragment(string);
            if(count++==100)
                appeared = true;

            textView.setText(R.string.Look);


            //Set Timer for the student to find the atom
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            textView.setText(text);
                            cancel();
                        }
                    },
                    10000
            );


        }
    }

    public void loadDB(Session session, Config config){
        //Choose database to load
        InputStream dbStream = getResources().openRawResource(R.raw.mycard);
        try{
            //Load the database
            AugmentedImageDatabase aid= AugmentedImageDatabase.deserialize(session,dbStream);
            config.setAugmentedImageDatabase(aid);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setARFragment(String obj) {
        assert arFragment != null;

        //Set Renderable
        ModelRenderable.builder()
                .setSource(this, Uri.parse(obj))
                .build()
                .thenAccept(renderable -> andyRenderable = renderable)
                .exceptionally(
                        throwable -> {

                            return null;
                        });
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        //Node specification
        node.getScaleController().setMaxScale(10.19f);
        node.getScaleController().setMinScale(0.009f);
        node.setRenderable(andyRenderable);
        node.setParent(arFragment.getArSceneView().getScene());
        node.select();


    }
}
