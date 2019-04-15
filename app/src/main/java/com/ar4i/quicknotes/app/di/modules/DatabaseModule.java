package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.data.database.NotesDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    NotesDatabase providesDatabase(App application) {
        return Room.databaseBuilder(application, NotesDatabase.class, NotesDatabase.NAME).build();
    }
}
