package com.ar4i.quicknotes.presentation.newnote.presenter;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.newnote.views.INewNoteView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewNotePresenter extends BasePresenter<INewNoteView> {

    public NewNotePresenter(INotesInteractor iNotesInteractor) {
        this.iNotesInteractor = iNotesInteractor;
    }
    // region========================================Fields=========================================

    INotesInteractor iNotesInteractor;
    private String title;
    private String body;

    // endregion-------------------------------------Fields-----------------------------------------


    //==========================================start extends BasePresenter<INewNoteView>===========

    @Override
    public void attachView(INewNoteView view) {
        super.attachView(view);
        getLastNote();
        track(getView().onTitleChanged()
                .subscribe(title -> {
                    this.title = title;
                    enableSendButton();
                }));

        track(getView().onBodyChanged()
                .subscribe(body -> {
                    this.body = body;
                    enableSendButton();
                }));

        track(getView().onSendButtonClick()
                .subscribe());
    }

    //-------------------------------------------end extends BasePresenter<INewNoteView>------------

    // region========================================Public methods=================================

    public void saveLastNote() {
        if (!getView().getTitle().isEmpty() || !getView().getBody().isEmpty())
            saveNote(new NoteVm(getView().getTitle(), getView().getBody()));
    }

    // endregion-------------------------------------Public methods---------------------------------

    // region========================================Private methods================================

    private void saveNote(NoteVm noteVm) {
        track(iNotesInteractor.saveNote(noteVm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    private void getLastNote() {
        track(iNotesInteractor.getLastSavedNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(noteVm -> {
                    if (noteVm != null && noteVm.getTitle() != null && !noteVm.getTitle().isEmpty()) {
                        getView().setTitle(noteVm.getTitle());
                        getView().setBody(noteVm.getBody());
                        deleteLastNote();
                    }
                }));
    }

    private void deleteLastNote() {
        track(iNotesInteractor.deleteLastNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    private void enableSendButton() {
        getView().enableSendButton(!title.isEmpty() && !body.isEmpty());
    }

    // endregion-------------------------------------Private methods--------------------------------
}
