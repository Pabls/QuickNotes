package com.ar4i.quicknotes.presentation.newnote.presenter;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.UserVm;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.domain.signin.ISignInInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.newnote.views.INewNoteView;

import java.sql.Timestamp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewNotePresenter extends BasePresenter<INewNoteView> {

    public NewNotePresenter(INotesInteractor iNotesInteractor, ISignInInteractor iSignInInteractor) {
        this.iNotesInteractor = iNotesInteractor;
        this.iSignInInteractor = iSignInInteractor;
    }
    // region========================================Fields=========================================

    INotesInteractor iNotesInteractor;
    ISignInInteractor iSignInInteractor;
    private String title;
    private String body;
    private UserVm userVm;

    // endregion-------------------------------------Fields-----------------------------------------


    //==========================================start extends BasePresenter<INewNoteView>===========

    @Override
    public void attachView(INewNoteView view) {
        super.attachView(view);
        getLastNote();
        getUser();
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
                .subscribe(click -> {
                    NoteVm noteVm = new NoteVm(new Timestamp(System.currentTimeMillis()).getTime(),
                            getView().getTitle(),
                            getView().getBody(),
                            userVm.getUid());
                    sendNewNote(noteVm);
                }));
    }

    //-------------------------------------------end extends BasePresenter<INewNoteView>------------

    // region========================================Public methods=================================

    public void saveLastNote() {
        if (!getView().getTitle().isEmpty() || !getView().getBody().isEmpty())
            saveNote(new NoteVm(getView().getTitle(), getView().getBody()));
    }

    // endregion-------------------------------------Public methods---------------------------------

    // region========================================Private methods================================

    private void getUser() {
        track(iSignInInteractor.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    this.userVm = user;
                }, error -> getView().showMessage(error.getMessage())));
    }

    private void sendNewNote(NoteVm noteVm) {
        track(iNotesInteractor.sendNote(noteVm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getView().setTitle("");
                    getView().setBody("");
                }));
    }

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
