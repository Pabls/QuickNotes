package com.ar4i.quicknotes.presentation.signin.views;

import com.ar4i.quicknotes.presentation.base.views.IMvpView;

import io.reactivex.Observable;

public interface ISignInView extends IMvpView {

    Observable<String> onEmailInput();

    Observable<String> onPasswordInput();

    Observable<Boolean> onEnterButtonClick();

    String getEmail();

    String getPassword();

    void setEmailError(String error);

    void setPasswordError(String error);

    void enableEnterButton(boolean enable);

    void navigateToApp();
}
