package com.ar4i.quicknotes.presentation.base.views;

public interface IMvpView extends IBaseMvpView {
    void showMessage(String mesage);
    void showLoad();
    void hideLoad();
}
