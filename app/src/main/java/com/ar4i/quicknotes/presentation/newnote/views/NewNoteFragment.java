package com.ar4i.quicknotes.presentation.newnote.views;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.newnote.presenter.NewNotePresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;

public class NewNoteFragment extends BaseFragment implements INewNoteView {

    public static NewNoteFragment newInstance() {
        return new NewNoteFragment();
    }

    // region========================================Fields=========================================

    @Inject
    NewNotePresenter newNotePresenter;

    // endregion-------------------------------------Fields-----------------------------------------

    // region========================================UI=============================================

    TextInputLayout tilTitle;
    TextInputLayout tilBody;
    EditText etTitle;
    EditText etBody;
    FloatingActionButton fabSendNote;

    // endregion-------------------------------------UI---------------------------------------------

    // ==========================================start Lifecycle====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStop() {
        newNotePresenter.saveLastNote();
        super.onStop();
    }

    // -------------------------------------------end Lifecycle-------------------------------------

    // region========================================extends BaseFragment===========================

    @Override
    public void inject() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_note;
    }

    @Override
    protected IPresenter getPresenter() {
        return newNotePresenter;
    }

    // endregion-------------------------------------extends BaseFragment---------------------------


    // region========================================implements INewNoteView========================

    @Override
    public Observable<Boolean> onSendButtonClick() {
        return RxView.clicks(fabSendNote).map(click -> true);
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
    public void enableSendButton(boolean enable) {
        fabSendNote.setEnabled(enable);
    }

    @Override
    public void setTitle(String title) {
        etTitle.setText(title);
    }

    @Override
    public void setBody(String body) {
        etBody.setText(body);
    }

    // endregion-------------------------------------implements INewNoteView------------------------

    // region========================================Private methods================================

    private void initView() {
        tilTitle = getActivity().findViewById(R.id.til_title);
        tilBody = getActivity().findViewById(R.id.til_body);
        etTitle = getActivity().findViewById(R.id.et_title);
        etBody = getActivity().findViewById(R.id.et_body);
        fabSendNote = getActivity().findViewById(R.id.fab_send);
    }

    // endregion-------------------------------------Private methods--------------------------------

}
