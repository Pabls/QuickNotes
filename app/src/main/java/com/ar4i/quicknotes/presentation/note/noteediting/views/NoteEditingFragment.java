package com.ar4i.quicknotes.presentation.note.noteediting.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.note.noteediting.presenter.NoteEditingPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import io.reactivex.Observable;

public class NoteEditingFragment extends BaseFragment implements INoteEditingView {

    private static final String EXTRA_NOTE = "com.ar4i.quicknotes.extra_note";

    public static NoteEditingFragment newInstance(NoteVm noteVm) {
        NoteEditingFragment fragment = new NoteEditingFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_NOTE, noteVm);
        fragment.setArguments(args);
        return fragment;
    }

    // region========================================Fields=========================================

    @Inject
    NoteEditingPresenter noteEditingPresenter;
    NoteVm note;

    // endregion-------------------------------------Fields-----------------------------------------


    //==========================================start UI============================================

    EditText etTitle;
    EditText etBody;
    FloatingActionButton fabSave;
    Group group;
    ConstraintLayout clContainer;

    //-------------------------------------------end UI---------------------------------------------


    //==========================================start Lifecycle=====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        try {
            NoteVm noteVm = (NoteVm) getArguments().getSerializable(EXTRA_NOTE);
            setNote(noteVm);
        } catch (Exception e) {
            note = null;
        }


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
        return R.layout.fragment_note_editing;
    }

    @Override
    protected IPresenter getPresenter() {
        return noteEditingPresenter;
    }

    //-------------------------------------------end extends BaseFragment---------------------------


    //==========================================start Private methods===============================

    private void initView() {
        etTitle = getActivity().findViewById(R.id.et_title);
        etBody = getActivity().findViewById(R.id.et_body);
        fabSave = getActivity().findViewById(R.id.fab_save);
        clContainer = getActivity().findViewById(R.id.cl_container);
        group = getActivity().findViewById(R.id.group);
    }

    private void setNote(NoteVm note) {
        this.note = note;
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
    public NoteVm getNote() {
        return note;
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

    @Override
    public void showSuccessfulView() {
        group.setVisibility(View.GONE);
        View.inflate(getActivity(), R.layout.view_done, clContainer);
        clContainer.setAlpha(0.0f);
        clContainer.animate()
                .setDuration(1500)
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (getActivity() != null)
                            getActivity().onBackPressed();
                    }
                });
    }

    //-------------------------------------------end implements INoteEditingView--------------------

}
