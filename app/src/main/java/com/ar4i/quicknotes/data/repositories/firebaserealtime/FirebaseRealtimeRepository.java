package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.entities.Note;
import com.ar4i.quicknotes.data.entities.Tag;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.TagVm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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
    private static NotesEventListener notesEventListener;
    private static TagsEventListener tagsEventListener;
    private static String userId;

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
    public Observable<NoteVm> getAddedNote() {
        return notesEventListener.receivedNote();
    }

    @Override
    public Observable<NoteVm> getDeletedNote() {
        return notesEventListener.deletedNote();
    }

    @Override
    public Completable sendTag(TagVm tagVm) {
        return Completable.create(emitter -> {
            dbRef.child(TAGS_PATH).child(userId)
                    .push()
                    .setValue(new Tag(tagVm.getName(), tagVm.getColor()));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<TagVm> getTags() {
        return tagsEventListener.receivedTag();
    }

    //-------------------------------------------end implements IFirebaseRealtimeRepository---------

    //==========================================start Private methods===============================

    private static void initFirebaseDatabaseInstance() {
        if (dbRef == null) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            dbRef = FirebaseDatabase.getInstance().getReference();
            dbRef.keepSynced(true);

            userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            notesEventListener = new NotesEventListener();
            dbRef.child(NOTES_PATH).child(userId).addChildEventListener(notesEventListener);

            tagsEventListener = new TagsEventListener();
            dbRef.child(TAGS_PATH).child(userId).addChildEventListener(tagsEventListener);
        }
    }

    private void saveNote(NoteVm noteVm) {
        List<Tag> tags = new ArrayList<>();
        if (noteVm.getTags() != null) {
            for (TagVm tagVm : noteVm.getTags()) {
                tags.add(new Tag(tagVm.getName(), tagVm.getColor()));
            }
        }
        dbRef.child(NOTES_PATH).child(userId)
                .child(getUid(userId, noteVm.getTimestamp()))
                .push()
                .setValue(new Note(noteVm.getTimestamp(), noteVm.getTitle(), noteVm.getBody(), tags));
    }

    private void deleteNote(NoteVm noteVm) {
        dbRef.child(NOTES_PATH).child(userId)
                .child(getUid(userId, noteVm.getTimestamp()))
                .removeValue();
    }

    private String getUid(String userId, long timestamp) {
        return userId + String.valueOf(timestamp);
    }

    //-------------------------------------------end Private methods--------------------------------
}
