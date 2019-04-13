package com.ar4i.quicknotes.app.di.application;

import com.ar4i.quicknotes.app.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class
})
public interface ApplicationComponent {
    App application();
}
