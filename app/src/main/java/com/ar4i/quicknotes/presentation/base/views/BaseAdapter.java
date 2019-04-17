package com.ar4i.quicknotes.presentation.base.views;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {

    //==========================================start Fields========================================

    protected List<T> items = new ArrayList<>();
    private PublishSubject<Integer> itemViewClickSubject = PublishSubject.create();

    //-------------------------------------------end Fields-----------------------------------------

    //==========================================start Public methods================================

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.bind(getItem(i));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAllAndNotify(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Observable<Integer> itemClickEvent() {
        return itemViewClickSubject;
    }

    //-------------------------------------------end Public methods---------------------------------


    //==========================================start Protected methods=============================

    protected PublishSubject<Integer> getSubject() {
        return itemViewClickSubject;
    }

    protected List<T> getItems() {
        return items;
    }

    //-------------------------------------------end Protected methods------------------------------


    //==========================================start Private methods===============================

    private T getItem(int i) {
        return items.get(i);
    }

    //-------------------------------------------end Private methods--------------------------------

}

