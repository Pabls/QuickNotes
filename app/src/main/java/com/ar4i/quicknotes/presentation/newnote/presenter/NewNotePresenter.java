package com.ar4i.quicknotes.presentation.newnote.presenter;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.data.models.UserVm;
import com.ar4i.quicknotes.domain.auth.IAuthInteractor;
import com.ar4i.quicknotes.domain.notes.INotesInteractor;
import com.ar4i.quicknotes.domain.resources.IResourceInteractor;
import com.ar4i.quicknotes.domain.tags.ITagsInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.newnote.views.INewNoteView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewNotePresenter extends BasePresenter<INewNoteView> {

    public NewNotePresenter(INotesInteractor iNotesInteractor,
                            IResourceInteractor iResourceInteractor,
                            ITagsInteractor iTagsInteractor) {
        this.iNotesInteractor = iNotesInteractor;
        this.iResourceInteractor = iResourceInteractor;
        this.iTagsInteractor = iTagsInteractor;
    }
    // region========================================Fields=========================================

    INotesInteractor iNotesInteractor;
    IResourceInteractor iResourceInteractor;
    ITagsInteractor iTagsInteractor;
    private String title;
    private String body;
    private List<TagVm> tagVms = new ArrayList<>();

    // endregion-------------------------------------Fields-----------------------------------------


    //==========================================start extends BasePresenter<INewNoteView>===========

    @Override
    public void attachView(INewNoteView view) {
        super.attachView(view);
        getLastNote();
        getTags();
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
                    List<TagVm> selectedTags = new ArrayList<>();
                    if (tagVms != null && !tagVms.isEmpty()) {
                        for (TagVm tagVm : tagVms) {
                            if (tagVm.isChecked()) {
                                selectedTags.add(tagVm);
                            }
                        }
                    }

                    NoteVm noteVm = new NoteVm(new Timestamp(System.currentTimeMillis()).getTime(),
                            getView().getTitle(),
                            getView().getBody(),
                            selectedTags);
                    sendNewNote(noteVm);
                }));

        track(getView().onTagClick()
                .subscribe(index -> tagVms.get(index).checkTag()));
    }

    //-------------------------------------------end extends BasePresenter<INewNoteView>------------

    // region========================================Public methods=================================

    public void saveLastNote() {
        if (!getView().getTitle().isEmpty() || !getView().getBody().isEmpty())
            saveNote(new NoteVm(getView().getTitle(), getView().getBody()));
    }

    // endregion-------------------------------------Public methods---------------------------------

    // region========================================Private methods================================

    private void sendNewNote(NoteVm noteVm) {
        track(iNotesInteractor.sendNote(noteVm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    clearInput();
                    clearTagsState();
                    deleteLastNote();
                    getView().notifyOfSuccess();
                }));
    }

    private void clearInput() {
        String emptyString = iResourceInteractor.getStringById(R.string.common_empty);
        getView().setTitle(emptyString);
        getView().setBody(emptyString);
    }

    private void saveNote(NoteVm noteVm) {
        track(iNotesInteractor.saveNote(noteVm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(_void -> getView().showLoad())
                .doOnTerminate(() -> getView().hideLoad())
                .subscribe());
    }

    private void getLastNote() {
        track(iNotesInteractor.getLastUnsavedNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(_void -> getView().showLoad())
                .doOnEvent((res, error) -> getView().hideLoad())
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

    private void getTags() {
        track(iTagsInteractor.getTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tag -> {
                    if(tag != null)
                    tagVms.add(tag);
                    getView().setTags(tagVms);
                }));
    }

    private void clearTagsState() {
        if (tagVms != null && !tagVms.isEmpty()) {
            for (TagVm tagVm : tagVms) {
                tagVm.setChecked(false);
            }
        }
    }

    // endregion-------------------------------------Private methods--------------------------------
}
