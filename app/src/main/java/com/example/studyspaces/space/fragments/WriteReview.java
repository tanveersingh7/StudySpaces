package com.example.studyspaces.space.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.Photo;
import com.example.studyspaces.data.studyspace.info.StudyArea;
import com.example.studyspaces.reviews.Review;
import com.example.studyspaces.space.page.Page;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteReview extends Fragment {

    private View addPhotoButton;
    private View submitReviewButton;
    private StudyArea currentStudyArea;
    public static Photo photo;
    private int CurrentRate;

    public WriteReview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentStudyArea = Config.selectedStudyArea;

        // Inflate the layout for this fragment
        final View writeReviewView = inflater.inflate(R.layout.fragment_write_review, container,
                false);

        final RatingBar rating = (RatingBar) writeReviewView.findViewById(R.id.rating_bar);
        final TextView reviewTitle = (TextView) writeReviewView.findViewById(R.id.write_reviewtitle);
        final TextView reiewContent = (TextView) writeReviewView.findViewById(R.id.write_review_text);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                CurrentRate = (int)rating;
            }
        });

        addPhotoButton = writeReviewView.findViewById(R.id.add_photo_to_review_button);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.AddPhotoFromReview = true;
                AddPhoto addPhoto = new AddPhoto();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, addPhoto);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        submitReviewButton = writeReviewView.findViewById(R.id.submit_review_button);
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // submit review here
                Review review = new Review(CurrentRate, reiewContent.getText().toString(), reviewTitle.getText().toString());
                review.reviewID = (int)System.currentTimeMillis();
                ((Page) getActivity()).addReview(currentStudyArea, review);
                if (photo != null) {
                    ((Page) getActivity()).addPhotowReview(currentStudyArea, review, photo);
                    photo = null;
                }

                // refresh the review list in page acitivity
                ((Page) getActivity()).RefreshReview();

                // makes the fragment return to the main activity
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        return writeReviewView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
