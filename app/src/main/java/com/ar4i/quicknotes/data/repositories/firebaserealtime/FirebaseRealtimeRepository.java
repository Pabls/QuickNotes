package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.entities.Note;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.Completable;

public class FirebaseRealtimeRepository implements IFirebaseRealtimeRepository {

    //==========================================start implements IFirebaseRealtimeRepository========

    @Override
    public Completable sendNote(NoteVm noteVm) {
        return Completable.create(emitter -> {
            DatabaseReference dbRef =  FirebaseDatabase.getInstance().getReference(noteVm.getUserId());
            dbRef.push().setValue(new Note(noteVm.getTimestamp(), noteVm.getTitle(), noteVm.getBody()));
            emitter.onComplete();
        });
    }

    //-------------------------------------------end implements IFirebaseRealtimeRepository---------
}
