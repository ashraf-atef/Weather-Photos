package com.ashraf.weather.photos.presentationlayer.activity.homeActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.ashraf.weather.photos.R;

public class PhotoAdaptor extends RecyclerView.Adapter<PhotoAdaptor.ItemViewHolder> {

    List<BitmapDto> bitmapList;
    OnItemClickListener onItemClickListener;

    public PhotoAdaptor(OnItemClickListener onItemClickListener) {
        this.bitmapList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_layout, viewGroup, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.bind(position);
    }

    public void setData(List<BitmapDto> list) {
        bitmapList = list;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_imageView)
        ImageView photoImageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            photoImageView.setOnClickListener(onClickListener);
        }

        public View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        };

        public void bind(int position) {
            photoImageView.setImageBitmap(bitmapList.get(position).getBitmap());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}