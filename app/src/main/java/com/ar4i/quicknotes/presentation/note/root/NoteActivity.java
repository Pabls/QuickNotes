package com.ar4i.quicknotes.presentation.note.root;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.note.notedetails.views.NoteDetailsFragment;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "com.ar4i.quicknotes.extra_note";
    private static final String KEY_NOTE = "com.ar4i.quicknotes.key_note";

    public static Intent getStartIntent(Context context, NoteVm noteVm) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE, noteVm);
        return intent;
    }

    // region========================================Fields=========================================

    NoteVm note;

    // endregion-------------------------------------Fields-----------------------------------------

    //==========================================start Lifecycle=====================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        try {
            note = (NoteVm) getIntent().getSerializableExtra(EXTRA_NOTE);
        } catch (Exception e) {
            note = null;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            try {
                note = (NoteVm) getIntent().getSerializableExtra(EXTRA_NOTE);
            } catch (Exception e) {
                note = null;
            }
            showFragment(NoteDetailsFragment.newInstance(note));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_NOTE, note);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        note = (NoteVm) savedInstanceState.getSerializable(KEY_NOTE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //-------------------------------------------end Lifecycle--------------------------------------

    //==========================================start Private methods===============================

    public void showFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit();
    }

    //-------------------------------------------end Private methods--------------------------------
}
