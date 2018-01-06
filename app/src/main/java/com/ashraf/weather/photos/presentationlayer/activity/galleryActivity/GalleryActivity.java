package com.ashraf.weather.photos.presentationlayer.activity.galleryActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ashraf.weather.photos.R;
import com.ashraf.weather.photos.assets.constant.Utilities;
import com.ashraf.weather.photos.presentationlayer.activity.baseActivity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends BaseActivity {

    public static final String POSITION_KEY = "position";
    @BindView(R.id.toolBar)
    Toolbar toolBar;

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
        galleryPagerAdapter = new GalleryPagerAdapter(this);
        setAdaptorData();
        mUiHelper.updateActionBar(this, toolBar, R.color.white);
    }

    private void setAdaptorData() {
        if (mUtilities.checkPermission(this)) {
            galleryPagerAdapter.setData(mBitmapHelper.getAllSavedImages());
            viewPager.setAdapter(galleryPagerAdapter);
            setViewPagerPosition();
        }
    }

    private int getPositionFromIntent() {
        if (getIntent() != null &&
                getIntent().hasExtra(POSITION_KEY)) {
            return getIntent().getIntExtra(POSITION_KEY, 0);
        }
        return 0;
    }

    private void setViewPagerPosition() {
        viewPager.setCurrentItem(getPositionFromIntent());
    }

    private void shareImage() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(mBitmapHelper.getFileFromPosition(viewPager.getCurrentItem())));
        startActivity(Intent.createChooser(share, "Share Image"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utilities.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setAdaptorData();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                shareImage();
                return true;
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
