package com.ar4i.quicknotes.presentation.note.notedetails.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.note.notedetails.presenter.NoteDetailsPresenter;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import io.reactivex.Observable;

public class NoteDetailsFragment extends BaseFragment implements INoteDetailsView {

    private static final String EXTRA_NOTE = "com.ar4i.quicknotes.extra_note";

    public static NoteDetailsFragment newInstance(NoteVm noteVm) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_NOTE, noteVm);
        fragment.setArguments(args);
        return fragment;
    }

    // region========================================Fields=========================================

    @Inject
    NoteDetailsPresenter noteDetailsPresenter;
    NoteVm note;

    // endregion-------------------------------------Fields-----------------------------------------


    //==========================================start UI============================================

    TextView tvTitle;
    TextView tvCreationDate;
    TextView tvBody;
    ImageView imgEdit;
    ImageView imgRemove;
    ConstraintLayout clContainer;
    Group group;

    //-------------------------------------------end UI---------------------------------------------


    //==========================================start Lifecycle=====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            NoteVm noteVm = (NoteVm) getArguments().getSerializable(EXTRA_NOTE);
            setNote(noteVm);
        } catch (Exception e) {
            note = null;
        }

        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    //-------------------------------------------end Lifecycle--------------------------------------


    //==========================================start extends BaseFragment==========================

    @Override
    public void inject() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_note_details;
    }

    @Override
    protected IPresenter getPresenter() {
        return noteDetailsPresenter;
    }

    //-------------------------------------------end extends BaseFragment---------------------------


    //==========================================start Private methods===============================

    private void initView() {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        tvCreationDate = getActivity().findViewById(R.id.tv_creation_date);
        tvBody = getActivity().findViewById(R.id.tv_body);
        imgEdit = getActivity().findViewById(R.id.img_edit);
        imgRemove = getActivity().findViewById(R.id.img_remove);
        clContainer = getActivity().findViewById(R.id.cl_container);
        group = getActivity().findViewById(R.id.group);
    }

    private void setNote(NoteVm note) {
        this.note = note;
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
    public NoteVm getNote() {
        return note;
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

    @Override
    public void showSuccessfulView() {
        group.setVisibility(View.GONE);
        View.inflate(getActivity(), R.layout.view_done, clContainer);
        clContainer.setAlpha(0.0f);
        clContainer.animate()
                .setDuration(2000)
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        getActivity().onBackPressed();
                    }
                });
    }

    //-------------------------------------------end implements INoteDetailsView--------------------

}
