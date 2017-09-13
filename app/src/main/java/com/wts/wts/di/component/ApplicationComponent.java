package com.wts.wts.di.component;

import android.content.SharedPreferences;

import com.wts.wts.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by android on 12/9/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    SharedPreferences exposeSharedPref();
}
