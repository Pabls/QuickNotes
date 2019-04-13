package com.ar4i.quicknotes.presentation.newnote.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar4i.quicknotes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewNoteFragment extends Fragment {

    public static NewNoteFragment newInstance() {
        return new NewNoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        FloatingActionButton fab = getActivity().findViewById(R.id.fab_send);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v != null)
//                    v.setAlpha(0.1f);
//            }
//        });
    }
}
