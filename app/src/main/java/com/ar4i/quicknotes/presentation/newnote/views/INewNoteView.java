package com.ar4i.quicknotes.presentation.newnote.views;

import com.ar4i.quicknotes.presentation.base.views.IMvpView;

import io.reactivex.Observable;

public interface INewNoteView extends IMvpView {

    Observable<Boolean> onSendButtonClick();

    Observable<String> onTitleChanged();

    Observable<String> onBodyChanged();

    String getTitle();

    String getBody();

    void enableSendButton(boolean enable);

    void setTitle(String title);

    void setBody(String body);
}
