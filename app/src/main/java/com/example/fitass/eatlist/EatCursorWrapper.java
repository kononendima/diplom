package com.example.fitass.eatlist;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class EatCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public EatCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public EatItem getEat() {
        String id = getString(getColumnIndex(EatItem.ID));
        String eat = getString(getColumnIndex(EatItem.EAT));
        String date = getString(getColumnIndex(EatItem.DATE));
        String userId = getString(getColumnIndex(EatItem.USER_ID));
        String calorie = getString(getColumnIndex(EatItem.CALORIE));
        String weight = getString(getColumnIndex(EatItem.WEIGHT));
        String uuid = getString(getColumnIndex(EatItem.UUID));
        EatItem eatItem = new EatItem(id, eat,date,calorie,userId,weight,uuid);
        return eatItem;
    }
}

