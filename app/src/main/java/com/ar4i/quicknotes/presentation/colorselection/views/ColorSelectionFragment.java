package com.ar4i.quicknotes.presentation.colorselection.views;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;
import com.ar4i.quicknotes.presentation.base.views.BaseDialogFragment;
import com.ar4i.quicknotes.presentation.colorselection.presenter.ColorSelectionPresenter;
import com.ar4i.quicknotes.presentation.tags.views.ColorViewModel;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxSeekBar;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.Observable;

public class ColorSelectionFragment extends BaseDialogFragment implements IColorSelectionView {

    public static final String TAG = "ColorSelectionFragment";

    public static ColorSelectionFragment newInstance() {
        return new ColorSelectionFragment();
    }

    //==========================================start Fields========================================

    @Inject
    ColorSelectionPresenter colorSelectionPresenter;
    private ColorViewModel model;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start UI============================================

    View vSelectedColor;
    SeekBar sbRed;
    SeekBar sbGreen;
    SeekBar sbBlue;
    View vRed;
    View vMullbery;
    View vViolet;
    View vGrape;
    View vDarkBlue;
    View vLightBlue;
    View vAzure;
    View vSky;
    View vAquamarine;
    View vEmerald;

    //-------------------------------------------end UI---------------------------------------------


    // ==========================================start Lifecycle====================================

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(ColorViewModel.class);
    }

    // -------------------------------------------end Lifecycle-------------------------------------


    //==========================================start extends BaseDialogFragment====================

    @Override
    public void inject() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_color_selection;
    }

    @Override
    protected IPresenter getPresenter() {
        return colorSelectionPresenter;
    }

    @Override
    protected int getTitle() {
        return R.string.color_selection_fragment_text_title;
    }

    @Override
    protected int getPositiveButtonText() {
        return R.string.color_selection_fragment_positive_button_text;
    }

    @Override
    protected int getNegativeButtonText() {
        return R.string.color_selection_fragment_negative_button_text;
    }

    @Override
    protected void onPositiveButtonClick() {
        if (model != null)
            model.setColor(colorSelectionPresenter.getColor());
        model = null;
        dismiss();
    }

    @Override
    protected void onNegativeButtonClick() {
       model = null;
        dismiss();
    }

    //-------------------------------------------end extends BaseDialogFragment---------------------


    //==========================================start implements IColorSelectionView================


    @Override
    public void setRedSeekBarValue(int value) {
        sbRed.setProgress(value);
    }

    @Override
    public void setGreenSeekBarValue(int value) {
        sbGreen.setProgress(value);
    }

    @Override
    public void setBlueSeekBarValue(int value) {
        sbBlue.setProgress(value);
    }

    @Override
    public void changeSelectedColorView(int color) {
        vSelectedColor.setBackgroundColor(color);
    }

    @Override
    public Observable<Integer> onRedSeekBarChange() {
        return RxSeekBar.changes(sbRed);
    }

    @Override
    public Observable<Integer> onGreenSeekBarChange() {
        return RxSeekBar.changes(sbGreen);
    }

    @Override
    public Observable<Integer> onBlueSeekBarChange() {
        return RxSeekBar.changes(sbBlue);
    }

    @Override
    public Observable<Integer> onColorPaletteClick() {
        return Observable.merge(
                onFirstFourColorsClick(),
                onSecondFourColorsClick(),
                onThirdFourColorsClick()
        );
    }

    @Override
    public int getRedSeekBarValue() {
        return sbRed.getProgress();
    }

    @Override
    public int getGreenSeekBarValue() {
        return sbGreen.getProgress();
    }

    @Override
    public int getBlueSeekBarValue() {
        return sbBlue.getProgress();
    }


    //-------------------------------------------end implements IColorSelectionView-----------------


    //==========================================start Private methods===============================

    protected void initView(View view) {
        vSelectedColor = view.findViewById(R.id.v_selected_color);
        sbRed = view.findViewById(R.id.sb_red);
        sbGreen = view.findViewById(R.id.sb_green);
        sbBlue = view.findViewById(R.id.sb_blue);
        vRed = view.findViewById(R.id.v_red);
        vMullbery = view.findViewById(R.id.v_mulberry);
        vViolet = view.findViewById(R.id.v_violet);
        vGrape = view.findViewById(R.id.v_grape);
        vDarkBlue = view.findViewById(R.id.v_dark_blue);
        vLightBlue = view.findViewById(R.id.v_light_blue);
        vAzure = view.findViewById(R.id.v_azure);
        vSky = view.findViewById(R.id.v_sky);
        vAquamarine = view.findViewById(R.id.v_aquamarine);
        vEmerald = view.findViewById(R.id.v_emerald);
    }

    private Observable<Integer> onFirstFourColorsClick() {
        return Observable.merge(
                RxView.clicks(vRed).map(o -> getBackgroundColor(vRed)),
                RxView.clicks(vMullbery).map(o -> getBackgroundColor(vMullbery)),
                RxView.clicks(vViolet).map(o -> getBackgroundColor(vViolet)),
                RxView.clicks(vGrape).map(o -> getBackgroundColor(vGrape))
        );
    }

    private Observable<Integer> onSecondFourColorsClick() {
        return Observable.merge(
                RxView.clicks(vDarkBlue).map(o -> getBackgroundColor(vDarkBlue)),
                RxView.clicks(vLightBlue).map(o -> getBackgroundColor(vLightBlue)),
                RxView.clicks(vAzure).map(o -> getBackgroundColor(vAzure)),
                RxView.clicks(vSky).map(o -> getBackgroundColor(vSky))
        );
    }

    private Observable<Integer> onThirdFourColorsClick() {
        return Observable.merge(
                RxView.clicks(vAquamarine).map(o -> getBackgroundColor(vAquamarine)),
                RxView.clicks(vEmerald).map(o -> getBackgroundColor(vEmerald))
        );
    }

    private int getBackgroundColor(View view) {
        int color = Color.TRANSPARENT;
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        return color;
    }

    //-------------------------------------------end Private methods--------------------------------
}
