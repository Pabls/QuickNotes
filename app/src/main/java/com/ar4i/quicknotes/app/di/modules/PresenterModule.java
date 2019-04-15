package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.data.repositories.database.DatabaseRepository;
import com.ar4i.quicknotes.data.repositories.firebaseauth.FirebaseAuthRepository;
import com.ar4i.quicknotes.data.repositories.firebaserealtime.FirebaseRealtimeRepository;
import com.ar4i.quicknotes.data.repositories.resources.ResourceRepository;
import com.ar4i.quicknotes.domain.notes.NotesInteractor;
import com.ar4i.quicknotes.domain.resources.ResourceInteractor;
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

    @Provides
    NotesInteractor provideNotesInteractor(DatabaseRepository databaseRepository,
                                           FirebaseRealtimeRepository firebaseRealtimeRepository) {
        return new NotesInteractor(databaseRepository, firebaseRealtimeRepository);
    }
}
