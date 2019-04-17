package com.ar4i.quicknotes.presentation.notes.presenter;

import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.domain.auth.IAuthInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.notes.views.INotesView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotesPresenter extends BasePresenter<INotesView> {

    public NotesPresenter(INotesInteractor iNotesInteractor, IAuthInteractor iAuthInteractor) {
        this.iNotesInteractor = iNotesInteractor;
        this.iAuthInteractor = iAuthInteractor;
    }

    // region========================================Fields=========================================

    INotesInteractor iNotesInteractor;
    IAuthInteractor iAuthInteractor;
    List<NoteVm> notes = new ArrayList<>();

    // endregion-------------------------------------Fields-----------------------------------------

    //==========================================start extends BasePresenter<INotesView>=============

    @Override
    public void attachView(INotesView view) {
        super.attachView(view);
        getUser();
        track(getView().onListItemClick()
                .subscribe(index -> {
                    getView().showMessage(String.valueOf(index));
                }));
    }

    //-------------------------------------------end extends BasePresenter<INotesView>--------------

    // region========================================Private methods================================

    private void getUser() {
        track(iAuthInteractor.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(_void -> getView().showLoad())
                .doOnEvent((res, error) -> getView().hideLoad())
                .subscribe(user -> {
                    if (user != null && user.getUid() != null && !user.getUid().isEmpty()) {
                        getNotes(user.getUid());
                    }
                }, error -> getView().showMessage(error.getMessage())));
    }

    private void getNotes(String userId) {
        track(iNotesInteractor.getNotes(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> {
                    getView().showNoNotesMessage(notes == null || notes.isEmpty());
                    getView().setNotes(notes);
                }, error -> getView().showMessage(error.getMessage())));
    }

    // endregion-------------------------------------Private methods--------------------------------
}
