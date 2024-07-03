package com.example.secondgame.model;

import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

public class Element {

    private AppCompatImageView image;
    private Type type;

    public Element() {
    }

    public ImageView getImage() {
        return image;
    }

    public Type getType() {
        return type;
    }

    public Element setImage(AppCompatImageView image) {
        this.image = image;
        return this;
    }

    public Element setType(Type type) {
        this.type = type;
        return this;
    }
}