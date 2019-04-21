package com.ar4i.quicknotes.presentation.newnote.views;

import android.content.res.ColorStateList;
import android.view.View;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.presentation.base.views.BaseViewHolder;
import com.google.android.material.chip.Chip;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import androidx.annotation.NonNull;
import io.reactivex.subjects.PublishSubject;

public class TagsViewHolder extends BaseViewHolder<TagVm> {

    // region========================================Fields============================================

    Chip chipTag;

    // endregion-------------------------------------Fields--------------------------------------------

    public TagsViewHolder(@NonNull View itemView, PublishSubject<Integer> itemViewClickSubject) {
        super(itemView, null);

        chipTag = itemView.findViewById(R.id.chip_tag);

        if (itemViewClickSubject != null) {
            RxCompoundButton.checkedChanges(chipTag)
                    .map(checked -> getAdapterPosition())
                    .filter(integer -> integer >= 0)
                    .subscribe(itemViewClickSubject);
        }
    }

    @Override
    public void bind(TagVm tag) {

        chipTag.setText(tag.getName());
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{new int[]{android.R.attr.state_enabled}},
                new int[]{tag.getColor()});

        chipTag.setChipBackgroundColor(colorStateList);
    }
}

