package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ar4i.quicknotes.data.entities.Note;
import com.ar4i.quicknotes.data.entities.Tag;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.TagVm;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class NotesEventListener implements ChildEventListener {

    // region========================================Fileds=========================================

    private BehaviorSubject<NoteVm> addedNoteSub = BehaviorSubject.create();
    private BehaviorSubject<NoteVm> deletedNoteSub = BehaviorSubject.create();

    // endregion-------------------------------------Fileds-----------------------------------------


    // region========================================implements ChildEventListener==================
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Note addedNote = getNote(dataSnapshot);
        NoteVm noteVm = convert(addedNote);
        setAddedNoteVm(noteVm);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        Note deletedNote = getNote(dataSnapshot);
        NoteVm noteVm = convert(deletedNote);
        setDeletedNoteVm(noteVm);
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }

    // endregion-------------------------------------implements ChildEventListener------------------


    // region========================================Public methods=================================

    public Observable<NoteVm> receivedNote() {
        return addedNoteSub.distinctUntilChanged();
    }

    public Observable<NoteVm> deletedNote() {
        return deletedNoteSub.distinctUntilChanged();
    }

    // endregion-------------------------------------Public methods---------------------------------


    // region========================================Private methods================================

    private NoteVm convert(Note note) {
        NoteVm noteVm = null;
        if (note != null) {
            List<TagVm> tagVms = new ArrayList<>();
            if (note.getTags() != null) {
                for (Tag tag : note.getTags()) {
                    tagVms.add(new TagVm(tag.getName(), tag.getColor()));
                }
            }
            noteVm = new NoteVm(note.getTimestamp(), note.getTitle(), note.getBody(), tagVms);
        }
        return noteVm;
    }

    private Note getNote(DataSnapshot dataSnapshot) {
        Note note;
        try {
            note = dataSnapshot.getChildren().iterator().next().getValue(Note.class);
            if (note != null) {
                List<TagVm> tagVms = new ArrayList<>();
                if (note.getTags() != null) {
                    for (Tag tag : note.getTags()) {
                        tagVms.add(new TagVm(tag.getName(), tag.getColor()));
                    }
                }
            }
        } catch (Exception e) {
            note = null;
        }
        return note;
    }

    private void setAddedNoteVm(NoteVm noteVm) {
        if (noteVm != null)
            addedNoteSub.onNext(noteVm);
    }

    private void setDeletedNoteVm(NoteVm noteVm) {
        if (noteVm != null)
            deletedNoteSub.onNext(noteVm);
    }
    // endregion-------------------------------------Private methods--------------------------------
}
