package com.ashraf.weather.photos.presentationlayer.activity.galleryActivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ashraf.weather.photos.R;
import com.ashraf.weather.photos.presentationlayer.activity.homeActivity.BitmapDto;

import java.util.List;

public class GalleryPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<BitmapDto> bitmapDtoList;

    public GalleryPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bitmapDtoList = bitmapDtoList;
    }

    public void setData(List<BitmapDto> bitmapDtoList) {
        this.bitmapDtoList = bitmapDtoList;
    }

    @Override
    public int getCount() {
        return bitmapDtoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.photo_gallery_layout, container, false);

        ImageView imageView =  itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmapDtoList.get(position).getBitmap());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
