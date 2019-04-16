package com.ar4i.quicknotes.presentation.notes.presenter;

import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.notes.views.INotesView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotesPresenter extends BasePresenter<INotesView> {

    public NotesPresenter(INotesInteractor iNotesInteractor) {
        this.iNotesInteractor = iNotesInteractor;
    }

    // region========================================Fields=========================================

    INotesInteractor iNotesInteractor;

    // endregion-------------------------------------Fields-----------------------------------------

    //==========================================start extends BasePresenter<INotesView>=============

    @Override
    public void attachView(INotesView view) {
        super.attachView(view);
        getNotes();
    }

    //-------------------------------------------end extends BasePresenter<INotesView>--------------

    // region========================================Private methods================================

    private void getNotes() {
        //-Lcb6p90cemveplprQe_
        track(iNotesInteractor.getNotes("-Lcb6p90cemveplprQe_")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    int i = 0;
                }, error -> {
                    getView().showMessage(error.getMessage());
                }));
    }

    // endregion-------------------------------------Private methods--------------------------------
}
