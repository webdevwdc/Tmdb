package com.wts.wts.app;

import android.app.Application;

import com.wts.wts.di.component.ApplicationComponent;
import com.wts.wts.di.component.DaggerApplicationComponent;
import com.wts.wts.di.modules.ApplicationModule;

/**
 * Created by android on 12/9/17.
 */

public class TrackApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent= DaggerApplicationComponent.builder().applicationModule(
                new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
