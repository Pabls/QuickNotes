package com.ar4i.quicknotes.presentation.notes.presenter;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.notes.views.INotesView;
import com.ar4i.quicknotes.presentation.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

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
        getNotes();
        track(getView().onListItemClick()
                .subscribe(index -> getView().navigateToNoteActivity(notes.get(index))));
    }

    //-------------------------------------------end extends BasePresenter<INotesView>--------------

    // region========================================Private methods================================

    private void getNotes() {
        track(iNotesInteractor.getNotes()
                .compose(upstream -> RxUtils.applySchedulers(upstream))
                .subscribe(notes -> {
                    this.notes = notes;
                    getView().showNoNotesMessage(notes.isEmpty());
                    getView().setNotes(notes);
                }, error -> getView().showMessage(error.getMessage())));
    }

    // endregion-------------------------------------Private methods--------------------------------
}
