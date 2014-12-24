package com.tcapps.hearthtools.application;

import dagger.ObjectGraph;
import android.app.Application;

import com.tcapps.hearthtools.module.DatabaseModule;
import com.tcapps.hearthtools.module.FragmentModule;

import java.util.Arrays;
import java.util.List;

public class HearthToolsApplication extends Application {

    private static ObjectGraph sObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        sObjectGraph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
            new DatabaseModule(this),
            new FragmentModule()
        );
    }

    public static void inject(Object object) {
        sObjectGraph.inject(object);
    }
}
