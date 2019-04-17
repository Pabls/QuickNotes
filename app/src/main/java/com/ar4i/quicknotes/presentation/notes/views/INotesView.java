package com.ar4i.quicknotes.presentation.notes.views;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.views.IMvpView;

import java.util.List;

import io.reactivex.Observable;

public interface INotesView extends IMvpView {
    Observable<Integer> onListItemClick();

    void setNotes(List<NoteVm> notes);

    void showNoNotesMessage(boolean show);
}
