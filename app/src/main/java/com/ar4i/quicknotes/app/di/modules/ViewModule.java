package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.domain.auth.AuthInteractor;
import com.ar4i.quicknotes.domain.notes.NotesInteractor;
import com.ar4i.quicknotes.domain.resources.ResourceInteractor;
import com.ar4i.quicknotes.domain.tags.ITagsInteractor;
import com.ar4i.quicknotes.domain.tags.TagsInteractor;
import com.ar4i.quicknotes.presentation.colorselection.presenter.ColorSelectionPresenter;
import com.ar4i.quicknotes.presentation.newnote.presenter.NewNotePresenter;
import com.ar4i.quicknotes.presentation.note.notedetails.presenter.NoteDetailsPresenter;
import com.ar4i.quicknotes.presentation.note.noteediting.presenter.NoteEditingPresenter;
import com.ar4i.quicknotes.presentation.notes.presenter.NotesPresenter;
import com.ar4i.quicknotes.presentation.signin.presenter.SignInPresenter;
import com.ar4i.quicknotes.presentation.tags.presenter.TagsPresenter;

import dagger.Module;
import dagger.Provides;

@Module()
public class ViewModule {

    @Provides
    SignInPresenter provideSignInPresenter(AuthInteractor authInteractor,
                                           ResourceInteractor resourceInteractor) {
        return new SignInPresenter(authInteractor, resourceInteractor);
    }

    @Provides
    NotesPresenter provideNotesPresenter(NotesInteractor notesInteractor, AuthInteractor authInteractor) {
        return new NotesPresenter(notesInteractor, authInteractor);
    }

    @Provides
    NewNotePresenter provideNewNotePresenter(NotesInteractor notesInteractor,
                                             AuthInteractor authInteractor,
                                             ResourceInteractor resourceInteractor,
                                             TagsInteractor tagsInteractor) {
        return new NewNotePresenter(notesInteractor, authInteractor, resourceInteractor, tagsInteractor);
    }

    @Provides
    NoteEditingPresenter provideNoteEditingPresenter(NotesInteractor notesInteractor) {
        return new NoteEditingPresenter(notesInteractor);
    }

    @Provides
    NoteDetailsPresenter provideNoteDetailsPresenter(NotesInteractor notesInteractor) {
        return new NoteDetailsPresenter(notesInteractor);
    }

    @Provides
    TagsPresenter provideTagsPresenter(TagsInteractor tagsInteractor, AuthInteractor authInteractor) {
        return new TagsPresenter(tagsInteractor, authInteractor);
    }

    @Provides
    ColorSelectionPresenter provideColorSelectionPresenter() {
        return new ColorSelectionPresenter();
    }
}
