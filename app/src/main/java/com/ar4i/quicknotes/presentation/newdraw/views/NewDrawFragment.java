package com.ar4i.quicknotes.presentation.newdraw.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar4i.quicknotes.R;

public class NewDrawFragment extends Fragment {

    public static NewDrawFragment newInstance() {
        return new NewDrawFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_draw, container, false);
    }

}
