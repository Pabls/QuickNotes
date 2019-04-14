package com.ar4i.quicknotes.app.di.components;

import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.app.di.modules.ApplicationModule;
import com.ar4i.quicknotes.app.di.modules.InteractorModule;
import com.ar4i.quicknotes.app.di.modules.PresenterModule;
import com.ar4i.quicknotes.app.di.modules.ViewModule;
import com.ar4i.quicknotes.presentation.signin.view.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        InteractorModule.class,
        PresenterModule.class,
        ViewModule.class}
)
public interface ApplicationComponent {

    App application();

    void inject(SignInActivity signInActivity);
}
