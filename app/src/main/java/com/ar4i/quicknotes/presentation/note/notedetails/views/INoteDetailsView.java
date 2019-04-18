package com.ar4i.quicknotes.presentation.note.notedetails.views;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.views.IMvpView;

import io.reactivex.Observable;

public interface INoteDetailsView extends IMvpView {
    Observable<Boolean> onEditIconClick();

    Observable<Boolean> onRemoveIconClick();

    NoteVm getNote();

    void setTitle(String title);

    void setCreationDate(String date);

    void setBody(String body);

    void showSuccessfulView();
}
