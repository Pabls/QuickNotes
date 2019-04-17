package com.ar4i.quicknotes.presentation.notes.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.note.root.NoteActivity;
import com.ar4i.quicknotes.presentation.notes.presenter.NotesPresenter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;

public class NotesFragment extends BaseFragment implements INotesView {

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    // region========================================Fields=========================================

    @Inject
    NotesPresenter notesPresenter;
    NotesAdapter notesAdapter;

    // endregion-------------------------------------Fields-----------------------------------------


    //==========================================start UI============================================

    TextView tvNoNotes;
    RecyclerView rvNotes;

    //-------------------------------------------end UI---------------------------------------------


    // ==========================================start Lifecycle====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
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


    //==========================================start implements INotesView=========================

    @Override
    public Observable<Integer> onListItemClick() {
        return notesAdapter.itemClickEvent();
    }


    @Override
    public void setNotes(List<NoteVm> notes) {
        notesAdapter.addAllAndNotify(notes);
    }

    @Override
    public void showNoNotesMessage(boolean show) {
        tvNoNotes.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void navigateToNoteActivity() {
        getActivity().startActivity(new Intent(getActivity(), NoteActivity.class));
    }

    //-------------------------------------------end implements INotesView--------------------------


    // region========================================Private Methods================================

    private void initView() {
        notesAdapter = new NotesAdapter();
        tvNoNotes = getActivity().findViewById(R.id.tv_no_notes);
        rvNotes = getActivity().findViewById(R.id.rv_notes);

        rvNotes.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvNotes.setAdapter(notesAdapter);
    }


    // endregion-------------------------------------Private Methods--------------------------------

}
