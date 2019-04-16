package com.ar4i.quicknotes.presentation.signin.views;

import android.os.Bundle;

import com.ar4i.quicknotes.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.sign_in_container, SignInFragment.newInstance())
                    .commit();
        }
    }
}
