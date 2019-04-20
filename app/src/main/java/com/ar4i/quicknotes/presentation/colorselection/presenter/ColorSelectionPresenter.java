package com.ar4i.quicknotes.presentation.colorselection.presenter;

import android.graphics.Color;

import com.ar4i.quicknotes.presentation.base.presenter.BasePresenter;
import com.ar4i.quicknotes.presentation.colorselection.views.IColorSelectionView;

import javax.inject.Inject;

public class ColorSelectionPresenter extends BasePresenter<IColorSelectionView> {

    @Inject
    public ColorSelectionPresenter() {
    }

    //==========================================start Fields========================================

    private static final int DEFAULT_RED_COLOR_VALUE = 255;
    private static final int DEFAULT_GREEN_COLOR_VALUE = 160;
    private static final int DEFAULT_BLUE_COLOR_VALUE = 0;

    private int redSeekBarValue;
    private int greenSeekBarValue;
    private int blueSeekBarValue;

    private int numberOfChangedSeekBars;

    //-------------------------------------------end Fields-----------------------------------------

    //==========================================start extends BasePresenter<IColorSelectionView>====

    @Override
    public void attachView(IColorSelectionView view) {
        super.attachView(view);

        setDefaultColorValue();

        track(getView().onRedSeekBarChange()
                .subscribe(value -> {
                    if (isAllSeekBarsChanged()) {
                        redSeekBarValue = value;
                        changeSelectedColor();
                    } else
                        setNumberOfChangedSeekBars(numberOfChangedSeekBars - 1);
                }));

        track(getView().onGreenSeekBarChange()
                .subscribe(value -> {
                    if (isAllSeekBarsChanged()) {
                        greenSeekBarValue = value;
                        changeSelectedColor();
                    } else
                        setNumberOfChangedSeekBars(numberOfChangedSeekBars - 1);
                }));

        track(getView().onBlueSeekBarChange()
                .subscribe(value -> {
                    if (isAllSeekBarsChanged()) {
                        blueSeekBarValue = value;
                        changeSelectedColor();
                    } else
                        setNumberOfChangedSeekBars(numberOfChangedSeekBars - 1);
                }));

        track(getView().onColorPaletteClick()
                .subscribe(color -> {
                    changeSelectedColor(color);
                    changeColorsValue(color);
                }));
    }

    //-------------------------------------------end extends BasePresenter<IColorSelectionView>-----


    //==========================================start Public methods================================

    public int getColor(){
        int red = getView().getRedSeekBarValue();
        int green = getView().getGreenSeekBarValue();
        int blue = getView().getBlueSeekBarValue();
        return Color.rgb(red, green, blue);
    }

    //-------------------------------------------end Public methods---------------------------------


    //==========================================start Private methods===============================

    private void setDefaultColorValue() {
        getView().setRedSeekBarValue(DEFAULT_RED_COLOR_VALUE);
        getView().setGreenSeekBarValue(DEFAULT_GREEN_COLOR_VALUE);
        getView().setBlueSeekBarValue(DEFAULT_BLUE_COLOR_VALUE);
        redSeekBarValue = DEFAULT_RED_COLOR_VALUE;
        greenSeekBarValue = DEFAULT_GREEN_COLOR_VALUE;
        blueSeekBarValue = DEFAULT_BLUE_COLOR_VALUE;
        setNumberOfChangedSeekBars(3);
    }

    private void changeSelectedColor() {
        changeSelectedColor(Color.rgb(redSeekBarValue, greenSeekBarValue, blueSeekBarValue));
    }

    private void changeSelectedColor(int color) {
        getView().changeSelectedColorView(color);
    }

    private void changeColorsValue(int color) {
        String hexColor = String.format("%06X", (0xFFFFFF & color));
        if (hexColor != null && hexColor.length() == 6) {
            redSeekBarValue = Integer.parseInt(hexColor.substring(0, 2), 16);
            greenSeekBarValue = Integer.parseInt(hexColor.substring(2, 4), 16);
            blueSeekBarValue = Integer.parseInt(hexColor.substring(4), 16);
        }
        changeSeekBarsValue();
    }

    private void changeSeekBarsValue() {
        setNumberOfChangedSeekBars(3);
        getView().setRedSeekBarValue(redSeekBarValue);
        getView().setGreenSeekBarValue(greenSeekBarValue);
        getView().setBlueSeekBarValue(blueSeekBarValue);
    }

    private void setNumberOfChangedSeekBars(int number) {
        numberOfChangedSeekBars = number;
    }

    private boolean isAllSeekBarsChanged() {
        return numberOfChangedSeekBars == 0;
    }

    //-------------------------------------------end Private methods--------------------------------
}

