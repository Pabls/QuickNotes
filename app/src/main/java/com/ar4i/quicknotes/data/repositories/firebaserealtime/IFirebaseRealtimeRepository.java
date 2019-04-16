package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.models.NoteVm;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IFirebaseRealtimeRepository {
    Completable sendNote(NoteVm noteVm);
    Observable<List<NoteVm>> getNotes(String userId);
}
