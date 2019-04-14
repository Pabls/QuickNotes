package com.ar4i.quicknotes.presentation.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.app.di.components.ApplicationComponent;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements IMvpView {

    //==========================================start Lifecycle=====================================

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getPresenter() != null)
            getPresenter().attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().detachView();
    }

    //-------------------------------------------end Lifecycle--------------------------------------

    //==========================================start implements MvpView============================

    @Override
    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //-------------------------------------------end -----------------------------------------------


    //==========================================start Protected methods=============================

    protected ApplicationComponent getComponent() {
        return App.getApplicationComponent();
    }

    //-------------------------------------------end Protected methods------------------------------


    //==========================================start Abstract methods==============================

    public abstract void inject();

    public abstract int getLayoutId();

    protected abstract IPresenter<IMvpView> getPresenter();

    //-------------------------------------------end Abstract methods-------------------------------

}