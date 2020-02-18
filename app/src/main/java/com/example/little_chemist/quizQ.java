package com.example.little_chemist;

import android.app.AppComponentFactory;
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
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import alirezat775.lib.carouselview.Carousel;
import alirezat775.lib.carouselview.CarouselListener;
import alirezat775.lib.carouselview.CarouselModel;
import alirezat775.lib.carouselview.CarouselView;

public class quizQ extends AppCompatActivity {

    //private Boolean hasNextPage = true;
    private static final String TAG = quizQ.class.getSimpleName();

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
            public void onPositionChange(int position) {
                Log.d(quizQ.this.getTAG(), "currentPosition : " + position);
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


}

