package com.example.studyspaces.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.Photo;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    ArrayList<Photo> images;
    private OnImageListener mOnImageListener;

    public GalleryAdapter(ArrayList<Photo> images, OnImageListener onImageListener) {
        this.images = new ArrayList<>(images);
        this.mOnImageListener = onImageListener;
    }



    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_image, parent, false);
        GalleryViewHolder galleryViewHolder = new GalleryViewHolder(view, mOnImageListener);
        return galleryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.galleryPhoto.setImageBitmap(images.get(position).image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView galleryPhoto;
        OnImageListener onImageListener;

        public GalleryViewHolder(@NonNull View itemView, OnImageListener onImageListener) {
            super(itemView);
            galleryPhoto = itemView.findViewById(R.id.gallery_photo);
            itemView.setOnClickListener(this);
            this.onImageListener = onImageListener;

        }

        @Override
        public void onClick(View v) {
            onImageListener.onImageClick(getAdapterPosition());
        }
    }

    public interface OnImageListener {
        void onImageClick(int position);
    }
}
