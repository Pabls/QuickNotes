package com.ar4i.quicknotes.presentation.tags.views;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ColorViewModel extends ViewModel {

    private MutableLiveData<Integer> colorMutableLiveData;

    public ColorViewModel() {
        colorMutableLiveData = new MutableLiveData<>();
    }

    public void setColor(int color) {
        colorMutableLiveData.postValue(color);
    }

    public LiveData<Integer> getColor() {
        return colorMutableLiveData;
    }
}

