package com.ashraf.weather.photos.presentationlayer.uiHelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

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
        bitmap.compress(Bitmap.CompressFormat.JPEG, PHOTO_QUALITY, bytes);
        File destination = new File(getPhotoDir(),
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

    public File getPhotoDir() {
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

        File photosDir = new File(getPhotoDir().getPath());
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
        getAllSavedImages();
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
        getAllSavedImages();
        return photoList.get(photoList.size() - 1).getBitmap();
    }

    public void deleteLastBitmap() {
        File file = new File(getPhotoDir(), photoList.get(photoList.size() - 1).getFileName());
        if (file.exists()) {
            file.delete();
            photoList.remove(photoList.size() - 1);
        }
    }

    public File getFileFromPosition(int position) {
        return new File(getPhotoDir(), photoList.get(position).getFileName());
    }

    public Bitmap drawTextToBitmap(Bitmap bitmap, float scale, String text, int textColor, int shadowColor) {
        try {
            int textSize;
            if (bitmap.getHeight() < 500) {
                textSize = 3;
            } else {
                textSize = 12;
            }

            android.graphics.Bitmap.Config bitmapConfig =   bitmap.getConfig();
            if(bitmapConfig == null) {
                bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
            }
            bitmap = bitmap.copy(bitmapConfig, true);

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(textColor);
            paint.setTextSize((int) (textSize * scale));
            paint.setShadowLayer(1f, 0f, 1f, shadowColor);

            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int x = (bitmap.getWidth() - bounds.width())/6;
            int y = (bitmap.getHeight() + bounds.height())/5;
            canvas.drawText(text, x * scale, y * scale, paint);
            Log.d("BIT", bitmap.getWidth()+"-"+bitmap.getHeight());
            return bitmap;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

}
