package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.entities.Note;
import com.ar4i.quicknotes.data.entities.Tag;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.TagVm;
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

    public FirebaseRealtimeRepository() {
        initFirebaseDatabaseInstance();
    }

    // region========================================Fields=========================================

    public static final String NOTES_PATH = "notes";
    public static final String TAGS_PATH = "tags";
    private static DatabaseReference dbRef;

    // endregion-------------------------------------Fields-----------------------------------------

    //==========================================start implements IFirebaseRealtimeRepository========

    @Override
    public Completable sendNote(NoteVm noteVm) {
        return Completable.create(emitter -> {
            saveNote(noteVm);
            emitter.onComplete();
        });
    }

    @Override
    public Completable removeNote(NoteVm noteVm) {
        return Completable.create(emitter -> {
            deleteNote(noteVm);
            emitter.onComplete();
        });
    }

    @Override
    public Completable updateNote(NoteVm noteVm) {
        return Completable.create(emitter -> {
            deleteNote(noteVm);
            saveNote(noteVm);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<NoteVm>> getNotes(String userId) {
        return Observable.create(emitter -> {
            dbRef.child(NOTES_PATH).child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<NoteVm> noteVms = new ArrayList<>();
                    Iterable<DataSnapshot> iterableDataSnapshot = dataSnapshot.getChildren();
                    try {
                        Iterator<DataSnapshot> iterator = iterableDataSnapshot.iterator();
                        while (iterator.hasNext()) {
                            Iterator<DataSnapshot> childIterator = iterator.next().getChildren().iterator();
                            while (childIterator.hasNext()) {
                                Note res = childIterator.next().getValue(Note.class);
                                if (res != null)
                                    noteVms.add(new NoteVm(res.getTimestamp(), res.getTitle(), res.getBody(), userId));
                            }
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

    @Override
    public Completable sendTag(TagVm tagVm) {
        return Completable.create(emitter -> {
            dbRef.child(TAGS_PATH).child(tagVm.getUserId())
                    .push()
                    .setValue(new Tag(tagVm.getName(), tagVm.getColor()));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<TagVm>> getTags() {
        return null;
    }

    //-------------------------------------------end implements IFirebaseRealtimeRepository---------

    //==========================================start Private methods===============================

    private static void initFirebaseDatabaseInstance() {
        if (dbRef == null) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            dbRef = FirebaseDatabase.getInstance().getReference();
            dbRef.keepSynced(true);
        }
    }

    private void saveNote(NoteVm noteVm) {
        dbRef.child(NOTES_PATH).child(noteVm.getUserId())
                .child(getUid(noteVm.getUserId(), noteVm.getTimestamp()))
                .push()
                .setValue(new Note(noteVm.getTimestamp(), noteVm.getTitle(), noteVm.getBody()));
    }

    private void deleteNote(NoteVm noteVm) {
        dbRef.child(NOTES_PATH).child(noteVm.getUserId())
                .child(getUid(noteVm.getUserId(), noteVm.getTimestamp()))
                .removeValue();
    }

    private String getUid(String userId, long timestamp) {
        return userId + String.valueOf(timestamp);
    }

    //-------------------------------------------end Private methods--------------------------------
}
