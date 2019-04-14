package com.ar4i.quicknotes.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.main.NavigationAdapter;
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

    //-------------------------------------------end Lifecycle--------------------------------------

    //==========================================start Private methods===============================

    private void initViews() {
        tbMenu = findViewById(R.id.tl_menu);
        vpSlider = findViewById(R.id.vp_slider);
        adapter = new NavigationAdapter(getSupportFragmentManager(), this);
        vpSlider.setAdapter(adapter);
        tbMenu.setupWithViewPager(vpSlider);

    }

    //-------------------------------------------end Private methods--------------------------------

}
