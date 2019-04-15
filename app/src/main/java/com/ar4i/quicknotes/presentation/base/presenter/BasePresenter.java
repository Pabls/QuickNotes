package com.ar4i.quicknotes.presentation.base.presenter;

import com.ar4i.quicknotes.presentation.base.views.IMvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IMvpView> implements IPresenter<V> {

    //==========================================start Fields========================================

    private V view;
    private CompositeDisposable sub;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start implements IPresenter<V>======================

    @Override
    public void attachView(V view) {
        this.view = view;
        this.sub = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        this.view = null;
        if (this.sub != null) {
            this.sub.dispose();
            this.sub = null;
        }
    }

    //-------------------------------------------end implements IPresenter<V>-----------------------


    //==========================================start Protected methods=============================

    protected void track(Disposable disposable) {
        if (this.sub != null)
            this.sub.add(disposable);
    }

    protected V getView() {
        return this.view;
    }

    //-------------------------------------------end Protected methods------------------------------
}
