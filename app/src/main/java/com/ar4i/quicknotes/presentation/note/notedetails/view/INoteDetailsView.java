package com.ar4i.quicknotes.presentation.note.notedetails.view;

import com.ar4i.quicknotes.presentation.base.view.IMvpView;

import io.reactivex.Observable;

public interface INoteDetailsView extends IMvpView {
    Observable<Boolean> onEditIconClick();

    Observable<Boolean> onRemoveIconClick();

    void setTitle(String title);

    void setCreationDate(String date);

    void setBody(String body);
}
