package com.ar4i.quicknotes.presentation.notes.views;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.presentation.base.views.BaseViewHolder;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import io.reactivex.subjects.PublishSubject;

public class NotesViewHolder extends BaseViewHolder<NoteVm> {

    // region========================================Fields============================================

    TextView tvTitle;
    TextView tvCreationDate;
    TextView tvBody;
    ChipGroup cgTags;

    // endregion-------------------------------------Fields--------------------------------------------

    public NotesViewHolder(@NonNull View itemView, PublishSubject<Integer> itemViewClickSubject) {
        super(itemView, itemViewClickSubject);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvCreationDate = itemView.findViewById(R.id.tv_creation_date);
        tvBody = itemView.findViewById(R.id.tv_body);
        cgTags = itemView.findViewById(R.id.cg_tags);
    }

    @Override
    public void bind(NoteVm note) {
        tvTitle.setText(note.getTitle());
        tvCreationDate.setText(note.getCreationDate());
        tvBody.setText(note.getBody());
        cgTags.removeAllViews();
        if (note.getTags() != null && !note.getTags().isEmpty()) {
            for (TagVm tagVm : note.getTags()) {
                Chip chip = new Chip(cgTags.getContext());

                ColorStateList colorStateList = new ColorStateList(
                        new int[][]{new int[]{android.R.attr.state_enabled}},
                        new int[]{tagVm.getColor()});

                chip.setChipBackgroundColor(colorStateList);

                chip.setText(tagVm.getName());
                chip.setTextColor(ContextCompat.getColor(chip.getContext(), R.color.white));

                chip.setClickable(false);
                chip.setFocusable(false);
                chip.setCheckable(false);

                cgTags.addView(chip);
            }
        } else {

        }

    }
}

