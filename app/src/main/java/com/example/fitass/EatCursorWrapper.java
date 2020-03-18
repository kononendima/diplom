package com.example.fitass;

import android.database.Cursor;
import android.database.CursorWrapper;

public class EatCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public EatCursorWrapper(Cursor cursor) {
        super(cursor);
    }
//Прослойка между курсором и бд
    public EatItem getEat() {
        String id = getString(getColumnIndex(EatItem.ID));
        String eat = getString(getColumnIndex(EatItem.EAT));
        String date = getString(getColumnIndex(EatItem.DATE));
        String calory = getString(getColumnIndex(EatItem.CALORY));
        EatItem eatItem = new EatItem(id, eat,date,calory);
        return eatItem;
    }

}

