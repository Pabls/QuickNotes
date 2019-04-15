package com.ar4i.quicknotes.domain.notes;

import com.ar4i.quicknotes.data.models.NoteVm;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface INotesInteractor {

    Completable saveNote(NoteVm note);

    Single<NoteVm> getLastSavedNote();

    Completable deleteLastNote();

    Completable sendNote(NoteVm noteVm);
}
