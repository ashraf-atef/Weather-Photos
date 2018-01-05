package com.ashraf.weather.photos.presentationlayer.activity.galleryActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ashraf.weather.photos.R;
import com.ashraf.weather.photos.presentationlayer.activity.baseActivity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends BaseActivity {

    public static final String POSITION_KEY = "position";
    public static void start(Context context, int position) {
        Intent starter = new Intent(context, GalleryActivity.class);
        starter.putExtra(POSITION_KEY, position);
        context.startActivity(starter);
    }

    @BindView(R.id.pager)
    ViewPager viewPager;

    GalleryPagerAdapter galleryPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        galleryPagerAdapter = new GalleryPagerAdapter(this, bitmapHelper.getAllSavedImages());
        viewPager.setAdapter(galleryPagerAdapter);
        setViewPagerPosition();
    }

    public int getPositionFromIntent() {
        if (getIntent() != null &&
                getIntent().hasExtra(POSITION_KEY)) {
            return getIntent().getIntExtra(POSITION_KEY, 0);
        }
        return 0;
    }

    public void setViewPagerPosition() {
        viewPager.setCurrentItem(getPositionFromIntent());
    }
}
