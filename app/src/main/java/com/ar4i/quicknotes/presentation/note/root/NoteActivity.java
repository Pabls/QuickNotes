package com.ar4i.quicknotes.presentation.note.root;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;

public class NoteActivity extends AppCompatActivity {

    //==========================================start Lifecycle=====================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
