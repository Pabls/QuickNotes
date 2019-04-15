package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.data.database.NotesDatabase;
import com.ar4i.quicknotes.data.repositories.database.DatabaseRepository;
import com.ar4i.quicknotes.data.repositories.firebaseauth.FirebaseAuthRepository;
import com.ar4i.quicknotes.data.repositories.firebaserealtime.FirebaseRealtimeRepository;
import com.ar4i.quicknotes.data.repositories.resources.ResourceRepository;

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

    @Provides
    @Singleton
    DatabaseRepository provideDatabaseRepository(NotesDatabase database) {
        return new DatabaseRepository(database);
    }
}
