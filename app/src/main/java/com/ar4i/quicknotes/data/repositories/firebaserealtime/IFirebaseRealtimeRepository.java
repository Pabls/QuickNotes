package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.TagVm;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface IFirebaseRealtimeRepository {
    Completable sendNote(NoteVm noteVm);

    Completable removeNote(NoteVm noteVm);

    Completable updateNote(NoteVm noteVm);

    Observable<List<NoteVm>> getNotes(String userId);

    Completable sendTag(TagVm tagVm);

    Observable<List<TagVm>> getTags(String userId);
}
