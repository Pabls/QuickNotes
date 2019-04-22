package com.ar4i.quicknotes.presentation.newnote.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.newnote.presenter.NewNotePresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;

public class NewNoteFragment extends BaseFragment implements INewNoteView {

    public static NewNoteFragment newInstance() {
        return new NewNoteFragment();
    }

    // region========================================Fields=========================================

    @Inject
    NewNotePresenter newNotePresenter;
    TagsAdapter tagsAdapter;

    // endregion-------------------------------------Fields-----------------------------------------

    // region========================================UI=============================================

    TextInputLayout tilTitle;
    TextInputLayout tilBody;
    EditText etTitle;
    EditText etBody;
    FloatingActionButton fabSendNote;
    ConstraintLayout clContainer;
    Group group;
    RecyclerView rvTags;

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
    public Observable<Integer> onTagClick() {
        return tagsAdapter.itemClickEvent();
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
    public void setTags(List<TagVm> tagVms) {
        rvTags.setVisibility(tagVms != null && !tagVms.isEmpty() ? View.VISIBLE : View.GONE);
        tagsAdapter.addAllAndNotify(tagVms);
    }

    @Override
    public void enableSendButton(boolean enable) {
        fabSendNote.setEnabled(enable);
    }

    @Override
    public void setTitle(String title) {
        etTitle.setText(title);
        if (!title.isEmpty())
            etTitle.setSelection(etTitle.getText().length());
    }

    @Override
    public void setBody(String body) {
        etBody.setText(body);
        if (!body.isEmpty())
            etBody.setSelection(etBody.getText().length());
    }

    @Override
    public void notifyOfSuccess() {
        hideKeyboard();
        showSuccessfulView();
    }

    // endregion-------------------------------------implements INewNoteView------------------------

    // region========================================Private methods================================

    private void showSuccessfulView() {
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
                        removeSuccessView();
                    }
                });
    }

    private void hideKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void removeSuccessView() {
        clContainer.removeViewAt(clContainer.getChildCount() - 1);
        group.setVisibility(View.VISIBLE);
        etTitle.requestFocus();
        tagsAdapter.notifyDataSetChanged();
        tagsAdapter.clearState();
    }

    private void initView() {
        tagsAdapter = new TagsAdapter();
        tilTitle = getActivity().findViewById(R.id.til_title);
        tilBody = getActivity().findViewById(R.id.til_body);
        etTitle = getActivity().findViewById(R.id.et_title);
        etBody = getActivity().findViewById(R.id.et_body);
        fabSendNote = getActivity().findViewById(R.id.fab_send);
        clContainer = getActivity().findViewById(R.id.cl_container);
        group = getActivity().findViewById(R.id.group);
        rvTags = getActivity().findViewById(R.id.rv_tags);

        rvTags.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvTags.setAdapter(tagsAdapter);

        etTitle.clearFocus();
        etBody.clearFocus();
    }

    // endregion-------------------------------------Private methods--------------------------------

}
