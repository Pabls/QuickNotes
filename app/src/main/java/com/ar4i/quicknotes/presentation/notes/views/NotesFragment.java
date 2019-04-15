package com.ar4i.quicknotes.presentation.notes.views;

import android.os.Bundle;
import android.view.View;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.notes.presenter.NotesPresenter;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotesFragment extends BaseFragment implements INotesView {

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    // region========================================Fields=========================================

    @Inject
    NotesPresenter notesPresenter;

    // endregion-------------------------------------Fields-----------------------------------------


    // ==========================================start Lifecycle====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //initView();
        super.onViewCreated(view, savedInstanceState);
    }

    // -------------------------------------------end Lifecycle-------------------------------------


    // region========================================extends BaseFragment===========================

    @Override
    public void inject() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_notes;
    }

    @Override
    protected IPresenter getPresenter() {
        return notesPresenter;
    }

    // endregion-------------------------------------extends BaseFragment---------------------------


    // region========================================Private Methods================================
    // endregion-------------------------------------Private Methods--------------------------------

}
