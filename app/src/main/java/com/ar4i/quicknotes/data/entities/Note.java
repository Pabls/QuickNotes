package com.ar4i.quicknotes.data.entities;

public class Note {
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
}
