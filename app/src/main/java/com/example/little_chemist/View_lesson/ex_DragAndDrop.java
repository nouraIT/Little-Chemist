package com.example.little_chemist.View_lesson;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.little_chemist.R;

public class ex_DragAndDrop extends AppCompatActivity {

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
                Intent Homepage = new Intent(ex_DragAndDrop.this, slideAdapter.class);
                startActivity(Homepage);
                finish();
            }
        });

        findViewById(R.id.c1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.c2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.c3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.c4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dropArea1).setOnDragListener(new MyDragListener());
        findViewById(R.id.dropArea2).setOnDragListener(new MyDragListener());
        findViewById(R.id.dropArea3).setOnDragListener(new MyDragListener());
        dropArea = findViewById(R.id.dropArea);

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
        Drawable enterShape = ContextCompat.getDrawable(ex_DragAndDrop.this, R.drawable.drop_area);
        Drawable normalShape = ContextCompat.getDrawable(ex_DragAndDrop.this, R.drawable.normalshape);
        Drawable rightShape = ContextCompat.getDrawable(ex_DragAndDrop.this, R.drawable.rightshape);

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

                    if( ((currentIV==R.id.c1 || currentIV==R.id.c2 || currentIV==R.id.c4)
                        && (v.getId()==R.id.dropArea1 || v.getId()==R.id.dropArea2 || v.getId()==dropArea.getId()) )
                    || (currentIV==R.id.c3 && (v.getId()==R.id.dropArea3))){
                        correctAns++;
                    }
                    else if( ((currentIV==R.id.c1 || currentIV==R.id.c2 || currentIV==R.id.c4)
                            && (v.getId()==R.id.dropArea3) )
                                || (currentIV==R.id.c3
                            && (v.getId()==R.id.dropArea1 || v.getId()==R.id.dropArea2 || v.getId()==dropArea.getId() ))){
                        correctAns--;
                    }

                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
//                case DragEvent is correct
                case DragEvent.ACTION_DRAG_ENDED:
                    if(correctAns==3) {
//                        System.out.println("RIGHT SHAPE");
                        dropArea.setBackgroundDrawable(rightShape);
                        //TODO add reset btn
                        //TODO show a button here
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
