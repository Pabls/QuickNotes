package com.ar4i.quicknotes.app.di.components;

import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.app.di.modules.ApplicationModule;
import com.ar4i.quicknotes.app.di.modules.DatabaseModule;
import com.ar4i.quicknotes.app.di.modules.InteractorModule;
import com.ar4i.quicknotes.app.di.modules.PresenterModule;
import com.ar4i.quicknotes.app.di.modules.ViewModule;
import com.ar4i.quicknotes.data.database.NotesDatabase;
import com.ar4i.quicknotes.presentation.newnote.views.NewNoteFragment;
import com.ar4i.quicknotes.presentation.note.notedetails.views.NoteDetailsFragment;
import com.ar4i.quicknotes.presentation.note.noteediting.views.NoteEditingFragment;
import com.ar4i.quicknotes.presentation.notes.views.NotesFragment;
import com.ar4i.quicknotes.presentation.signin.views.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DatabaseModule.class,
        InteractorModule.class,
        PresenterModule.class,
        ViewModule.class}
)
public interface ApplicationComponent {

    App application();

    NotesDatabase database();

    void inject(SignInActivity signInActivity);

    void inject(NotesFragment notesFragment);

    void inject(NewNoteFragment newNoteFragment);

    void inject(NoteEditingFragment noteEditingFragment);

    void inject(NoteDetailsFragment noteDetailsFragment);
}
