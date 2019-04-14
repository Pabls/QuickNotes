package com.ar4i.quicknotes.data.model;

import java.text.SimpleDateFormat;

public class Note {

    private static final String PATTERN = "MM/dd/yyyy HH:mm:ss";
    private String creationDate;

    public long timestamp;
    public String title;
    public String body;

    public Note() {
    }

    public Note(long timestamp, String title, String body) {
        this.timestamp = timestamp;
        this.title = title;
        this.body = body;
    }

//    public String getCreationDate() {
//        return new SimpleDateFormat(PATTERN).format(new java.util.Date(timestamp * 1000));
//    }
}
