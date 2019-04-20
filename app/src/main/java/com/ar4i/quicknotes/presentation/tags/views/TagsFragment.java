package com.ar4i.quicknotes.presentation.tags.views;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.colorselection.views.ColorSelectionFragment;
import com.ar4i.quicknotes.presentation.tags.presenter.TagsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.Observable;

public class TagsFragment extends BaseFragment implements ITagsView {

    public static TagsFragment newInstance() {
        return new TagsFragment();
    }

    //==========================================start Fields========================================

    @Inject
    TagsPresenter tagsPresenter;
    ColorViewModel model;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start UI============================================

    View vSelectedColor;
    EditText etTagName;
    FloatingActionButton fabAdd;

    //-------------------------------------------end UI---------------------------------------------


    //==========================================start extends BaseFragment==========================


    @Override
    public void inject() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutId() {

        return R.layout.fragment_tags;
    }

    @Override
    protected IPresenter getPresenter() {
        return tagsPresenter;
    }

    //-------------------------------------------end extends BaseFragment---------------------------


    // ==========================================start Lifecycle====================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        initViewModel();
        super.onViewCreated(view, savedInstanceState);
    }

    // -------------------------------------------end Lifecycle-------------------------------------


    //==========================================start implements ITagsView==========================

    @Override
    public Observable<Boolean> onColorLensIconClick() {
        return RxView.clicks(vSelectedColor).map(o -> true);
    }

    @Override
    public Observable<Boolean> onAddTagButtonClick() {
        return RxView.clicks(fabAdd).map(o -> true);
    }

    @Override
    public Observable<String> onTagChanged() {
        return RxTextView.textChanges(etTagName).map(charSequence -> charSequence.toString());
    }

    @Override
    public String getTagName() {
        return etTagName.getText().toString();
    }

    @Override
    public int getColor() {
        return model.getColor().getValue();
    }

    @Override
    public void enableAddTagButton(boolean enable) {
        fabAdd.setEnabled(enable);
    }

    @Override
    public void setImageColor(int color) {
        vSelectedColor.setBackgroundColor(color);
    }

    @Override
    public void setTag(String tag) {
        etTagName.setText(tag);
    }

    @Override
    public void showColorSelectionDialog() {
        ColorSelectionFragment.newInstance().show(getActivity().getSupportFragmentManager(), ColorSelectionFragment.TAG);
    }


    //-------------------------------------------end implements ITagsView---------------------------


    //==========================================start Private methods===============================

    private void initView() {
        vSelectedColor = getActivity().findViewById(R.id.v_selected_color);
        etTagName = getActivity().findViewById(R.id.et_name);
        fabAdd = getActivity().findViewById(R.id.fab_add);
    }

    private void initViewModel() {
        model = ViewModelProviders.of(getActivity()).get(ColorViewModel.class);
        LiveData<Integer> data = model.getColor();
        if (data.getValue() == null)
            model.setColor(getBackgroundColor());
        data.observe(this, color -> {
            if (color != null) {
                setImageColor(color);
            }
        });
    }

    private int getBackgroundColor() {
        int color = Color.TRANSPARENT;
        Drawable background = vSelectedColor.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        return color;
    }

    //-------------------------------------------end Private methods--------------------------------

}
