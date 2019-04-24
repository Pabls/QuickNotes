package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import androidx.annotation.NonNull;

import com.ar4i.quicknotes.data.entities.Note;
import com.ar4i.quicknotes.data.entities.Tag;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.TagVm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class NotesEventListener implements ValueEventListener {

    // region========================================Fileds=========================================

    private BehaviorSubject<List<NoteVm>> noteSub = BehaviorSubject.create();

    // endregion-------------------------------------Fileds-----------------------------------------


    // region========================================implements ValueEventListener==================

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
                    if (res != null) {
                        List<TagVm> tagVms = new ArrayList<>();
                        if (res.getTags() != null) {
                            for (Tag tag : res.getTags()) {
                                tagVms.add(new TagVm(tag.getName(), tag.getColor()));
                            }
                        }
                        noteVms.add(new NoteVm(res.getTimestamp(), res.getTitle(), res.getBody(), tagVms));
                    }

                }
            }
        } catch (Exception e) {
        }
        setNoteVms(noteVms);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }

    // endregion-------------------------------------implements ValueEventListener------------------


    // region========================================Public methods=================================

    public Observable<List<NoteVm>> receivedNotes() {
        return noteSub.distinctUntilChanged();
    }

    // endregion-------------------------------------Public methods---------------------------------


    // region========================================Private methods================================

    private void setNoteVms(List<NoteVm> notesVm) {
        noteSub.onNext(notesVm);
    }

    // endregion-------------------------------------Private methods--------------------------------
}
