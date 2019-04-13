package com.ar4i.quicknotes.presentation.base.presenter;

import com.ar4i.quicknotes.presentation.base.view.IMvpView;

public interface IPresenter<V extends IMvpView> {
    void attachView(V view);

    void detachView();
}
