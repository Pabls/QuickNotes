package com.ar4i.quicknotes.presentation.notes;

import android.view.View;
import android.widget.TextView;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.BaseViewHolder;

import androidx.annotation.NonNull;
import io.reactivex.subjects.PublishSubject;

public class NotesViewHolder extends BaseViewHolder<NoteVm> {

    // region========================================Fields============================================

    TextView tvTitle;
    TextView tvCreationDate;
    TextView tvBody;

    // endregion-------------------------------------Fields--------------------------------------------

    public NotesViewHolder(@NonNull View itemView, PublishSubject<Integer> itemViewClickSubject) {
        super(itemView, itemViewClickSubject);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvCreationDate = itemView.findViewById(R.id.tv_creation_date);
        tvBody = itemView.findViewById(R.id.tv_body);
    }

    @Override
    public void bind(NoteVm note) {
        tvTitle.setText(note.getTitle());
        tvCreationDate.setText(note.getCreationDate());
        tvBody.setText(note.getBody());
    }
}

