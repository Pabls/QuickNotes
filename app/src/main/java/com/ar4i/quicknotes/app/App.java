package com.ar4i.quicknotes.app;

import android.app.Application;

import com.ar4i.quicknotes.app.di.components.ApplicationComponent;
import com.ar4i.quicknotes.app.di.components.DaggerApplicationComponent;
import com.ar4i.quicknotes.app.di.modules.ApplicationModule;

public class App extends Application {

    //==========================================start Fields========================================

    private static ApplicationComponent applicationComponent;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start Lifecycle=====================================

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        //FirebaseApp.initializeApp(this);
    }

    //-------------------------------------------end Lifecycle--------------------------------------


    //==========================================start Public methods================================

    public static ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    //-------------------------------------------end Public methods---------------------------------


    //==========================================start Private methods===============================

    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    //-------------------------------------------end Private methods--------------------------------

}
