package com.ar4i.quicknotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //==========================================start Fields========================================

    FragmentPagerAdapter adapter;

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
