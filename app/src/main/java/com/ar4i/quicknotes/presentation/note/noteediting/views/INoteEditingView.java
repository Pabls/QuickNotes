package com.ar4i.quicknotes.presentation.note.noteediting.views;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.note.base.ISuccessView;

import io.reactivex.Observable;

public interface INoteEditingView extends ISuccessView {

    Observable<Boolean> onSaveButtonClick();

    Observable<String> onTitleChanged();

    Observable<String> onBodyChanged();

    String getTitle();

    String getBody();

    NoteVm getNote();

    void setTitle(String title);

    void setBody(String body);

    void enableSaveButton(boolean enable);
}
