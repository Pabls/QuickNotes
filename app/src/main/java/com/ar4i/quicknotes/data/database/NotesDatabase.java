package com.ar4i.quicknotes.data.database;

import com.ar4i.quicknotes.data.database.dao.NoteDao;
import com.ar4i.quicknotes.data.database.dto.NoteDto;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteDto.class}, version = NotesDatabase.VERSION, exportSchema = false)
public abstract  class NotesDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public static final String NAME = "com.ar4i.quicknotes.database";

    public abstract NoteDao getNoteDao();
}
