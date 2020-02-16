package com.example.little_chemist;

import androidx.annotation.NonNull;
import com.example.little_chemist.kotlin.Intrinsics;
import alirezat775.lib.carouselview.CarouselModel;

public final class EmptyModel extends CarouselModel {
    private final String text;

    @NonNull
    public final String getText() {
        return this.text;
    }

    public EmptyModel(@NonNull String text) {
        super();
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.text = text;
    }
}