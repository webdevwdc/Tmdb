package com.wts.wts.di.component;

import com.wts.wts.di.modules.LoginModule;
import com.wts.wts.di.scope.PerActivity;
import com.wts.wts.modules.login.LoginActivity;

import dagger.Component;

/**
 * Created by android on 12/9/17.
 */

@PerActivity
@Component(modules = LoginModule.class , dependencies = ApplicationComponent.class)
public interface LoginComponent {
    void inject (LoginActivity loginActivity);
}
