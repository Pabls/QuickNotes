package com.ar4i.quicknotes.data.database.dao;

import com.ar4i.quicknotes.data.database.dto.NoteDto;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes LIMIT 1")
    NoteDto getLastNote();

    @Query("DELETE FROM notes")
    void deleteNote();

    @Insert
    void insertNote(NoteDto noteDto);
}
