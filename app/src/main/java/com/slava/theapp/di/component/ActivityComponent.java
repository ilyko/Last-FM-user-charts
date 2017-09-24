package com.slava.theapp.di.component;
import com.slava.theapp.di.PerActivity;
import com.slava.theapp.di.module.ActivityModule;
import com.slava.theapp.hello.HelloActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //inject activities,fragments,dialogs here:

    void inject(HelloActivity activity);


}
