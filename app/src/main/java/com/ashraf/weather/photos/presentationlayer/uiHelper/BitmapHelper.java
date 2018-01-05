package com.ashraf.weather.photos.presentationlayer.uiHelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;

import com.ashraf.weather.photos.presentationlayer.activity.homeActivity.BitmapDto;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

public class BitmapHelper {

    public static final String PHOTOS_DIR_NAME = "CurrentWeather Photos";
    public static final String PHOTO_EXTENATION = ".jpg";
    public static final int PHOTO_QUALITY = 90;

    public List<BitmapDto> photoList;

    public Bitmap getBitmapFromGalleryResultIntent(Context context, @Nullable Intent intent) {
        if (intent != null) {
            try {
                return MediaStore.Images.Media.getBitmap(context.getContentResolver(), intent.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Bitmap getBitmapFromCameraResultIntent(Context context, @Nullable Intent intent) {
        if (intent != null) {
            return (Bitmap) intent.getExtras().get("data");
        }
        return null;
    }

    public boolean saveBitmapOnDisk(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(createPhotosDirIfNotExists(),
                System.currentTimeMillis() + PHOTO_EXTENATION);
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
            addPhotoToCacheList(destination.getName(), bitmap);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private File createPhotosDirIfNotExists() {
        File photosDir = new File(Environment.getExternalStorageDirectory(), PHOTOS_DIR_NAME);
        if (!photosDir.exists()) {
            photosDir.mkdir();
        }
        return photosDir;
    }

    public List<BitmapDto> getAllSavedImages() {
        if (!isNullableCacheList()) {
            return photoList;
        }

        File photosDir = new File(createPhotosDirIfNotExists().getPath());
        File[] listOfFiles = photosDir.listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() &&
                        getFileExtension(listOfFiles[i].getPath()).equals(PHOTO_EXTENATION)) {
                    addPhotoToCacheList(listOfFiles[i].getName(),
                            decodeFileToBitmap(listOfFiles[i].getPath()));
                }
            }
        }
        return photoList;
    }

    public void addPhotoToCacheList(String name, Bitmap bitmap) {
        if (isNullableCacheList()) {
            getAllSavedImages();
        }
        photoList.add(new BitmapDto(name, bitmap));
    }

    public boolean isNullableCacheList() {
        if (photoList == null) {
            photoList = new ArrayList<>();
            return true;
        }
        return false;
    }

    public String getFileExtension(String path) {
        return path.substring(path.lastIndexOf("."));
    }

    public Bitmap decodeFileToBitmap(String filePath) {
        return BitmapFactory.decodeFile(filePath);
    }

   public Bitmap getLastBitmap() {
        if (isNullableCacheList()) {
            getAllSavedImages();
        }
        return photoList.get(photoList.size()-1).getBitmap();
   }

    public Bitmap typeTextAboveBitmap(Bitmap bitmap, String text, int textSize, int color) {
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setTextSize(textSize);
        canvas.drawText(text, bitmap.getWidth() / 2, bitmap.getHeight() / 2, paint);
        return mutableBitmap;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public Bitmap overlay(Bitmap backgroundBitmap, Bitmap foregroundBitmap) {
        Bitmap bmOverlay = Bitmap.createBitmap(backgroundBitmap.getWidth(), backgroundBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        foregroundBitmap.setWidth(backgroundBitmap.getWidth());
        backgroundBitmap.setHeight(backgroundBitmap.getHeight());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(backgroundBitmap, new Matrix(), null);
        canvas.drawBitmap(foregroundBitmap, new Matrix(), null);
        return bmOverlay;
    }
}
