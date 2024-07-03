package com.example.secondgame.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import im.delight.android.location.SimpleLocation;

public class GPS {
    private static GPS myGps = null;


    private Context context;

    public static GPS getInstance() {
        return myGps;
    }

    private SimpleLocation location;

    private GPS(Context context) {
        this.context = context;
        location = new SimpleLocation(context);

    }

    public static void init(Context context) {
        if (myGps == null) {
            myGps = new GPS(context);
        }
    }

    public void checkPreferences(AppCompatActivity activity){
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }
    }


    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }

    public void start() {
        location.beginUpdates();
    }

    public void stop() {
        location.endUpdates();
    }
}
