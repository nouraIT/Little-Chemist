package com.example.little_chemist;

import alirezat775.lib.carouselview.CarouselModel;

public final class model extends CarouselModel {
    private int id;


//get the question and answers from here
    public final String getId() {

        return "Question "+this.id;
    }

    public model(int id) {

        this.id = id;
    }
}