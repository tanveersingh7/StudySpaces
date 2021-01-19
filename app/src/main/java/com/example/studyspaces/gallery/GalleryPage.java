package com.example.studyspaces.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.Photo;
import com.example.studyspaces.data.studyspace.info.StudyArea;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GalleryPage extends AppCompatActivity implements GalleryAdapter.OnImageListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private GalleryAdapter mGalleryAdapter;
    ArrayList<Photo> images;
    private StudyArea currentStudyArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_page);
        mRecyclerView = findViewById(R.id.galleryRecycler);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // read image from current study area
        currentStudyArea = Config.selectedStudyArea;
        images = currentStudyArea.getGallery();

        mGalleryAdapter = new GalleryAdapter(images, this);
        mRecyclerView.setAdapter(mGalleryAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onImageClick(int position) {
        Intent intent = new Intent(this, FullImage.class);
        Photo image = images.get(position);
        Config.fullDisplayImg = image;
        startActivity(intent);
    }
}
