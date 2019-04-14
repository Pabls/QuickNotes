package com.ar4i.quicknotes.presentation.base.view;

import android.os.Bundle;
import android.widget.Toast;

import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.app.di.components.ApplicationComponent;
import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements IMvpView {

    //==========================================start Lifecycle=====================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        inject();

        getPresenter().attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }

    //-------------------------------------------end Lifecycle--------------------------------------

    //==========================================start implements MvpView============================

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //-------------------------------------------end -----------------------------------------------


    //==========================================start Protected methods=============================

    protected ApplicationComponent getComponent() {
        return App.getApplicationComponent();
    }

    //-------------------------------------------end Protected methods------------------------------


    //==========================================start Abstract methods==============================

    protected abstract void inject();

    protected abstract int getLayoutId();

    protected abstract BasePresenter<IMvpView> getPresenter();

    //-------------------------------------------end Abstract methods-------------------------------

}
