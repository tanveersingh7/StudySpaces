package com.example.studyspaces.reviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.Photo;

public class FullReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_review);
        Intent intent = getIntent();

        Review currentDisReview = Config.fullDisplayReview;
        String reviewTitle = currentDisReview.getReviewTitle();
        String reviewContent = currentDisReview.reviewContent;
        Photo photo = Config.selectedStudyArea.getReviewPhoto(currentDisReview.reviewID);

        TextView text_title = (TextView) findViewById(R.id.fullReviewTitle);
        TextView text_content = (TextView) findViewById(R.id.fullReviewContent);
        ImageView imageView = (ImageView) findViewById(R.id.ImgView_ReviewPhoto);
        RatingBar dispRating = (RatingBar) findViewById(R.id.DispRate);

        text_title.setText(reviewTitle);
        text_content.setText(reviewContent);
        dispRating.setRating(currentDisReview.stars);

        if(photo != null){
            imageView.setImageBitmap(photo.image);
        }
    }
}
