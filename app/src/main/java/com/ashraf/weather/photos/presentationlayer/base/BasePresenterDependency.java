package com.ashraf.weather.photos.presentationlayer.base;

import com.ashraf.weather.photos.assets.application.TawseelaApplication;
import com.ashraf.weather.photos.assets.constant.Utilities;

import javax.inject.Inject;

public class BasePresenterDependency {
    @Inject
    public Utilities mUtilities;

    public BasePresenterDependency() {
        TawseelaApplication.getAppComponent().inject(this);
    }
}
