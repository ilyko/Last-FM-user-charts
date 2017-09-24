package com.slava.theapp.util.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

        Scheduler ui();

        Scheduler computation();

        Scheduler io();

}
