package com.ar4i.quicknotes.presentation.tags.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;

public class TagsActivity extends AppCompatActivity {

    //==========================================start Lifecycle=====================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        showFragment(TagsFragment.newInstance());
    }

    //-------------------------------------------end Lifecycle--------------------------------------


    //==========================================start  extends AppCompatActivity====================

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

    //-------------------------------------------end  extends AppCompatActivity---------------------


    //==========================================start Private methods===============================

    public void showFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit();
    }

    //-------------------------------------------end Private methods--------------------------------
}
