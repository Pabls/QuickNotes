package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.entities.Note;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class FirebaseRealtimeRepository implements IFirebaseRealtimeRepository {

    // region========================================Fields=========================================

    public static final String NOTES_PATH = "notes";

    // endregion-------------------------------------Fields-----------------------------------------

    //==========================================start implements IFirebaseRealtimeRepository========

    @Override
    public Completable sendNote(NoteVm noteVm) {
        return Completable.create(emitter -> {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(NOTES_PATH);
            dbRef.child(noteVm.getUserId()).push().setValue(new Note(noteVm.getTimestamp(), noteVm.getTitle(), noteVm.getBody()));
            emitter.onComplete();
        });
    }

    @Override
    public Completable removeNote(NoteVm noteVm) {
        return Completable.create(emitter -> {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(NOTES_PATH);
            DatabaseReference notesRef = dbRef.child(noteVm.getUserId());
            String key = notesRef.getKey();
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<NoteVm>> getNotes(String userId) {
        return Observable.create(emitter -> {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(NOTES_PATH);

            dbRef.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<NoteVm> noteVms = new ArrayList<>();
                    Iterable<DataSnapshot> iterableDataSnapshot = dataSnapshot.getChildren();
                    try {
                        Note note = dataSnapshot.child(userId).getValue(Note.class);
                        Iterator<DataSnapshot> iterator = iterableDataSnapshot.iterator();
                        while (iterator.hasNext()) {
                            Note res = iterator.next().getValue(Note.class);
                            if (res != null)
                                noteVms.add(new NoteVm(res.getTimestamp(), res.getTitle(), res.getBody(), userId));
                        }
                    } catch (Exception e) {
                    }
                    emitter.onNext(noteVms);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        });
    }

    //-------------------------------------------end implements IFirebaseRealtimeRepository---------
}
