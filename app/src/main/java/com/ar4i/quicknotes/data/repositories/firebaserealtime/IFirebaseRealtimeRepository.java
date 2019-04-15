package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.entities.Note;

import io.reactivex.Single;

public interface IFirebaseRealtimeRepository {
    Single<Boolean> sendNote(Note note);
}