package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.data.repository.firebaseauth.FirebaseAuthRepository;
import com.ar4i.quicknotes.data.repository.resource.ResourceRepository;
import com.ar4i.quicknotes.domain.resource.ResourceInteractor;
import com.ar4i.quicknotes.domain.signin.SignInInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    SignInInteractor provideSignInInteractor(FirebaseAuthRepository firebaseAuthRepository) {
        return new SignInInteractor(firebaseAuthRepository);
    }

    @Provides
    ResourceInteractor provideResourceInteractor(ResourceRepository resourceRepository) {
        return new ResourceInteractor(resourceRepository);
    }
}
