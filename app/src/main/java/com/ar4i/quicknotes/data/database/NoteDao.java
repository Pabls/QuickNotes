package com.ar4i.quicknotes.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes LIMIT 1")
    NoteEntity getLastNote();

    @Query("DELETE FROM notes")
    void deleteNote();

    @Insert
    void insertNote(NoteEntity noteEntity);
}
