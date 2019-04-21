package com.ar4i.quicknotes.presentation.main;


import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.presentation.newnote.views.NewNoteFragment;
import com.ar4i.quicknotes.presentation.notes.views.NotesFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NavigationAdapter extends FragmentPagerAdapter {

    public NavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    //==========================================start Fields========================================

    private static final int PAGES_COUNT = 2;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start extends FragmentPagerAdapter==================

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewNoteFragment.newInstance();
            case 1:
                return NotesFragment.newInstance();
//            case 2:
//                return TagsFragment.newInstance();
//            case 3:
//                return NewDrawFragment.newInstance();
            default:
                return NotesFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return getString(R.string.tab_layout_text_new_note);
            case 1:
                return getString(R.string.tab_layout_text_my_notes);
//            case 2:
//                return getString(R.string.tab_layout_text_tags);
//            case 3:
//                return getString(R.string.tab_layout_text_new_drawing);
            default:
                return null;
        }
    }

    //-------------------------------------------end extends FragmentPagerAdapter-------------------


    //==========================================start Private Methods===============================


    private String getString(int resId) {
        return App.getContext().getString(resId);
    }

    //-------------------------------------------end Private Methods--------------------------------
}
