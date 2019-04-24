package com.ar4i.quicknotes.domain.notes;

import com.ar4i.quicknotes.data.models.NoteVm;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface INotesInteractor {

    Completable saveNote(NoteVm note);

    Single<NoteVm> getLastUnsavedNote();

    Completable deleteLastNote();

    Completable sendNote(NoteVm noteVm);

    Observable<NoteVm> getAddedNote();

    Observable<NoteVm> getDeletedNote();

    Completable removeNote(NoteVm noteVm);

    Completable updateNote(NoteVm noteVm);
}
