package com.ashraf.weather.photos.presentationlayer.activity.baseActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;


import com.ashraf.weather.photos.assets.application.WeatherPhotoApplication;

import com.ashraf.weather.photos.assets.constant.Utilities;
import com.ashraf.weather.photos.presentationlayer.uiHelper.BitmapHelper;
import com.ashraf.weather.photos.presentationlayer.uiHelper.UiHelper;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    public Utilities mUtilities;
    @Inject
    public UiHelper mUiHelper;
    @Inject
    public BitmapHelper mBitmapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherPhotoApplication) getApplication()).getAppComponent().inject(this);
    }

}
