package com.example.secondgame.callbacks;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorDetector {

    public interface CallBack_move {
        void move(int direction);
    }

    private SensorManager mSensorManager;
    private Sensor sensor;

    private CallBack_move callBack_move;

    public SensorDetector(Context context, CallBack_move _callBack_move) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.callBack_move = _callBack_move;
    }

    public void start() {
        mSensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mSensorManager.unregisterListener(sensorEventListener);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            Log.d("Accelerator", "" + x);
            calculatePlace(x);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

    private void calculatePlace(float x) {
        if (x + 1 > 3.5)
            callBack_move.move(1);
        else if (x - 1 < -3.5)
            callBack_move.move(-1);

    }
}