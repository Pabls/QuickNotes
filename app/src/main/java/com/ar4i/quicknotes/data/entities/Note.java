package com.ar4i.quicknotes.data.entities;

import com.google.firebase.database.Exclude;

public class Note {
    // region========================================Fields=========================================

    public long timestamp;
    public String title;
    public String body;

    // endregion-------------------------------------Fields-----------------------------------------

    public Note() {
    }

    public Note(long timestamp, String title, String body) {
        this.timestamp = timestamp;
        this.title = title;
        this.body = body;
    }

    // region========================================SET/GET========================================
    @Exclude
    public long getTimestamp() {
        return timestamp;
    }
    @Exclude
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    @Exclude
    public String getTitle() {
        return title;
    }
    @Exclude
    public void setTitle(String title) {
        this.title = title;
    }
    @Exclude
    public String getBody() {
        return body;
    }
    @Exclude
    public void setBody(String body) {
        this.body = body;
    }

    // endregion-------------------------------------SET/GET----------------------------------------
}
