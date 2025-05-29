package com.example.fitass.note;

public class Note {
    private long id;
    private String text;
    private String date; // <-- новое поле

    public Note(long id, String text, String date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}

