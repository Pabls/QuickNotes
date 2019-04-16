package com.ar4i.quicknotes.domain.notes;

import com.ar4i.quicknotes.data.models.NoteVm;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface INotesInteractor {

    Completable saveNote(NoteVm note);

    Single<NoteVm> getLastSavedNote();

    Completable deleteLastNote();

    Completable sendNote(NoteVm noteVm);

    Observable<List<NoteVm>> getNotes(String userId);
}
