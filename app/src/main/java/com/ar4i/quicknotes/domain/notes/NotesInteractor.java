package com.ar4i.quicknotes.domain.notes;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.repositories.database.IDatabaseRepository;
import com.ar4i.quicknotes.data.repositories.firebaserealtime.IFirebaseRealtimeRepository;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class NotesInteractor implements INotesInteractor {

    public NotesInteractor(IDatabaseRepository iDatabaseRepository,
                           IFirebaseRealtimeRepository iFirebaseRealtimeRepository) {
        this.iDatabaseRepository = iDatabaseRepository;
        this.iFirebaseRealtimeRepository = iFirebaseRealtimeRepository;
    }

    // region========================================Fields=========================================

    private Subscription sub;
    IDatabaseRepository iDatabaseRepository;
    IFirebaseRealtimeRepository iFirebaseRealtimeRepository;

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

    @Override
    public Completable sendNote(NoteVm noteVm) {
        return iFirebaseRealtimeRepository.sendNote(noteVm);
    }

    @Override
    public Observable<List<NoteVm>> getNotes(String userId) {
        return iFirebaseRealtimeRepository.getNotes(userId);
    }

    // endregion-------------------------------------implements INotesInteractor--------------------
}
