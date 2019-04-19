package com.ar4i.quicknotes.data.models;

import android.os.Parcelable;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteVm implements Serializable {
    private static final String PATTERN = "dd.MM.yyyy HH:mm:ss";
    private String creationDate;
    private long timestamp;
    private String title;
    private String body;
    private String userId;

    public NoteVm() {
    }

    public NoteVm(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public NoteVm(long timestamp, String title, String body, String userId) {
        this(title, body);
        this.timestamp = timestamp;
        this.creationDate = covert(timestamp);
        this.userId = userId;
    }

    // region========================================GET/SET============================================

    public String getCreationDate() {
        return creationDate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // endregion-------------------------------------GET/SET--------------------------------------------

    // region========================================Private methods================================

    private String covert(long currenttimestamp) {
        Date date = new Date(currenttimestamp);
        Format format = new SimpleDateFormat(PATTERN);
        return format.format(date);
    }

    // endregion-------------------------------------Private methods--------------------------------

}
