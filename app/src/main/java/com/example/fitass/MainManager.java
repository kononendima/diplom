package com.example.fitass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MainManager {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    MainManager(Context context) {
        mContext=context;
        mDatabase=new
                DataBaseHelper(mContext).getWritableDatabase();
    }
    public void checkRecord(){



    }
}
