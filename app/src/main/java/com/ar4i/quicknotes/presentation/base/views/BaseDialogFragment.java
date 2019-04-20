package com.ar4i.quicknotes.presentation.base.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.app.di.components.ApplicationComponent;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment implements IBaseMvpView {

    //==========================================start Lifecycle=====================================

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(getLayoutId(), null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getTitle())
                .setView(view)
                .setPositiveButton(getPositiveButtonText(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onPositiveButtonClick();
                    }
                })
                .setNegativeButton(getNegativeButtonText(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onNegativeButtonClick();
                    }
                });

        initView(view);

        if (getPresenter() != null)
            getPresenter().attachView(this);
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().detachView();
    }

    //-------------------------------------------end Lifecycle--------------------------------------

    //==========================================start Protected methods=============================

    protected ApplicationComponent getComponent() {
        return App.getApplicationComponent();
    }

    //-------------------------------------------end Protected methods------------------------------


    //==========================================start Abstract methods==============================

    protected abstract void inject();

    protected abstract int getLayoutId();

    protected abstract IPresenter<IBaseMvpView> getPresenter();

    protected abstract int getTitle();

    protected abstract int getPositiveButtonText();

    protected abstract int getNegativeButtonText();

    protected abstract void onPositiveButtonClick();

    protected abstract void onNegativeButtonClick();

    protected abstract void initView(View view);

    //-------------------------------------------end Abstract methods-------------------------------
}
