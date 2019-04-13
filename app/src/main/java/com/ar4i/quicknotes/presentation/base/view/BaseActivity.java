package com.ar4i.quicknotes.presentation.base.view;

import android.os.Bundle;
import android.widget.Toast;

import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements IMvpView {

    //==========================================start Fields========================================
    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start Lifecycle=====================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject();

        setContentView(getLayoutId());

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


    //==========================================start Private methods===============================
    //-------------------------------------------end Private methods--------------------------------


    //==========================================start Abstract methods==============================

    public abstract void inject();

    public abstract int getLayoutId();

    protected abstract IPresenter<IMvpView> getPresenter();

    //-------------------------------------------end Abstract methods-------------------------------

}
