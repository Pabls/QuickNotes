package com.ar4i.quicknotes.presentation.note.noteediting.view;

import com.ar4i.quicknotes.presentation.base.view.IMvpView;

import io.reactivex.Observable;

public interface INoteEditingView extends IMvpView {

    Observable<Boolean> onSaveButtonClick();

    Observable<String> onTitleChanged();

    Observable<String> onBodyChanged();

    String getTitle();

    String getBody();

    void setTitle(String title);

    void setBody(String body);

    void enableSaveButton(boolean enable);

}
