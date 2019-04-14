package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.data.repository.firebaseauth.FirebaseAuthRepository;
import com.ar4i.quicknotes.data.repository.firebaserealtime.FirebaseRealtimeRepository;
import com.ar4i.quicknotes.data.repository.resource.ResourceRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    @Singleton
    FirebaseAuthRepository provideFirebaseAuthRepository() {
        return new FirebaseAuthRepository();
    }

    @Provides
    @Singleton
    FirebaseRealtimeRepository provideFirebaseRealtimeRepository() {
        return new FirebaseRealtimeRepository();
    }

    @Provides
    @Singleton
    ResourceRepository provideResourceRepository(App application) {
        return new ResourceRepository(application);
    }
}
