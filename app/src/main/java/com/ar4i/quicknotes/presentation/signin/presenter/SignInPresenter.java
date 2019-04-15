package com.ar4i.quicknotes.presentation.signin.presenter;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.domain.resources.IResourceInteractor;
import com.ar4i.quicknotes.domain.signin.ISignInInteractor;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.signin.views.ISignInView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SignInPresenter extends BasePresenter<ISignInView> {

    @Inject
    public SignInPresenter(ISignInInteractor iSignInInteractor, IResourceInteractor iResourceInteractor) {
        this.iSignInInteractor = iSignInInteractor;
        this.iResourceInteractor = iResourceInteractor;
    }

    //==========================================start Fields========================================

    private ISignInInteractor iSignInInteractor;
    private IResourceInteractor iResourceInteractor;
    private boolean isEmailValid;
    private boolean isPasswordValid;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start extends BasePresenter<ISignInView>============

    @Override
    public void attachView(ISignInView view) {

        super.attachView(view);
        getUser();

        track(getView().onEmailInput().subscribe(email -> {
            if (email.isEmpty()) {
                showEmailError(false);
            } else {
                isEmailValid = iSignInInteractor.isEmailValid(email);
                showEmailError(!isEmailValid);
            }
            enableEnterButton();
        }));

        track(getView().onPasswordInput().subscribe(password -> {
            if (password.isEmpty()) {
                showPasswordError(false);
            } else {
                isPasswordValid = iSignInInteractor.isPasswordValid(password);
                showPasswordError(!isPasswordValid);
            }
            enableEnterButton();
        }));

        track(getView().onEnterButtonClick()
                .subscribe(click -> trySignIn(getView().getEmail(), getView().getPassword())));
    }

    //-------------------------------------------end extends BasePresenter<ISignInView>-------------


    //==========================================start Private methods===============================

    private void getUser(){
        track(iSignInInteractor.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    if (user != null && user.getUid() != null && !user.getUid().isEmpty()) {
                        getView().navigateToApp();
                    }
                }, error -> getView().showMessage(error.getMessage())));
    }

    private void trySignIn(String email, String password) {
        track(iSignInInteractor.signIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                    if (success) {
                        getView().navigateToApp();
                    } else {
                        tryCreateUser(email, password);
                    }
                }, error -> getView().showMessage(error.getMessage())));
    }


    private void tryCreateUser(String email, String password) {
        track(iSignInInteractor.createUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                    if (success) {
                        getView().navigateToApp();
                    }
                }, error -> getView().showMessage(error.getMessage())));
    }

    private void showEmailError(boolean show) {
        if (show) {
            getView().setEmailError(getStringById(R.string.sign_in_activity_text_input_layout_text_invalid_email));
        } else {
            getView().setEmailError(getStringById(R.string.common_empty));
        }
    }

    private void showPasswordError(boolean show) {
        if (show) {
            getView().setPasswordError(getStringById(R.string.sign_in_activity_text_input_layout_text_invalid_password));
        } else {
            getView().setPasswordError(getStringById(R.string.common_empty));
        }
    }

    private void enableEnterButton() {
        getView().enableEnterButton(isEmailValid && isPasswordValid);
    }

    private String getStringById(int id) {
        return iResourceInteractor.getStringById(id);
    }

    //-------------------------------------------end Private methods--------------------------------

}