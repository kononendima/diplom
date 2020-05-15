package com.example.fitass.activitypage;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.fitass.DataBaseHelper;
import com.example.fitass.UserManager;
import com.example.fitass.eatlist.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityListManager {
    Step step;
    UserManager userManager;
    String todayDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    int userId;
    private SQLiteDatabase mDatabase;
    public ActivityListManager(Context context) {
        mDatabase=new
                DataBaseHelper(context).getWritableDatabase();
        userManager=new UserManager(context);
    }

    public void saveStepsToDb(int steps){
         userId=userManager.getCurrentUserIdFromMemory();

        String a=checkStepRecordOnThisDay();
        if(a==null){
            ContentValues values = new ContentValues();
            values.put(step.USER_ID, userManager.getCurrentUserIdFromMemory());
            values.put(step.STEPS, steps);
            values.put(step.DATE, todayDate);
            mDatabase.insert(step.TABLE_NAME,null,values);
        }else {
            mDatabase.execSQL("UPDATE "+step.TABLE_NAME+" SET \""+step.STEPS+"\" = "+ steps+" WHERE ((\""+step.DATE+"\"=\""+todayDate+"\") and (\""+step.USER_ID+"\" = "+userId+"))");
        }
    }

    public String checkStepRecordOnThisDay() {


        Cursor cursor = mDatabase.rawQuery("select * from " + step.TABLE_NAME + " where ((\""+step.DATE+"\"=\""+todayDate+"\") and (\""+step.USER_ID+"\" = "+userId+"))", null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            String id = cursor.getString(0);
            return id;
        } else {
            return null;
        }
    }

    public int getCurrentUserSteps(){
            int userId = userManager.getCurrentUserIdFromMemory();
            Cursor cursor = mDatabase.rawQuery("select * from " + step.TABLE_NAME + " where ((\"" + step.DATE + "\"=\"" + todayDate + "\") and (\"" + step.USER_ID + "\" = " + userId + "))", null);
            cursor.moveToFirst();
            if(cursor.getCount()!=0) {
                int stepsCount = Integer.parseInt(cursor.getString(2));
                return stepsCount;
            }else {
                return 0;
            }
    }
    public ArrayList<Step> getStepList(){
            int userId=userManager.getCurrentUserIdFromMemory();
            ArrayList<Step> stepList=new ArrayList<>();
            Cursor cursor =mDatabase.rawQuery("SELECT "+step.STEPS+","+step.DATE+" from "+step.TABLE_NAME+" where \""+step.USER_ID+"\" = "+ userId+"",null);
            if (cursor.moveToFirst()) {
                do {
                    Step step=new Step();
                    step.setSteps(cursor.getString(0));
                    step.setDate(cursor.getString(1));
                    stepList.add(step);


                } while (cursor.moveToNext());

            }
            cursor.close();

            return stepList;
        }


}
