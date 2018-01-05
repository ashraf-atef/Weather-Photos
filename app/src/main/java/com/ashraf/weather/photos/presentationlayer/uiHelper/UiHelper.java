package com.ashraf.weather.photos.presentationlayer.uiHelper;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ashraf.weather.photos.R;

public class UiHelper {

    private  ActionBar prepareActionBar(AppCompatActivity activity, Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        actionBar.setDisplayShowTitleEnabled(false);
        return actionBar;
    }

    public  void updateActionBar(AppCompatActivity activity, Toolbar toolbar, int color) {
        ActionBar actionBar = prepareActionBar(activity, toolbar);
        actionBar.setDisplayShowHomeEnabled(true); // show or hide the default home button
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(activity, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(activity, color), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
    }

    public  void updateActionBarWithoutBackArrow(AppCompatActivity activity, Toolbar toolbar) {
        ActionBar actionBar = prepareActionBar(activity, toolbar);
        actionBar.setDisplayShowHomeEnabled(false); // show or hide the default home button
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

}
