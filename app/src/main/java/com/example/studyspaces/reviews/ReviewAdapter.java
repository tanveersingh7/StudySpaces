package com.example.studyspaces.reviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyspaces.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private ArrayList<Review> mReviewList;
    private OnReviewListener mOnReviewListener;

    public ReviewAdapter(ArrayList<Review> reviewList, OnReviewListener onReviewListener){
        this.mReviewList = reviewList;
        this.mOnReviewListener = onReviewListener;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_card, parent, false);
        ReviewViewHolder rvh = new ReviewViewHolder(v, mOnReviewListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review currentReview = mReviewList.get(position);
        holder.reviewRating.setText(currentReview.getStarsAsString());
        holder.reviewTitle.setText(currentReview.getReviewTitle());
        holder.reviewDescription.setText(currentReview.getReviewContent());

    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView reviewTitle;
        public TextView reviewDescription;
        public TextView reviewRating;
        OnReviewListener onReviewListener;

        public ReviewViewHolder(@NonNull View itemView, OnReviewListener onReviewListener) {
            super(itemView);
            reviewTitle = itemView.findViewById(R.id.reviewTitle);
            reviewDescription = itemView.findViewById(R.id.reviewContent);
            reviewRating = itemView.findViewById(R.id.reviewRating);
            this.onReviewListener = onReviewListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onReviewListener.onReviewClick(getAdapterPosition());
        }
    }

    public interface OnReviewListener {
        void onReviewClick(int position);
    }
}
