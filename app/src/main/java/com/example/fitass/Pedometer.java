package com.example.fitass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.TextView;

public class Pedometer extends Object implements SensorEventListener,Runnable {
    TextView tv_steps;
    SensorManager mSensorManager;
    Context mContext;
    int a=0;
    View view;



    public Pedometer(Context context,SensorManager sensorManager) {
        mSensorManager=sensorManager;
        mContext=context;
        String a;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        Sensor counterSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mSensorManager.registerListener(this,counterSensor,SensorManager.SENSOR_DELAY_FASTEST);
        if(event.values[0]==1.0) {
            a++;

            tv_steps.setText(Integer.toString(a));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void run() {

    }
}
