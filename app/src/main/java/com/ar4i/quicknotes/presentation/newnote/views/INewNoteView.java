package com.ar4i.quicknotes.presentation.newnote.views;

import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.presentation.base.views.IMvpView;

import java.util.List;

import io.reactivex.Observable;

public interface INewNoteView extends IMvpView {

    Observable<Boolean> onSendButtonClick();

    Observable<String> onTitleChanged();

    Observable<String> onBodyChanged();

    Observable<Integer> onTagClick();

    String getTitle();

    String getBody();

    void setTags(List<TagVm> tagVms);

    void enableSendButton(boolean enable);

    void setTitle(String title);

    void setBody(String body);

    void notifyOfSuccess();
}
