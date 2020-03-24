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
import com.example.little_chemist.Tables.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.core.*;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.math.Vector3;
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



public class LabLesson5 extends AppCompatActivity {

private static int n=0;
    //for net movement along x-axis
    public float x = 0;
    public float y = 0;
    private ARfragment6 arFragment;
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
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lesson5);
        arFragment = (ARfragment6) getSupportFragmentManager().findFragmentById(R.id.arFragment);
     textView = findViewById(R.id.textview);
        Button Reset = findViewById(R.id.reset);
        Button next = findViewById(R.id.next);
        textView.setText(R.string.tap_call6);
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            textView.setText(R.string.thro);
            addObject(Uri.parse("redfire.sfb"));
        });

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
                String Lid=""+bundle.getInt("lessonId") ;
                helper.updateLesson(name,Integer.parseInt(Lid),"completed");
                Intent h = new Intent(LabLesson5.this, Ch3.class);
                startActivity(h);

            }
        });
        arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            arFragment.onUpdate(frameTime);
            onUpdate();
        });

        modelLoader = new ModelLoader(new WeakReference<>(this));

        initializeGallery();


    }

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

    private boolean updateTracking() {
        Frame frame = arFragment.getArSceneView().getArFrame();
        boolean wasTracking = isTracking;
        isTracking = frame != null &&
                frame.getCamera().getTrackingState() == TrackingState.TRACKING;
        return isTracking != wasTracking;
    }

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

    private android.graphics.Point getScreenCenter() {
        View vw = findViewById(android.R.id.content);
        return new android.graphics.Point(vw.getWidth()/2, vw.getHeight()/2);
    }

    private void initializeGallery() {
        LinearLayout gallery = findViewById(R.id.gallery_layout);

        ImageView green = findViewById(R.id.image1);
        green.setImageResource(R.drawable.green);
        //green.setTooltipText(getString(R.string.gas));
        green.setOnClickListener(view ->{
            addnew("greenfire.sfb");
          });
        green.setPadding(5,5,5,5);

        ImageView blue = findViewById(R.id.image2);
        blue.setImageResource(R.drawable.blue);
      //  gas.setTooltipText(getString(R.string.gas));
        blue.setOnClickListener(view ->{
            addnew("bluefire.sfb");
        });
        blue.setPadding(5,5,5,5);

        ImageView yellow = findViewById(R.id.image3);
        yellow.setImageResource(R.drawable.yellow);
        //  gas.setTooltipText(getString(R.string.gas));
        yellow.setOnClickListener(view ->{
            addnew("orangefire.sfb");
        });
        yellow.setPadding(5,5,5,5);

        ImageView purple = findViewById(R.id.image4);
        purple.setImageResource(R.drawable.purple);
        //  gas.setTooltipText(getString(R.string.gas));
        purple.setOnClickListener(view ->{
            addnew("purplefire.sfb");
        });
        purple.setPadding(5,5,5,5);

        ImageView orange = findViewById(R.id.image5);
        orange.setImageResource(R.drawable.orange);
        //  gas.setTooltipText(getString(R.string.gas));
        orange.setOnClickListener(view ->{
            addnew("yellowfire.sfb");
        });
        orange.setPadding(5,5,5,5);

        ImageView red = findViewById(R.id.image6);
        red.setImageResource(R.drawable.red);
        //  gas.setTooltipText(getString(R.string.gas));
        red.setOnClickListener(view ->{
            addnew("redfire.sfb");
        });
        red.setPadding(5,5,5,5);
    }

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

    public void addNodeToScene(Anchor anchor, ModelRenderable renderable) {

        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
// Maxscale must be greater than minscale

//        node.getScaleController().setMaxScale(0.09f);
        node.getScaleController().setMinScale(0.05f);
        node.setRenderable(renderable);
        node.setParent(anchorNode);
       // node.setName(String.valueOf(n));
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
    public void addnew(String obj){
        List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
        for (Node node : children) {
                if ( !(node instanceof Camera) && !(node instanceof Sun)){
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

    public void onException(Throwable throwable){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(throwable.getMessage())
                .setTitle("Codelab error!");
        AlertDialog dialog = builder.create();
        dialog.show();
        return;
    }

    public void Render()
    {
        changePos();
        if(Collision(net, ball))
        {
            points++; //You dont need findView Textview score for that exists in OnCreate Method
            this.score.setText("Score:" + points);
        }
    }

    public void changePos() {

        //down
        ballDownY += 10;
        if (ball.getY() > screenHeight) {
            ballDownX = (float) Math.floor((Math.random() * (screenWidth - ball.getWidth())));
            ballDownY = -100.0f;

        }
        ball.setY(ballDownY);
        ball.setX(ballDownX);

        //make net follow finger
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                x = event.getX();
                y = event.getY();

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    net.setX(x);
                    net.setY(y);
                }
                return true;
            }

        });

    }

    public boolean Collision(ImageView net, ImageView ball){
        Rect BallRect = new Rect();
        ball.getHitRect(BallRect);
        Rect NetRect = new Rect();
        net.getHitRect(NetRect);
        return BallRect.intersect(NetRect);
    }

    public class ModelLoader {
        private static final String TAG = "ModelLoader";
        private final WeakReference<LabLesson5> owner;

        ModelLoader(WeakReference<LabLesson5> owner) {
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
                        LabLesson5 activity = owner.get();
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
