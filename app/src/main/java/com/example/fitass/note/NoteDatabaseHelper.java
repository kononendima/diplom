package com.example.fitass.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteDatabaseHelper {

    private static final String TABLE_NOTES = "notes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEXT = "text";
    private SQLiteDatabase mDatabase;

    public NoteDatabaseHelper(Context context) {
        mDatabase = new DataBaseHelper(context).getWritableDatabase();
    }

    public long addNote(String text) {
        ContentValues values = new ContentValues();
        values.put("text", text);

        String currentDate = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(new Date());
        values.put("date", currentDate);

        return mDatabase.insert("notes", null, values);
    }


    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        Cursor cursor = mDatabase.query("notes", null, null, null, null, null, "id DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String text = cursor.getString(cursor.getColumnIndexOrThrow("text"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                notes.add(new Note(id, text, date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    public void updateNote(long id, String newText) {
        ContentValues values = new ContentValues();
        values.put("text", newText);

        String currentDate = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(new Date());
        values.put("date", currentDate); // Обновим дату изменения

        mDatabase.update("notes", values, "id = ?", new String[]{String.valueOf(id)});
    }
}
