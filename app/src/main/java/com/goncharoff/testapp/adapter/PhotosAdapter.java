package com.goncharoff.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.goncharoff.testapp.R;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosHolder> {

    private Context context;
    private List<String> photosUrls;

    public PhotosAdapter(Context context, List<String> photosUrls) {
        this.context = context;
        this.photosUrls = photosUrls;
    }

    @NonNull
    @Override
    public PhotosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photos_item, parent, false);

        return new PhotosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosHolder holder, int position) {
        Glide.with(context).load(photosUrls.get(position))
                .error(R.drawable.ic_launcher_background)
                .into(holder.photoHolder);
    }

    @Override
    public int getItemCount() {
        return photosUrls.size();
    }

    class PhotosHolder extends RecyclerView.ViewHolder {

        private ImageView photoHolder;

        PhotosHolder(@NonNull View itemView) {
            super(itemView);
            photoHolder = itemView.findViewById(R.id.photosHolder);
        }

    }
}
