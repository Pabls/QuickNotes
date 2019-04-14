package com.ar4i.quicknotes.presentation.note.noteediting.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.view.BaseFragment;
import com.ar4i.quicknotes.presentation.base.view.IMvpView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;

public class NoteEditingFragment extends BaseFragment implements INoteEditingView {

    //==========================================start UI============================================

    EditText etTitle;
    EditText etBody;
    FloatingActionButton fabSave;

    //-------------------------------------------end UI---------------------------------------------



    //==========================================start extends BaseFragment==========================

    @Override
    public void inject() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_note_editing;
    }

    @Override
    protected IPresenter<IMvpView> getPresenter() {
        return null;
    }

    //-------------------------------------------end extends BaseFragment---------------------------



    //==========================================start Lifecycle=====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    //-------------------------------------------end Lifecycle--------------------------------------



    //==========================================start Private methods===============================

    private void initView() {
        etTitle = getActivity().findViewById(R.id.tv_title);
        etBody = getActivity().findViewById(R.id.et_body);
        fabSave = getActivity().findViewById(R.id.fab_save);
    }

    //-------------------------------------------end Private methods--------------------------------



    //==========================================start implements INoteEditingView===================

    @Override
    public Observable<Boolean> onSaveButtonClick() {
        return RxView.clicks(fabSave).map(click -> true);
    }

    @Override
    public Observable<String> onTitleChanged() {
        return RxTextView.textChanges(etTitle).map(CharSequence::toString);
    }

    @Override
    public Observable<String> onBodyChanged() {
        return RxTextView.textChanges(etBody).map(CharSequence::toString);
    }

    @Override
    public String getTitle() {
        return etTitle.getText().toString();
    }

    @Override
    public String getBody() {
        return etBody.getText().toString();
    }

    @Override
    public void setTitle(String title) {
        etTitle.setText(title);
    }

    @Override
    public void setBody(String body) {
        etBody.setText(body);
    }

    @Override
    public void enableSaveButton(boolean enable) {
        fabSave.setEnabled(enable);
    }

    //-------------------------------------------end implements INoteEditingView--------------------
}
