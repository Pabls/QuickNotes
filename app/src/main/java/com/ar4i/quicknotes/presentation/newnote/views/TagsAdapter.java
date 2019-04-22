package com.ar4i.quicknotes.presentation.newnote.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.presentation.base.views.BaseAdapter;

import androidx.annotation.NonNull;

public class TagsAdapter extends BaseAdapter<TagVm, TagsViewHolder> {
    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tags, parent, false);
        return new TagsViewHolder(view, getSubject());
    }

    public void clearState() {
        if (viewHolders != null && !viewHolders.isEmpty()) {
            for (TagsViewHolder tagsViewHolder : viewHolders) {
                if (tagsViewHolder.chipTag != null) {
                    tagsViewHolder.chipTag.setChecked(false);
                }
            }
        }
    }
}
