package com.ar4i.quicknotes.presentation.note.notedetails.views;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.note.base.ISuccessView;

import io.reactivex.Observable;

public interface INoteDetailsView extends ISuccessView {
    Observable<Boolean> onEditIconClick();

    Observable<Boolean> onRemoveIconClick();

    NoteVm getNote();

    void navigateToNoteEditingFragment(NoteVm note);

    void setTitle(String title);

    void setCreationDate(String date);

    void setBody(String body);
}
