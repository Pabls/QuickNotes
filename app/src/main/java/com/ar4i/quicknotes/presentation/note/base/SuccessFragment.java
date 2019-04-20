package com.ar4i.quicknotes.presentation.note.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.presentation.base.views.BaseFragment;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

public abstract class SuccessFragment extends BaseFragment implements ISuccessView {

    //==========================================start UI============================================

    Group group;
    ConstraintLayout clContainer;

    //-------------------------------------------end UI---------------------------------------------


    //==========================================start Lifecycle=====================================


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clContainer = getActivity().findViewById(R.id.cl_container);
        group = getActivity().findViewById(R.id.group);
    }

    //-------------------------------------------end Lifecycle--------------------------------------


    //==========================================start implements ISuccessView=======================

    @Override
    public void showSuccessfulView() {
        if (group != null && clContainer != null) {
            group.setVisibility(View.GONE);
            View.inflate(getActivity(), R.layout.view_done, clContainer);
            clContainer.setAlpha(0.0f);
            clContainer.animate()
                    .setDuration(1500)
                    .alpha(1.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            if (getActivity() != null)
                                getActivity().onBackPressed();
                        }
                    });
        }
    }


    //-------------------------------------------end implements ISuccessView------------------------
}
