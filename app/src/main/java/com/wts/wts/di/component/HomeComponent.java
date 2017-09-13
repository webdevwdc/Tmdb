package com.wts.wts.di.component;

import com.wts.wts.di.modules.HomeModule;
import com.wts.wts.di.scope.PerActivity;
import com.wts.wts.modules.home.HomeActivity;

import dagger.Component;


@PerActivity
@Component(modules = HomeModule.class , dependencies = ApplicationComponent.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
