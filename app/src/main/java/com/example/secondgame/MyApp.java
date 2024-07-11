package com.example.secondgame;

import android.app.Application;

import com.example.secondgame.utils.GPS;
import com.example.secondgame.utils.MySPV3;
import com.example.secondgame.utils.MySignal;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MySignal.init(this);
        MySPV3.init(this);
        GPS.init(this);

    }
}