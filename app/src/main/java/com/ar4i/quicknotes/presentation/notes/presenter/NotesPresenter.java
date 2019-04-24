package com.ar4i.quicknotes.presentation.notes.presenter;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.notes.views.INotesView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotesPresenter extends BasePresenter<INotesView> {

    public NotesPresenter(INotesInteractor iNotesInteractor) {
        this.iNotesInteractor = iNotesInteractor;
    }

    // region========================================Fields=========================================

    INotesInteractor iNotesInteractor;
    List<NoteVm> notes = new ArrayList<>();

    // endregion-------------------------------------Fields-----------------------------------------

    //==========================================start extends BasePresenter<INotesView>=============

    @Override
    public void attachView(INotesView view) {
        super.attachView(view);
        getAddedNotes();
        getDeletedNote();
        track(getView().onListItemClick()
                .subscribe(index -> getView().navigateToNoteActivity(notes.get(index))));
    }

    //-------------------------------------------end extends BasePresenter<INotesView>--------------

    // region========================================Private methods================================

    private void getAddedNotes() {
        track(iNotesInteractor.getAddedNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(note -> {
                    this.notes.add(0, note);
                    getView().showNoNotesMessage(notes == null || notes.isEmpty());
                    getView().setNotes(notes);
                }, error -> getView().showMessage(error.getMessage())));
    }

    private void getDeletedNote() {
        track(iNotesInteractor.getDeletedNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(note -> {
                    if (!this.notes.isEmpty()) {
                        notes.remove(note);
                    }
                    getView().setNotes(notes);
                }, error -> getView().showMessage(error.getMessage())));
    }
    // endregion-------------------------------------Private methods--------------------------------
}
