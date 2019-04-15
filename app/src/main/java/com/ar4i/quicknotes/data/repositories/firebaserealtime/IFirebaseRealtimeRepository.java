package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.models.NoteVm;

import io.reactivex.Completable;

public interface IFirebaseRealtimeRepository {
    Completable sendNote(NoteVm noteVm);
}
