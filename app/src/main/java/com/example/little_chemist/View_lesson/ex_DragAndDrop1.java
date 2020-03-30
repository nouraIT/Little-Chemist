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
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.little_chemist.R;

public class ex_DragAndDrop1 extends AppCompatActivity {

    public int segmentN,ex ;
    Button reset,finish;
    LinearLayout dropArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ex_draganddrop);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(ex_DragAndDrop1.this, Lessons.class);
                n.putExtra("lesson",11) ;
                startActivity(n);
            }
        });

        findViewById(R.id.c1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.c2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.c3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.c4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dropArea1).setOnDragListener(new MyDragListener());
        findViewById(R.id.dropArea3).setOnDragListener(new MyDragListener());
        dropArea = findViewById(R.id.dropArea);

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
                Intent n = new Intent(ex_DragAndDrop1.this, Lessons.class);
                n.putExtra("lesson",11) ;
                startActivity(n);

            }
        });

    }

    int currentIV = 0;

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
    int correctAns =0;
    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = ContextCompat.getDrawable(ex_DragAndDrop1.this, R.drawable.drop_area);
        Drawable normalShape = ContextCompat.getDrawable(ex_DragAndDrop1.this, R.drawable.normalshape);
        Drawable rightShape = ContextCompat.getDrawable(ex_DragAndDrop1.this, R.drawable.rightshape);

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
//|| v.getId()==R.id.dropArea2
                    if( ((currentIV==R.id.c1 || currentIV==R.id.c2 || currentIV==R.id.c4)
                        && (v.getId()==R.id.dropArea1 )
                    || (currentIV==R.id.c3 && v.getId()==R.id.dropArea3)) ){
                        correctAns++;
                    }
                    else if( ((currentIV==R.id.c1 || currentIV==R.id.c2 || currentIV==R.id.c4)
                            && (v.getId()==R.id.dropArea3) ) //|| v.getId()==R.id.dropArea2
                                || (currentIV==R.id.c3
                            && v.getId()==R.id.dropArea1 )){
                        correctAns--;
                    }

                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);

                    if(v.getId()==R.id.dropArea1) {
                        GridLayout container = (GridLayout) v;
                        container.setColumnCount(2);
                        container.addView(view);
                    }
                    else {
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                    }
                    view.setVisibility(View.VISIBLE);
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(correctAns==3) {
                        v.setBackgroundDrawable(normalShape);
                        dropArea.setBackgroundDrawable(rightShape);

                        finish.setVisibility(View.VISIBLE);
                    }else {
                        dropArea.setBackgroundDrawable(enterShape);
                        v.setBackgroundDrawable(normalShape);
                    }
                default:
                    break;
            }
            return true;
        }
    }

}
