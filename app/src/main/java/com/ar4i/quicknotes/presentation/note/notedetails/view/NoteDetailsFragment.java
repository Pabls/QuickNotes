package com.ar4i.quicknotes.presentation.note.notedetails.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.view.BaseFragment;
import com.ar4i.quicknotes.presentation.base.view.IMvpView;
import com.jakewharton.rxbinding2.view.RxView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;

public class NoteDetailsFragment extends BaseFragment implements INoteDetailsView {

    //==========================================start UI============================================

    TextView tvTitle;
    TextView tvCreationDate;
    TextView tvBody;
    ImageView imgEdit;
    ImageView imgRemove;

    //-------------------------------------------end UI---------------------------------------------



    //==========================================start Lifecycle=====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    //-------------------------------------------end Lifecycle--------------------------------------



    //==========================================start extends BaseFragment==========================

    @Override
    public void inject() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_note_details;
    }

    @Override
    protected IPresenter<IMvpView> getPresenter() {
        return null;
    }

    //-------------------------------------------end extends BaseFragment---------------------------



    //==========================================start Private methods===============================

    private void initView() {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        tvCreationDate = getActivity().findViewById(R.id.tv_creation_date);
        tvBody = getActivity().findViewById(R.id.tv_body);
        imgEdit = getActivity().findViewById(R.id.img_edit);
        imgRemove = getActivity().findViewById(R.id.img_remove);
    }

    //-------------------------------------------end Private methods--------------------------------



    //==========================================start implements INoteDetailsView===================

    @Override
    public Observable<Boolean> onEditIconClick() {
        return RxView.clicks(imgEdit).map(click -> true);
    }

    @Override
    public Observable<Boolean> onRemoveIconClick() {
        return RxView.clicks(imgRemove).map(click -> true);
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setCreationDate(String date) {
        tvCreationDate.setText(date);
    }

    @Override
    public void setBody(String body) {
        tvBody.setText(body);
    }

    //-------------------------------------------end implements INoteDetailsView--------------------
}
