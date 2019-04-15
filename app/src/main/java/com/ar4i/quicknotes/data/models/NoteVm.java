package com.ar4i.quicknotes.data.models;

import java.text.SimpleDateFormat;

public class NoteVm {
    private static final String PATTERN = "MM/dd/yyyy HH:mm:ss";
    private String creationDate;
    private long timestamp;
    private String title;
    private String body;

    public NoteVm() {
    }

    public NoteVm(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public NoteVm(long timestamp, String title, String body) {
        this(title, body);
        this.timestamp = timestamp;
        this.creationDate = covert(timestamp);
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

    // endregion-------------------------------------GET/SET--------------------------------------------

    // region========================================Private methods================================

    private String covert(long currenttimestamp) {
        return new SimpleDateFormat(PATTERN).format(new java.util.Date(currenttimestamp * 1000));
    }

    // endregion-------------------------------------Private methods--------------------------------

}
