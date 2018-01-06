package com.ashraf.weather.photos.testApplication;

import android.app.Application;

import com.ashraf.weather.photos.assets.ioc.module.UseCasesModule;
import com.ashraf.weather.photos.assets.ioc.module.UtilitesModule;
import com.ashraf.weather.photos.tectComponent.DaggerTestComponent;
import com.ashraf.weather.photos.tectComponent.TestComponent;
import com.ashraf.weather.photos.assets.constant.Constant;
import com.ashraf.weather.photos.assets.ioc.module.NetModule;
import com.ashraf.weather.photos.assets.ioc.module.RemoteRepositoryModule;


public class TestApplication extends Application {

    private TestComponent testComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        testComponent = DaggerTestComponent.builder()
                .netModule(new NetModule(Constant.API_URL))
                .remoteRepositoryModule(new RemoteRepositoryModule())
                .useCasesModule(new UseCasesModule())
                .utilitesModule(new UtilitesModule())
                .build();
    }

    public TestComponent getTestComponent() {
        return testComponent;
    }
}
