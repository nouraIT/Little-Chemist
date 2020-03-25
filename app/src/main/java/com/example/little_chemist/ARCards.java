package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.example.little_chemist.Tables.Student;
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
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
    private  String text;
//private  int  m=0;
    static int m =0;
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

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
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
                    if(Lid != 2)
                        h = new Intent(ARCards.this, Ch5.class);

                    h = new Intent(ARCards.this, Ch1.class);
                    startActivity(h);

                }

            }
        });
    }


    private void onUpdate(FrameTime frameTime) {

        Frame frame = arFragment.getArSceneView().getArFrame();
        Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage image : images) {
           // textView.setText(R.string.scan);
            if (image.getTrackingMethod() == AugmentedImage.TrackingMethod.FULL_TRACKING) {

                RelativeLayout gallery = findViewById(R.id.gallery);
                ArFragment arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
                String name = image.getName();

                switch (name) {
                    case "argon.png":
                        string = "argon.sfb";
                        text=getString(R.string.argon2);
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

        if (!appeared){

            //System.out.println("it works fine here ");
            setARFragment(string);
            if(count++==100)
                appeared = true;

            textView.setText(R.string.Look);

            //TODO add timer
            new Reminder(30);
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            textView.setText(text);


        }
    }

    public void loadDB(Session session, Config config){
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



class Reminder {
    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            timer.cancel(); //Terminate the timer thread
        }
    }

    public static void main(String args[]) {
        new Reminder(5);
        System.out.println("Task scheduled.");
    }
}