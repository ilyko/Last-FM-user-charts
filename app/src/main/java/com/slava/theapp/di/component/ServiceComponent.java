package com.slava.theapp.di.component;


import com.slava.theapp.di.PerService;
import com.slava.theapp.di.module.ServiceModule;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    //void inject(SyncService service);

}
