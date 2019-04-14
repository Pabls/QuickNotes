package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.domain.resource.ResourceInteractor;
import com.ar4i.quicknotes.domain.signin.SignInInteractor;
import com.ar4i.quicknotes.presentation.signin.presenter.SignInPresenter;

import dagger.Module;
import dagger.Provides;

@Module()
public class ViewModule {

    @Provides
    SignInPresenter provideSignInPresenter(SignInInteractor iSignInInteractor, ResourceInteractor resourceInteractor) {
        return new SignInPresenter(iSignInInteractor, resourceInteractor);
    }
}
