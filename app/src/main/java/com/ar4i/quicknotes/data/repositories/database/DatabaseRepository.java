package com.ar4i.quicknotes.data.repositories.database;

import com.ar4i.quicknotes.data.database.NotesDatabase;
import com.ar4i.quicknotes.data.database.dto.NoteDto;
import com.ar4i.quicknotes.data.models.NoteVm;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DatabaseRepository implements IDatabaseRepository {

    @Inject
    public DatabaseRepository(NotesDatabase notesDatabase) {
        this.notesDatabase = notesDatabase;
    }

    // region========================================Fields=========================================

    NotesDatabase notesDatabase;

    // endregion-------------------------------------Fields-----------------------------------------


    // region========================================implements IDatabaseRepository=================

    @Override
    public Completable setNote(NoteVm note) {
        return Completable.create(emitter -> {
            notesDatabase.getNoteDao().insertNote(new NoteDto(note.getTitle(), note.getBody()));
            emitter.onComplete();
        });
    }

    @Override
    public Completable deleteLastNote() {
        return Completable.create(emitter -> {
            notesDatabase.getNoteDao().deleteNote();
            emitter.onComplete();
        });
    }

    @Override
    public Single<NoteVm> getLastNote() {
        return Single.create(emitter -> {

            NoteVm noteVm;
            NoteDto note = notesDatabase.getNoteDao().getLastNote();

            if (note != null) {
                noteVm = new NoteVm(note.getTitle(), note.getBody());
            } else {
                noteVm = new NoteVm();
            }

            emitter.onSuccess(noteVm);
        });
    }

    // endregion-------------------------------------implements IDatabaseRepository-----------------
}
