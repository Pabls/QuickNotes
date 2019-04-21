package com.ar4i.quicknotes.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;
import com.ar4i.quicknotes.presentation.main.NavigationAdapter;
import com.ar4i.quicknotes.presentation.tags.views.TagsActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    //==========================================start Fields========================================

    NavigationAdapter adapter;

    //-------------------------------------------end Fields-----------------------------------------

    //==========================================start UI============================================

    TabLayout tbMenu;
    ViewPager vpSlider;

    //-------------------------------------------end UI---------------------------------------------

    //==========================================start Lifecycle=====================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_tag:
                startActivity(new Intent(this, TagsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter = null;
    }

    //-------------------------------------------end Lifecycle--------------------------------------

    //==========================================start Private methods===============================

    private void initViews() {
        tbMenu = findViewById(R.id.tl_menu);
        vpSlider = findViewById(R.id.vp_slider);
        adapter = new NavigationAdapter(getSupportFragmentManager());
        vpSlider.setAdapter(adapter);
        tbMenu.setupWithViewPager(vpSlider);

    }

    //-------------------------------------------end Private methods--------------------------------

}
