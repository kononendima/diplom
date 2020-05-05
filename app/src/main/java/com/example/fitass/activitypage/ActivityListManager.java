package com.example.fitass.activitypage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;
import com.example.fitass.Step;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityListManager {
    Step step;
    SharedPreferences sPref;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public ActivityListManager(Context context) {
        mContext=context;
        mDatabase=new
                DataBaseHelper(mContext).getWritableDatabase();
    }
    public void saveSteps(int steps){
        int userId=getCurrentUserId();
        String todayDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String a=checkRecordOnThisDay(todayDate);
  //      Cursor cursor =mDatabase.rawQuery("INSERT INTO activity_table(user_id,steps,date) VALUES ",null);

    }
    private int getCurrentUserId(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int id = Integer.parseInt(sPref.getString("id","0"));
        return id;
    }
    public String checkRecordOnThisDay(String todayDate){
        step=new Step();
        Cursor cursor=mDatabase.rawQuery("select * from activity_table where \"date\" = \""+todayDate+"\"",null);
        cursor.moveToFirst();
        String id=cursor.getString(0);
       return id;
    }
}
