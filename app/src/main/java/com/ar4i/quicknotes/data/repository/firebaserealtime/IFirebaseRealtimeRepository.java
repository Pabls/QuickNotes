package com.ar4i.quicknotes.data.repository.firebaserealtime;

import com.ar4i.quicknotes.data.model.Note;

import io.reactivex.Single;

public interface IFirebaseRealtimeRepository {
    Single<Boolean> sendNote(Note note);
}
