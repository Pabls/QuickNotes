package com.ar4i.quicknotes.data.database.dto;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteDto {
    @PrimaryKey
    @NonNull
    public String title;
    public String body;

    public NoteDto(){}

    public NoteDto(@NonNull String title, String body) {
        this.title = title;
        this.body = body;
    }

    // region========================================SET/GET========================================

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
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
