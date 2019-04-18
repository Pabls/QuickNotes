package com.ar4i.quicknotes.presentation.note.notedetails.presenter;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.note.notedetails.views.INoteDetailsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoteDetailsPresenter extends BasePresenter<INoteDetailsView> {

    public NoteDetailsPresenter(INotesInteractor iNotesInteractor) {
        this.iNotesInteractor = iNotesInteractor;
    }

    // region========================================Fileds=========================================

    INotesInteractor iNotesInteractor;
    private NoteVm noteVm;

    // endregion-------------------------------------Fileds-----------------------------------------

    // region========================================extends BasePresenter<INoteDetailsView>========

    @Override
    public void attachView(INoteDetailsView view) {
        super.attachView(view);
        noteVm = getView().getNote();
        setDataToView(noteVm);

        track(getView().onEditIconClick()
                .subscribe(click -> {

                }));

        track(getView().onRemoveIconClick()
                .subscribe(click -> removeNote(noteVm)));
    }

    // endregion-------------------------------------extends BasePresenter<INoteDetailsView>--------

    // region========================================Private methods================================

    private void setDataToView(NoteVm noteVm) {
        getView().setBody(noteVm.getBody());
        getView().setTitle(noteVm.getTitle());
        getView().setCreationDate(noteVm.getCreationDate());
    }

    private void removeNote(NoteVm noteVm) {
        track(iNotesInteractor.removeNote(noteVm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                }, error -> {

                }));
    }

    // endregion-------------------------------------Private methods--------------------------------
}
