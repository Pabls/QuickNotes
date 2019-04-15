package com.ar4i.quicknotes.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteEntity.class}, version = NotesDatabase.VERSION, exportSchema = false)
public abstract  class NotesDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public static final String NAME = "com.ar4i.quicknotes.database";

    public abstract NoteDao getNoteDao();
}
