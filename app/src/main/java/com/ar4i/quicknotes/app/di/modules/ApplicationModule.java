package com.ar4i.quicknotes.app.di.modules;

import com.ar4i.quicknotes.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplication() {
        return application;
    }

}
