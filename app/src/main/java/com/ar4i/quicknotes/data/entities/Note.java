package com.ar4i.quicknotes.data.entities;

import com.google.firebase.database.Exclude;

import java.util.List;

public class Note {
    // region========================================Fields=========================================

    public long timestamp;
    public String title;
    public String body;
    public List<Tag> tags;

    // endregion-------------------------------------Fields-----------------------------------------

    public Note() {
    }

    public Note(long timestamp, String title, String body, List<Tag> tags) {
        this.timestamp = timestamp;
        this.title = title;
        this.body = body;
        this.tags = tags;
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
    @Exclude
    public List<Tag> getTags() { return tags; }
    @Exclude
    public void setTags(List<Tag> tags) { this.tags = tags; }

    // endregion-------------------------------------SET/GET----------------------------------------
}
