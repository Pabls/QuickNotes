package com.ar4i.quicknotes.presentation.tags.views;

import com.ar4i.quicknotes.presentation.base.views.IMvpView;

import io.reactivex.Observable;


public interface ITagsView extends IMvpView {

    Observable<Boolean> onColorLensIconClick();

    Observable<Boolean> onAddTagButtonClick();

    Observable<String> onTagChanged();

    String getTagName();

    int getColor();

    void enableAddTagButton(boolean enable);

    void setImageColor(int color);

    void setTag(String tag);

    void showColorSelectionDialog();

    void notifyOfSuccess();

}
