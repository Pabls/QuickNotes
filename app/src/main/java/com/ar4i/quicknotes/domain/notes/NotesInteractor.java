package com.ar4i.quicknotes.domain.notes;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.repositories.database.IDatabaseRepository;

import org.reactivestreams.Subscription;

import io.reactivex.Completable;
import io.reactivex.Single;

public class NotesInteractor implements INotesInteractor {

    public NotesInteractor(IDatabaseRepository iDatabaseRepository) {

        this.iDatabaseRepository = iDatabaseRepository;
    }

    // region========================================Fields=========================================

    private Subscription sub;
    IDatabaseRepository iDatabaseRepository;

    // endregion-------------------------------------Fields-----------------------------------------

    // region========================================implements INotesInteractor====================

    @Override
    public Completable saveNote(NoteVm note) {
        return iDatabaseRepository.setNote(note);
    }

    @Override
    public Single<NoteVm> getLastSavedNote() {
        return iDatabaseRepository.getLastNote();
    }

    @Override
    public Completable deleteLastNote() {
        return iDatabaseRepository.deleteLastNote();
    }

    // endregion-------------------------------------implements INotesInteractor--------------------
}
