package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.entities.Note;

import io.reactivex.Single;

public class FirebaseRealtimeRepository implements IFirebaseRealtimeRepository {

    @Override
    public Single<Boolean> sendNote(Note note) {
        return Single.create(emitter -> {
            emitter.onSuccess(true);
        });
    }
}
