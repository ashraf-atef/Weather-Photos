package com.ashraf.weather.photos.presentationlayer.activity.baseActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;


import com.ashraf.weather.photos.assets.application.TawseelaApplication;

import com.ashraf.weather.photos.assets.constant.Utilities;
import com.ashraf.weather.photos.presentationlayer.uiHelper.BitmapHelper;
import com.ashraf.weather.photos.presentationlayer.uiHelper.UiHelper;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    public Utilities mUtilities;
    @Inject
    public UiHelper uiHelper;
    @Inject
    public BitmapHelper bitmapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TawseelaApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
