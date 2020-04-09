package com.example.fitass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Pedometer implements SensorEventListener {
    TextView tv_steps;
    SensorManager sensorManager;
    Context mContext=;
    int a=0;
    View view;



    public Pedometer(Context context) {
        mContext=context;
    }





    @Override
    public void onSensorChanged(SensorEvent event) {

        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor counterSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensorManager.registerListener(this,counterSensor,SensorManager.SENSOR_DELAY_FASTEST);
        if(event.values[0]==1.0) {
            a++;

            tv_steps.setText(Integer.toString(a));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
