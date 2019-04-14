package com.ar4i.quicknotes.presentation.note.root;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.view.BaseFragment;

public class NoteActivity extends AppCompatActivity {

    //==========================================start Lifecycle=====================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //-------------------------------------------end Lifecycle--------------------------------------


    //==========================================start Private methods===============================

    private void showFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit();
    }

    //-------------------------------------------end Private methods--------------------------------
}
