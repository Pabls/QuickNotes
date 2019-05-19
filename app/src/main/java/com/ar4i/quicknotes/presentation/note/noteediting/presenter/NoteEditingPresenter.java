package com.ar4i.quicknotes.presentation.note.noteediting.presenter;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.note.noteediting.views.INoteEditingView;
import com.ar4i.quicknotes.presentation.utils.RxUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoteEditingPresenter extends BasePresenter<INoteEditingView> {

    public NoteEditingPresenter(INotesInteractor iNotesInteractor) {
        this.iNotesInteractor = iNotesInteractor;
    }

    // region========================================Field==========================================

    INotesInteractor iNotesInteractor;
    private NoteVm note;

    // endregion-------------------------------------Field------------------------------------------

    // region========================================extends BasePresenter<INoteEditingView>========

    @Override
    public void attachView(INoteEditingView view) {
        super.attachView(view);
        note = getView().getNote();
        setDataToView(note);

        track(getView().onTitleChanged()
                .subscribe(title -> {
                    note.setTitle(title);
                    enableSaveButton();
                }));

        track(getView().onBodyChanged()
                .subscribe(body -> {
                    note.setBody(body);
                    enableSaveButton();
                }));

        track(getView().onSaveButtonClick()
                .subscribe(click -> saveNote(note)));
    }

    // endregion-------------------------------------extends BasePresenter<INoteEditingView>--------

    // region========================================Private methods================================


    private void enableSaveButton() {
        getView().enableSaveButton(note != null &&
                note.getBody() != null &&
                note.getTitle() != null &&
                !note.getTitle().isEmpty() &&
                !note.getBody().isEmpty()
        );
    }

    private void setDataToView(NoteVm noteVm) {
        getView().setBody(noteVm.getBody());
        getView().setTitle(noteVm.getTitle());
    }

    private void saveNote(NoteVm noteVm) {
        track(iNotesInteractor.updateNote(noteVm)
                .compose(upstream -> RxUtils.applySchedulers(upstream))
                .subscribe(() -> getView().showSuccessfulView()));
    }

    // endregion-------------------------------------Private methods--------------------------------

}
