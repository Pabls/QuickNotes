package com.ar4i.quicknotes.data.database;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes LIMIT 1")
    NoteEntity getLastNote();

    @Query("DELETE FROM notes")
    void deleteNote();

    @Update
    void update(NoteEntity noteEntity);
}
