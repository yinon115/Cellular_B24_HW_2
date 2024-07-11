/* package com.example.secondgame.utils;

import android.content.Context;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.secondgame.R;



public class MyImageUtils {

    private static MyImageUtils instance;
    private static Context appContext;

    public static MyImageUtils getInstance() {
        return instance;
    }

    private MyImageUtils(Context context) {
        appContext = context;
    }

    public static MyImageUtils initHelper(Context context) {
        if (instance == null)
            instance = new MyImageUtils(context);
        return instance;
    }



    public void load(String link, ImageView imageView) {
        Glide
                .with(appContext)
                .load(link)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    public void load(int drawable, ImageView imageView) {
        Glide
                .with(appContext)
                .load(drawable)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}


 */