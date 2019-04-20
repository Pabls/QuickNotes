package com.ar4i.quicknotes.presentation.colorselection.views;

import com.ar4i.quicknotes.presentation.base.views.IBaseMvpView;

import io.reactivex.Observable;

public interface IColorSelectionView extends IBaseMvpView {

    Observable<Integer> onRedSeekBarChange();

    Observable<Integer> onGreenSeekBarChange();

    Observable<Integer> onBlueSeekBarChange();

    Observable<Integer> onColorPaletteClick();

    int getRedSeekBarValue();

    int getGreenSeekBarValue();

    int getBlueSeekBarValue();

    void setRedSeekBarValue(int value);

    void setGreenSeekBarValue(int value);

    void setBlueSeekBarValue(int value);

    void changeSelectedColorView(int color);
}
