package com.ar4i.quicknotes.data.models;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteVm implements Serializable {
    private static final String PATTERN = "dd.MM.yyyy HH:mm:ss";
    private String creationDate;
    private long timestamp;
    private String title;
    private String body;
    private String userId;
    private List<TagVm> tags;

    public NoteVm() {
    }

    public NoteVm(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public NoteVm(long timestamp, String title, String body, String userId, List<TagVm> tags) {
        this(title, body);
        this.timestamp = timestamp;
        this.creationDate = convert(timestamp);
        this.userId = userId;
        this.tags = tags;
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

    public List<TagVm> getTags() {
        return tags;
    }

    public void setTags(List<TagVm> tags) {
        this.tags = tags;
    }

    // endregion-------------------------------------GET/SET--------------------------------------------

    // region========================================Private methods================================

    private String convert(long currenttimestamp) {
        Date date = new Date(currenttimestamp);
        Format format = new SimpleDateFormat(PATTERN);
        return format.format(date);
    }

    // endregion-------------------------------------Private methods--------------------------------

}
