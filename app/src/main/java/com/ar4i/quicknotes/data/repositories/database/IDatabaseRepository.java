package com.ar4i.quicknotes.data.repositories.database;

import com.ar4i.quicknotes.data.models.NoteVm;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IDatabaseRepository {
    Completable setNote(NoteVm note);

    Completable deleteLastNote();

    Single<NoteVm> getLastNote();
}
