package com.ar4i.quicknotes.presentation.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.BaseAdapter;

import androidx.annotation.NonNull;

public class NotesAdapter extends BaseAdapter<NoteVm, NotesViewHolder> {
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view, getSubject());
    }
}
