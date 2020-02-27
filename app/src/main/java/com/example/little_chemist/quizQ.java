package com.example.little_chemist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.little_chemist.Chapters_dir.Ch1;
import com.example.little_chemist.kotlin.Intrinsics;
import java.util.ArrayList;

import alirezat775.lib.carouselview.Carousel;
import alirezat775.lib.carouselview.CarouselListener;
import alirezat775.lib.carouselview.CarouselModel;
import alirezat775.lib.carouselview.CarouselView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class quizQ extends AppCompatActivity {

    //private Boolean hasNextPage = true;
    private static final String TAG = quizQ.class.getSimpleName();

    private ArrayList<Integer> mImage = new ArrayList<Integer>();


    public quizQ(){
        Intrinsics.checkExpressionValueIsNotNull(TAG, "quizQ.class.getSimpleName()");
    }

    public final String getTAG() {
        return this.TAG;
    }


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.quizes);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homepage = new Intent(quizQ.this, Ch1.class);
                startActivity(Homepage);
                finish();
            }
        });
// RecyclerView
        recyclerimage();


//CarouselView
        final adapter adapter = new adapter();
        AppCompatActivity appcomp = this;
        CarouselView carouselview = findViewById(R.id.carousel_view1);
        final Carousel carousel = new Carousel(appcomp,carouselview,adapter);

        carousel.setOrientation(carouselview.HORIZONTAL, false, true);
        carousel.autoScroll(false, 1000000000, false);
        carousel.scaleView(true);

        adapter.setOnClickListener((com.example.little_chemist.adapter.OnClick)(new com.example.little_chemist.adapter.OnClick() {
            public void click(@NonNull model model) {
                Intrinsics.checkParameterIsNotNull(model, "model");
                carousel.remove((CarouselModel)model);
            }
        }));
//        carousel.scrollSpeed(100f)
       // carousel.enableSlider(true);

        carousel.addCarouselListener((CarouselListener)(new CarouselListener() {
            boolean positioneqfive = false;
            public void onPositionChange(int position) {
                Log.d(quizQ.this.getTAG(), "currentPosition : " + position);


                if(position != 0 ) {
                    int count = position - 1;
                    mImage.set(count, R.drawable.tick_mark);
                    initRecyclerView();
                    if (position == 4){
                        positioneqfive = true;
                    }
                }

                if (position != 4 && positioneqfive){
                        mImage.set(4, R.drawable.tick_mark);
                        initRecyclerView();

                }

            }

            public void onScroll(int dx, int dy) {
                carousel.pauseAutoScroll();
                Log.d(quizQ.this.getTAG(), "onScroll dx : " + dx + " -- dy : " + dx);
            }
        }));

//        carousel.add(EmptySampleModel("empty list"))
        carousel.add((new model(1)));
        carousel.add((new model(2)));
        carousel.add((new model(3)));
        carousel.add((new model(4)));
        carousel.add((new model(5)));



//        carousel.add((CarouselModel)(new model(6)));
//        carousel.add((CarouselModel)(new model(7)));
//        carousel.add((CarouselModel)(new model(8)));
//        carousel.add((CarouselModel)(new model(9)));
//        carousel.add((CarouselModel)(new model(10)));

    }

    private void recyclerimage(){

        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
        mImage.add(R.drawable.ques_mark);
//        mImage.add(R.drawable.graycircle);
//        mImage.add(R.drawable.graycircle);
//        mImage.add(R.drawable.graycircle);
//        mImage.add(R.drawable.graycircle);
//        mImage.add(R.drawable.graycircle);

        initRecyclerView();

    }

    private void initRecyclerView(){

        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,  mImage);
        recyclerView.setAdapter(adapter);

    }


}

