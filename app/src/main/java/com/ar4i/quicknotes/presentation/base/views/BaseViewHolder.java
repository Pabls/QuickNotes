package com.ar4i.quicknotes.presentation.base.views;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(@NonNull View itemView, PublishSubject<Integer> itemViewClickSubject) {
        super(itemView);

        if(itemViewClickSubject != null) {
            RxView.clicks(itemView)
                    .map(aVoid -> getAdapterPosition())
                    .filter(integer -> integer >= 0)
                    .subscribe(itemViewClickSubject);
        }
    }

    public abstract void bind(T item);

    public void bind(T item, int position) {
        bind(item);
    }

}

