
package com.example.fitass;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.fitass.activitypage.ActivityListManager;

import java.util.Date;

import static java.lang.String.valueOf;

public class MyService extends Service {
    SensorManager sensorManager;
    SensorEventListener listen;
    Date currentDate;
    ActivityListManager activityListManager;
    int Steps=0;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getApplicationContext()
                .getSystemService(SENSOR_SERVICE);
        listen = new Pedometer2();
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensorManager.registerListener(listen, accel, SensorManager.SENSOR_DELAY_NORMAL);
        String input = intent.getStringExtra("inputExtra");


        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(valueOf(Steps))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread
        //stopSelf();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activityListManager=new ActivityListManager(getApplicationContext());
        activityListManager.saveSteps(Steps);
        sensorManager.unregisterListener(listen);
        Toast.makeText(this, "Destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement

        if(values[0]==1.0){
            Steps++;
        }



    }

    public class Pedometer2 extends Object implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                getAccelerometer(event);
            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }


    }
}