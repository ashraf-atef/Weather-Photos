package com.ashraf.weather.photos.presentationlayer.activity.homeActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.ashraf.weather.photos.R;
import com.ashraf.weather.photos.assets.constant.Utilities;
import com.ashraf.weather.photos.presentationlayer.activity.baseActivity.BaseActivity;
import com.ashraf.weather.photos.presentationlayer.activity.galleryActivity.GalleryActivity;
import com.ashraf.weather.photos.presentationlayer.activity.weatherActivity.WeatherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements PhotoAdaptor.OnItemClickListener {


    @BindView(R.id.add_FAB)
    FloatingActionButton addFAB;
    @BindView(R.id.coordinator_layout)
    ConstraintLayout coordinatorLayout;
    @BindView(R.id.photo_recyclerView)
    RecyclerView photoRecyclerView;

    PhotoAdaptor photoAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setupPhotoRecyclerView();
    }

    private void setupPhotoRecyclerView() {
        photoRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        photoRecyclerView.setLayoutManager(gridLayoutManager);
        photoAdaptor = new PhotoAdaptor(bitmapHelper.getAllSavedImages(), this);
        photoRecyclerView.setAdapter(photoAdaptor);
    }

    public void notifyDataChanged() {
        photoAdaptor.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        notifyDataChanged();
    }

    @OnClick(R.id.add_FAB)
    public void onAddPhotoFABClick() {
        selectImage();
    }

    int SELECTED_OPTION;
    private static int TAKE_A_PHOTO_OPTION = 0;
    private static int CHOOSE_FROM_GALLERY_OPTION = 1;
    private static int CANCELED_OPTION = 2;

    int CAMERA_REQUEST_CODE = 11;
    int GALLERY_REQUEST_CODE = 12;


    private void selectImage() {
        final CharSequence[] items = getResources().getStringArray(R.array.pick_image_options);
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(getString(R.string.take_photo));
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == CANCELED_OPTION) {
                    dialog.dismiss();
                } else {
                    boolean accessPermission = mUtilities.checkPermission(HomeActivity.this);
                    if (accessPermission) {
                        if (item == TAKE_A_PHOTO_OPTION) {
                            SELECTED_OPTION = TAKE_A_PHOTO_OPTION;
                            openCameraIntent();
                        } else if (item == CHOOSE_FROM_GALLERY_OPTION) {
                            SELECTED_OPTION = CHOOSE_FROM_GALLERY_OPTION;
                            openGalleryIntent();
                        }
                    }
                }
            }
        });
        builder.show();
    }

    private void openCameraIntent() {
        mUtilities.openCameraIntent(HomeActivity.this, CAMERA_REQUEST_CODE);
    }

    private void openGalleryIntent() {
        mUtilities.openGalleryIntent(HomeActivity.this,
                getString(R.string.select_file),
                GALLERY_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utilities.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (SELECTED_OPTION == TAKE_A_PHOTO_OPTION)
                        openCameraIntent();
                    else if (SELECTED_OPTION == CHOOSE_FROM_GALLERY_OPTION)
                        openGalleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = null;
            if (requestCode == GALLERY_REQUEST_CODE) {
                bitmap = bitmapHelper.getBitmapFromGalleryResultIntent(getBaseContext(), data);

            } else if (requestCode == CAMERA_REQUEST_CODE) {
                bitmap = bitmapHelper.getBitmapFromCameraResultIntent(getBaseContext(), data);
            } else {
                return;
            }
            bitmapHelper.saveBitmapOnDisk(bitmap);
            WeatherActivity.start(this);
        }
    }

    @Override
    public void onItemClick(int position) {
        GalleryActivity.start(this, position);
    }
}
