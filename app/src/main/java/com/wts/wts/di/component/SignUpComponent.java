package com.wts.wts.di.component;

import com.wts.wts.di.modules.LoginModule;
import com.wts.wts.di.modules.SignUpModule;
import com.wts.wts.di.scope.PerActivity;
import com.wts.wts.modules.signup.SignUpActivity;

import dagger.Component;

/**
 * Created by android on 13/9/17.
 */

@PerActivity
@Component(modules = SignUpModule.class , dependencies = ApplicationComponent.class)
public interface SignUpComponent {
    void inject(SignUpActivity signUpActivity);
}
