
package com.example.fitass.service;

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
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.fitass.Fragments.ActivityFragment;
import com.example.fitass.MainActivity;
import com.example.fitass.R;
import com.example.fitass.activitypage.ActivityListManager;
import com.example.fitass.activitypage.Step;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.String.valueOf;

public class MyService extends Service {
    SensorManager sensorManager;
    SensorEventListener listen;
    ActivityListManager activityListManager;
    int steps=0;
    Handler  handler = new Handler();;
    boolean flag;
    DateFormat timeFormat;
    Date currentDate;
    ArrayList<Step> stepList;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        activityListManager=new ActivityListManager(getApplicationContext());
        steps=activityListManager.getCurrentUserSteps();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        sensorManager = (SensorManager) getApplicationContext()
                .getSystemService(SENSOR_SERVICE);
        listen = new Pedometer2();
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensorManager.registerListener(listen, accel, SensorManager.SENSOR_DELAY_NORMAL);

        flag=true;
        recallingSync();
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(valueOf(steps))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
        activityListManager=new ActivityListManager(getApplicationContext());
        activityListManager.saveStepsToDb(steps);
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

    public void recallingSync() {
        currentDate= new Date();
         Runnable r = new Runnable() {
            public void run() {
                timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                final String currentTimeString = timeFormat.format(currentDate);
               if(currentTimeString=="00:00:00" || currentTimeString=="00:00:01" || currentTimeString=="00:00:02" || currentTimeString=="00:00:03" || currentTimeString=="00:00:04" || currentTimeString=="00:00:05" )
                   steps=0;
                activityListManager = new ActivityListManager(getApplicationContext());
                activityListManager.saveStepsToDb(steps);
                if (flag) {
                    handler.postDelayed(this, 5000);
                }else {
                   handler.removeCallbacksAndMessages(this);
                }
            }
        };
        if(flag==true) {
            r.run();
        }else{
            handler.removeCallbacksAndMessages(r);
        }
    }
    public class Pedometer2 extends Object implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                getAccelerometer(event);
            }
        }
        private void getAccelerometer(SensorEvent event) {
            float[] values = event.values;
            if(values[0]==1.0){
                steps++;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}