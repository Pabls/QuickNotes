package com.ar4i.quicknotes.data.models;

import androidx.annotation.Nullable;

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
    private List<TagVm> tags;

    public NoteVm() {
    }

    public NoteVm(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public NoteVm(long timestamp, String title, String body, List<TagVm> tags) {
        this(title, body);
        this.timestamp = timestamp;
        this.creationDate = convert(timestamp);
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

    // region========================================Override=======================================

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NoteVm)) {
            return false;
        }
        NoteVm nvm = (NoteVm) obj;
        return nvm.timestamp == timestamp;
    }

    // endregion-------------------------------------Override---------------------------------------
}
