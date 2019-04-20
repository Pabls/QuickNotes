package com.ar4i.quicknotes.presentation.base.presenter;

import com.ar4i.quicknotes.presentation.base.views.IBaseMvpView;

public interface IPresenter<V extends IBaseMvpView> {
    void attachView(V view);
    void detachView();
}
