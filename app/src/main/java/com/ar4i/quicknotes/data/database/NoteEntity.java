package com.ar4i.quicknotes.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteEntity {
    @PrimaryKey
    @NonNull
    public String title;
    public String body;
}
