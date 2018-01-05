package com.ashraf.weather.photos.assets.constant;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Html;
import android.view.View;

public class Utilities {
    public static AlertDialog showAlertDialog(ContextThemeWrapper contextThemeWrapper,
                                              String title,
                                              Drawable icon,
                                              String message,
                                              String positiveButtonTitle,
                                              final int positiveButtonTextColor,
                                              DialogInterface.OnClickListener positiveClickListener,
                                              String negativeButtonTitle,
                                              final int negativeButtonTextColor,
                                              DialogInterface.OnClickListener negativeClickListener,
                                              View view

    ) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(contextThemeWrapper);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setIcon(icon);
        alertDialogBuilder.setMessage(Html.fromHtml(message));
        alertDialogBuilder.setPositiveButton(positiveButtonTitle, positiveClickListener);
        alertDialogBuilder.setNegativeButton(negativeButtonTitle, negativeClickListener);
        alertDialogBuilder.setView(view);
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(positiveButtonTextColor);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(negativeButtonTextColor);
            }
        });
        return dialog;
    }

    public void addFragment(Context context, int frameId, Fragment fragment, String tag, Bundle bundle) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameId, fragment, tag);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }

    public void replaceFragment(
            final Context context,
            final int frameId,
            final Fragment fragment,
            final String tag,
            final boolean saveToBackStack,
            final String addToBackStackName,
            final Bundle bundle
    ) {
        final FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(fragment.getClass().getName()) == null) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(frameId, fragment, tag);
            if (saveToBackStack) {
                fragmentTransaction.addToBackStack(addToBackStackName);
            }
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
            fragmentTransaction.commit();
        }
    }


    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void openCameraIntent(Context context, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ((Activity)context).startActivityForResult(intent, requestCode);
    }

    public void openGalleryIntent(Context context, String title, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        ((Activity)context).startActivityForResult(Intent.createChooser(intent, title), requestCode);
    }

}
