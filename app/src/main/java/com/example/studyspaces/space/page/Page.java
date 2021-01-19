package com.example.studyspaces.space.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.Photo;
import com.example.studyspaces.data.studyspace.info.StudyArea;
import com.example.studyspaces.gallery.GalleryPage;
import com.example.studyspaces.reviews.FullReview;
import com.example.studyspaces.reviews.Review;
import com.example.studyspaces.reviews.ReviewAdapter;
import com.example.studyspaces.space.fragments.AddPhoto;
import com.example.studyspaces.space.fragments.WriteReview;
import com.example.studyspaces.space.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class Page extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, ReviewAdapter.OnReviewListener {

    private String userId = "12345";
    private List<Review> reviewList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_page);

        StudyArea seletedArea = Config.selectedStudyArea;

        String intentLocation = seletedArea.getName();
        Bitmap intentImage = seletedArea.getDisplayPhoto().image;
        String intentSubAddress = seletedArea.getLocation();
        StringBuilder detailBuilder = new StringBuilder();
        detailBuilder.append("Max occpuancy: " + seletedArea.maxOccupancy + "\n");
        detailBuilder.append("Number of outlet: " + seletedArea.powerOutlet + "\n");
        detailBuilder.append("WiFi: " + (seletedArea.wiFi?"yes":"no") + "\n");

        TextView location = (TextView) findViewById(R.id.Location);
        imageView = (ImageView) findViewById(R.id.detailImage);
        TextView subAddress = (TextView) findViewById(R.id.subAddress);
        TextView detail = (TextView) findViewById(R.id.spaceDetails);

        location.setText(intentLocation);
        imageView.setImageBitmap(intentImage);
        subAddress.setText(intentSubAddress);
        detail.setText(detailBuilder.toString());

        reviewList = seletedArea.getReviews();

        mRecyclerView = findViewById(R.id.reviewRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReviewAdapter((ArrayList<Review>) reviewList, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(v.getContext(), GalleryPage.class);
                startActivity(galleryIntent);
            }
        });
    }

    public void RefreshReview(){
        StudyArea seletedArea = Config.selectedStudyArea;
        reviewList = seletedArea.getReviews();

        mRecyclerView = findViewById(R.id.reviewRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReviewAdapter((ArrayList<Review>) reviewList, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void showPopup(View view) {
        PopupMenu popUp = new PopupMenu(this, view);
        popUp.setOnMenuItemClickListener(this);
        popUp.inflate(R.menu.popup_menu);
        popUp.show();
    }

    public void goToMoreDetailsActivity(View view) {
        Intent intent = new Intent(this, MoreDetails.class);
        startActivity(intent);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.add_photo:
                FragmentTransaction addPhotoFt = fragmentManager.beginTransaction();
                addPhotoFt.replace(R.id.page, new AddPhoto());
                addPhotoFt.addToBackStack("add photo");
                addPhotoFt.commit();
                return true;

            case R.id.write_review:
                FragmentTransaction writeReviewFt = fragmentManager.beginTransaction();
                writeReviewFt.replace(R.id.page, new WriteReview());
                writeReviewFt.addToBackStack("write review");
                writeReviewFt.commit();
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onReviewClick(int position) {
        Intent intent = new Intent(this, FullReview.class);
        Config.fullDisplayReview = reviewList.get(position);
        startActivity(intent);
    }

    public void addReview(StudyArea studyArea, Review review){
        review.areaID = studyArea.areaID;
        studyArea.AddReview(review);
        SearchActivity.myReviewDBManager.add(review);
    }

    public void addPhotowReview(StudyArea studyArea, Review review, Photo photo){
        photo.areaID = studyArea.areaID;
        photo.reviewID = review.reviewID;
        studyArea.AddPhoto(photo);
        SearchActivity.myPhotoDBManager.add(photo);
    }

    public void addPhoto(StudyArea studyArea, Photo photo){
        photo.areaID = studyArea.areaID;
        studyArea.AddPhoto(photo);
        SearchActivity.myPhotoDBManager.add(photo);
    }
}
