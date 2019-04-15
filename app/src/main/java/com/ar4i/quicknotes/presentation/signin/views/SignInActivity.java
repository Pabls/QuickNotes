package com.ar4i.quicknotes.presentation.signin.views;

import io.reactivex.Observable;

import android.widget.Button;
import android.widget.EditText;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseActivity;
import com.ar4i.quicknotes.presentation.main.MainActivity;
import com.ar4i.quicknotes.presentation.signin.presenter.SignInPresenter;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

public class SignInActivity extends BaseActivity implements ISignInView {

    //==========================================start Fields========================================

    @Inject
    SignInPresenter signInPresenter;

    //-------------------------------------------end Fields-----------------------------------------

    //==========================================start UI============================================

    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    EditText etEmail;
    EditText etPassword;
    Button btnEnter;

    //-------------------------------------------end UI---------------------------------------------


    //==========================================start extends BaseActivity==========================

    @Override
    public void inject() {
        getComponent().inject(this);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_in;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected IPresenter getPresenter() {
        return signInPresenter;
    }


    //-------------------------------------------end extends BaseActivity---------------------------


    //==========================================start implements ISignInView========================

    @Override
    public Observable<String> onEmailInput() {
        return RxTextView.textChanges(etEmail).map(CharSequence::toString);
    }

    @Override
    public Observable<String> onPasswordInput() {
        return RxTextView.textChanges(etPassword).map(CharSequence::toString);
    }

    @Override
    public Observable<Boolean> onEnterButtonClick() {
        return RxView.clicks(btnEnter).map(click -> true);
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void setEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void enableEnterButton(boolean enable) {
        btnEnter.setEnabled(enable);
    }

    @Override
    public void navigateToApp() {
        startActivity(MainActivity.getStartIntent(this));
    }

    //-------------------------------------------end implements ISignInView-------------------------


    //==========================================start Private methods===============================

    private void initView() {
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnEnter = findViewById(R.id.btn_enter);
    }

    //-------------------------------------------end Private methods--------------------------------
}
