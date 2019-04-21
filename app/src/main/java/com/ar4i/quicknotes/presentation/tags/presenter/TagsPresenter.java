package com.ar4i.quicknotes.presentation.tags.presenter;

import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.data.models.UserVm;
import com.ar4i.quicknotes.domain.auth.IAuthInteractor;
import com.ar4i.quicknotes.domain.tags.ITagsInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.tags.views.ITagsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TagsPresenter extends BasePresenter<ITagsView> {

    @Inject
    public TagsPresenter(ITagsInteractor iTagsInteractor, IAuthInteractor iAuthInteractor) {
        this.iTagsInteractor = iTagsInteractor;
        this.iAuthInteractor = iAuthInteractor;
    }

    //==========================================start Fields========================================

    ITagsInteractor iTagsInteractor;
    IAuthInteractor iAuthInteractor;
    private UserVm userVm;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start extends BasePresenter<ITagsView>==============

    @Override
    public void attachView(ITagsView view) {
        super.attachView(view);

        getUser();

        track(getView().onColorLensIconClick()
                .subscribe(click -> getView().showColorSelectionDialog()));

        track(getView().onTagChanged()
                .subscribe(value -> getView().enableAddTagButton(value != null && !value.isEmpty())));

        track(getView().onAddTagButtonClick()
                .subscribe(click -> {
                    String name = getView().getTagName();
                    int color = getView().getColor();
                    sendTag(new TagVm(name, color, userVm.getUid()));
                }));


    }

    //-------------------------------------------end extends BasePresenter<ITagsView>---------------


    //==========================================start Private methods===============================

    private void getUser() {
        track(iAuthInteractor.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(_void -> getView().showLoad())
                .doOnEvent((res, error) -> getView().hideLoad())
                .subscribe(user -> {
                    this.userVm = user;
                }, error -> getView().showMessage(error.getMessage())));
    }

    private void sendTag(TagVm tagVm) {
        track(iTagsInteractor.sendTag(tagVm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getView().notifyOfSuccess();
                }));

    }

    //-------------------------------------------end Private methods--------------------------------
}
