package com.example.little_chemist.View_lesson;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.little_chemist.R;

public class ex_DragAndDrop3 extends AppCompatActivity{

    Button reset,finish;
    LinearLayout dropArea, dropArea2;
    int currentIV = 0,correctAns =0;
    TextView text1,text2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ex_draganddrop3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(ex_DragAndDrop3.this, slideAdapter.class);
                startActivity(Homepage);
                finish();
            }
        });

        findViewById(R.id.s1).setOnTouchListener(new ex_DragAndDrop3.MyTouchListener());
        findViewById(R.id.s2).setOnTouchListener(new ex_DragAndDrop3.MyTouchListener());
        findViewById(R.id.dropArea1).setOnDragListener(new ex_DragAndDrop3.MyDragListener());
        findViewById(R.id.dropArea2).setOnDragListener(new ex_DragAndDrop3.MyDragListener());
        dropArea = findViewById(R.id.dropArea1);
        dropArea2 = findViewById(R.id.dropArea2);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        reset = findViewById(R.id.reset) ;
        finish = findViewById(R.id.finish);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(ex_DragAndDrop3.this, Lessons.class);
                n.putExtra("lesson",16) ;
                startActivity(n);

            }
        });
    }


    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);

                currentIV=view.getId();
                return true;
            } else {
                return false;
            }
        }
    }
    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = ContextCompat.getDrawable(ex_DragAndDrop3.this, R.drawable.drop_area);
        Drawable normalShape = ContextCompat.getDrawable(ex_DragAndDrop3.this, R.drawable.normalshape);
        Drawable rightShape = ContextCompat.getDrawable(ex_DragAndDrop3.this, R.drawable.rightshape);

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    if( currentIV==R.id.s1 && v.getId()==R.id.dropArea2 ){

                        v.setBackgroundDrawable(normalShape);
                        dropArea2.setBackgroundDrawable(rightShape);
                        correctAns++;
                    }
                    else if(currentIV==R.id.s2 && v.getId()==R.id.dropArea1){

                        v.setBackgroundDrawable(normalShape);
                        dropArea.setBackgroundDrawable(rightShape);
                        correctAns++;
                    }
                    else {
                        v.setBackgroundDrawable(normalShape);
                        correctAns--;
                    }

                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);

                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);

                    view.setVisibility(View.VISIBLE);
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(correctAns==2)
                        finish.setVisibility(View.VISIBLE);

                default:
                    break;
            }
            return true;
        }
    }
}
