package com.ar4i.quicknotes;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NavigationAdapter extends FragmentPagerAdapter {

    public NavigationAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    //==========================================start Fields========================================

    private static final int SCREENS_COUNT = 3;
    private Context context;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start extends FragmentPagerAdapter==================

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NotesFragment.newInstance();
            case 1:
                return NewNoteFragment.newInstance();
            case 2:
                return NewDrawFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return SCREENS_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return getString(R.string.tab_layout_text_my_notes);
            case 1:
                return getString(R.string.tab_layout_text_new_note);
            case 2:
                return getString(R.string.tab_layout_text_new_drawing);
            default:
                return null;
        }
    }

    //-------------------------------------------end extends FragmentPagerAdapter-------------------


    //==========================================start Private Methods===============================


    private String getString(int resId) {
        return context.getString(resId);
    }

    //-------------------------------------------end Private Methods--------------------------------
}
