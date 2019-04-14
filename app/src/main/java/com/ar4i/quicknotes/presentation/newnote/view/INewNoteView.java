package com.ar4i.quicknotes.presentation.newnote.view;

import com.ar4i.quicknotes.presentation.base.view.IMvpView;

import io.reactivex.Observable;

public interface INewNoteView extends IMvpView {

    Observable<Boolean> onSendButtonClick();

    Observable<String> onTitleChanged();

    Observable<String> onBodyChanged();

    String getTitle();

    String getBody();

    void enableSendButton(boolean enable);
}
