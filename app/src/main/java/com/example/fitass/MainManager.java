package com.example.fitass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MainManager {

    MainManager(Context context) {
        SQLiteDatabase mDatabase = new
                DataBaseHelper(context).getWritableDatabase();
    }
    public void checkRecord(){
    }
}
