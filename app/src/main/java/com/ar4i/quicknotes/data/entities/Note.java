package com.ar4i.quicknotes.data.entities;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // endregion-------------------------------------SET/GET----------------------------------------
}
