package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.domain.notes.NotesInteractor;
import com.ar4i.quicknotes.domain.resources.ResourceInteractor;
import com.ar4i.quicknotes.domain.signin.SignInInteractor;
import com.ar4i.quicknotes.presentation.newnote.presenter.NewNotePresenter;
import com.ar4i.quicknotes.presentation.note.notedetails.presenter.NoteDetailsPresenter;
import com.ar4i.quicknotes.presentation.note.noteediting.presenter.NoteEditingPresenter;
import com.ar4i.quicknotes.presentation.notes.presenter.NotesPresenter;
import com.ar4i.quicknotes.presentation.signin.presenter.SignInPresenter;

import dagger.Module;
import dagger.Provides;

@Module()
public class ViewModule {

    @Provides
    SignInPresenter provideSignInPresenter(SignInInteractor iSignInInteractor, ResourceInteractor resourceInteractor) {
        return new SignInPresenter(iSignInInteractor, resourceInteractor);
    }

    @Provides
    NotesPresenter provideNotesPresenter() {
        return new NotesPresenter();
    }

    @Provides
    NewNotePresenter provideNewNotePresenter(NotesInteractor notesInteractor) {
        return new NewNotePresenter(notesInteractor);
    }

    @Provides
    NoteEditingPresenter provideNoteEditingPresenter() {
        return new NoteEditingPresenter();
    }

    @Provides
    NoteDetailsPresenter provideNoteDetailsPresenter() {
        return new NoteDetailsPresenter();
    }
}
