package com.example.little_chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.app.AlertDialog;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import com.example.little_chemist.Chapters_dir.Ch3;
import com.example.little_chemist.Chapters_dir.Ch4;
import com.example.little_chemist.Tables.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.core.*;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Rectangle;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.ModelRenderable;



public class LabLesson4 extends AppCompatActivity {

    private ARfragment5 arFragment;
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

    //for net movement along x-axis
    public float x = 0;
    public float y = 0;
    private ModelLoader modelLoader;
    private RelativeLayout myLayout = null;
    //Screen Size
    private int screenWidth;
    private int screenHeight;
    //Position
    private float ballDownY;
    private float ballDownX;

    //Images
    private ImageView net = null;
    private ImageView ball = null;
    //score
    private TextView score = null;
    //points
    private int points = 0;
    private SharedPreferences pref;
    private  Bundle bundle ;
    private DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lesson4);
        bundle = getIntent().getExtras();
        textView = findViewById(R.id.textview);
        textView.setText(R.string.tap_call7);
        arFragment = (ARfragment5) getSupportFragmentManager().findFragmentById(R.id.arFragment);
//        textView = findViewById(R.id.textview);
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

                pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String name = pref.getString("username", null); // getting String
                Student student = helper.getStudent(name);
                int Lid=bundle.getInt("lessonId") ;
                helper.updateLesson(name,Lid,"completed");
                Intent h = new Intent(LabLesson4.this, Ch3.class);
                startActivity(h);
                finish();

            }
        });
        arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            arFragment.onUpdate(frameTime);
            onUpdate();
        });

        modelLoader = new ModelLoader(new WeakReference<>(this));

        initializeGallery();


    }

    //==========================================================================


    //this method will update the pointer for the user while the user is exploring
    // to show them if they hit a plane or not
    private void onUpdate() {
        boolean trackingChanged = updateTracking();
        View contentView = findViewById(android.R.id.content);

        if (trackingChanged) {
            if (isTracking) {
                contentView.getOverlay().add(pointer);
            } else {
                contentView.getOverlay().remove(pointer);
            }
            contentView.invalidate();
        }

        if (isTracking) {
            boolean hitTestChanged = updateHitTest();
            if (hitTestChanged) {
                pointer.setEnabled(isHitting);
                contentView.invalidate();
            }
        }
    }

    //==========================================================================


    //this will update the attr (isTracking) for the (onUpdate) method
    // to show if the user is moving or not
    private boolean updateTracking() {
        Frame frame = arFragment.getArSceneView().getArFrame();
        boolean wasTracking = isTracking;
        isTracking = frame != null &&
                frame.getCamera().getTrackingState() == TrackingState.TRACKING;
        return isTracking != wasTracking;
    }


    //==========================================================================


    //this will update the attr (hitTestChanged) for the (onUpdate) method
    //Performs frame.HitTest and returns if a hit is detected

    //Performs a ray cast from the user's device in the direction of the given location in the camera view.
    // Intersections with detected scene geometry are returned,
    // sorted by distance from the device; the nearest intersection is returned first.

    //it returns a an ordered list of intersections with scene geometry, nearest hit first
    // i didn't get it, but these are what i found
    private boolean updateHitTest() {
        Frame frame = arFragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        boolean wasHitting = isHitting;
        isHitting = false;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane &&
                        ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    isHitting = true;
                    break;
                }
            }
        }
        return wasHitting != isHitting;
    }

    //==========================================================================


    //this will just get the center of the screen
    private android.graphics.Point getScreenCenter() {
        View vw = findViewById(android.R.id.content);
        return new android.graphics.Point(vw.getWidth()/2, vw.getHeight()/2);
    }

    //==========================================================================

    //this method will put pics of the obj in the gallery at the bottom
    // and sets every pic with its 3D model
    private void initializeGallery() {
        LinearLayout gallery = findViewById(R.id.gallery_layout);

        ImageView gas = findViewById(R.id.image1);
        gas.setImageResource(R.drawable.gas);
        gas.setTooltipText(getString(R.string.gas));
        gas.setOnClickListener(view ->{addnew("gas.sfb");});
        gas.setPadding(5,5,5,5);
        //gallery.addView(gas);

        ImageView liquid = findViewById(R.id.image2);
        liquid.setImageResource(R.drawable.liq);
        liquid.setTooltipText(getString(R.string.liquid));
        liquid.setOnClickListener(view ->{addnew("liquid.sfb");});
        liquid.setPadding(5,5,5,5);

        //gallery.addView(liquid);
//
//        ImageView house = new ImageView(this);
//        house.setImageResource(R.drawable.house_thumb);
//        house.setContentDescription("house");
//        house.setOnClickListener(view ->{addObject(Uri.parse("House.sfb"));});
//        gallery.addView(house);

    }

    //==========================================================================

    //this method will add a new node for the 3D model
    public void addnew(String obj){
        List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
        for (Node node : children) {
            if ( !(node instanceof com.google.ar.sceneform.Camera) && !(node instanceof Sun)){
                if(((AnchorNode) node).getAnchor() != null ) {
                    ((AnchorNode) node).getAnchor().detach();

                }
            }
            else continue;
            if ( !(node instanceof Sun)&&!(node instanceof Camera) ){
                node.setParent(null);
            }
        }
        addObject(Uri.parse(obj));
    }
    //==========================================================================

    //this method will add the 3D model
    private void addObject(Uri model) {
        Frame frame = arFragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane &&
                        ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    modelLoader.loadModel(hit.createAnchor(), model);
                    break;

                }
            }
        }
    }

    //==========================================================================

    //this method will determine where the model will be exactly (sets the coordinates)
    public void addNodeToScene(Anchor anchor, ModelRenderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
// Maxscale must be greater than minscale

//       node.getScaleController().setMaxScale(0.09f);
        node.getScaleController().setMinScale(0.05f);
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        SkeletonNode skeletonNode = new SkeletonNode();
        skeletonNode.setParent(anchorNode);
        skeletonNode.setRenderable(renderable);

        // node.getName();
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
        if(modelAnimator != null && modelAnimator.isRunning())
            modelAnimator.end();

        int a = renderable.getAnimationDataCount();

        if(i == a)
            i=0;

        AnimationData animationData = renderable.getAnimationData(i);

        modelAnimator = new ModelAnimator(animationData,renderable);
        modelAnimator.setRepeatCount(50);
        modelAnimator.start();
        i++;
    }


    //==========================================================================

    //this method is just and exception catcher
    public void onException(Throwable throwable){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(throwable.getMessage())
                .setTitle("Codelab error!");
        AlertDialog dialog = builder.create();
        dialog.show();
        return;
    }

    //==========================================================================


//    public void Render()
//    {
//        changePos();
//        if(Collision(net, ball))
//        {
//            points++; //You dont need findView Textview score for that exists in OnCreate Method
//            this.score.setText("Score:" + points);
//        }
//    }
//    //==========================================================================
//
//    public void changePos() {
//
//        //down
//        ballDownY += 10;
//        if (ball.getY() > screenHeight) {
//            ballDownX = (float) Math.floor((Math.random() * (screenWidth - ball.getWidth())));
//            ballDownY = -100.0f;
//
//        }
//        ball.setY(ballDownY);
//        ball.setX(ballDownX);
//
//        //make net follow finger
//        myLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                x = event.getX();
//                y = event.getY();
//
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    net.setX(x);
//                    net.setY(y);
//                }
//                return true;
//            }
//
//        });
//
//    }
//
//    //checks if the models intersects
//    public boolean Collision(ImageView net, ImageView ball){
//        Rect BallRect = new Rect();
//        ball.getHitRect(BallRect);
//        Rect NetRect = new Rect();
//        net.getHitRect(NetRect);
//        return BallRect.intersect(NetRect);
//    }


    //==========================================================================


    //class for loading the models
    public class ModelLoader {
        private static final String TAG = "ModelLoader";
        private final WeakReference<LabLesson4> owner;

        ModelLoader(WeakReference<LabLesson4> owner) {
            this.owner = owner;
        }

        void loadModel(Anchor anchor, Uri uri) {
            if (owner.get() == null) {
                Log.d(TAG, "Activity is null.  Cannot load model.");
                return;
            }
            ModelRenderable.builder()
                    .setSource(owner.get(), uri)
                    .build()
                    .handle((renderable, throwable) -> {
                        LabLesson4 activity = owner.get();
                        if (activity == null) {
                            return null;
                        } else if (throwable != null) {
                            activity.onException(throwable);
                        } else {
                            activity.addNodeToScene(anchor, renderable);
                        }
                        return null;
                    });

            return;
        }
    }

}
