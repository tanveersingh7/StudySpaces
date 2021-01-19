package com.example.studyspaces.space.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.StudyArea;
import com.example.studyspaces.data.studyspace.info.StudySpace;
import com.example.studyspaces.space.StudySpaceItem;

import java.util.ArrayList;
import java.util.List;

public class StudySpaceAdapter extends RecyclerView.Adapter<StudySpaceAdapter.StudySpaceItemHolder>
        implements Filterable {

//    private List<StudySpaceItem> mStudySpaceItemList;
//    private List<StudySpaceItem> mStudySpaceItemListFull;

    private List<StudyArea> mStudyAreaList;
    private List<StudyArea> mStudyAreaListFull;
    private OnCardListener mOnCardListener;

    public static class StudySpaceItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView mTitleView;
        public TextView mShortDescView;
        public TextView mRatingView;
        OnCardListener onCardListener;

        public StudySpaceItemHolder(@NonNull View itemView, OnCardListener onCardListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTitleView = itemView.findViewById(R.id.textViewTitle);
            mShortDescView = itemView.findViewById(R.id.textViewShortDesc);
            mRatingView = itemView.findViewById(R.id.textViewRating);
            this.onCardListener = onCardListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }

//    public StudySpaceAdapter(List<StudySpaceItem> studySpaceItemList, OnCardListener onCardListener) {
//        mStudySpaceItemList = studySpaceItemList;
//        mStudySpaceItemListFull = new ArrayList<StudySpaceItem>(mStudySpaceItemList);
//        this.mOnCardListener = onCardListener;
//    }

    public StudySpaceAdapter(List<StudyArea> studyAreasList, OnCardListener onCardListener) {
        mStudyAreaList = studyAreasList;
        mStudyAreaListFull = new ArrayList<StudyArea>(mStudyAreaList);
        this.mOnCardListener = onCardListener;
    }

    @NonNull
    @Override
    public StudySpaceItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_study_space_item, parent, false);
        StudySpaceItemHolder sch = new StudySpaceItemHolder(v, mOnCardListener);
        return sch;
    }

    @Override
    public void onBindViewHolder(@NonNull StudySpaceItemHolder holder, int position) {
//        StudySpaceItem currentItem = mStudySpaceItemList.get(position);
//        holder.mImageView.setImageResource(currentItem.getImageResource());
//        holder.mTitleView.setText(currentItem.getmTitle());
//        holder.mShortDescView.setText(currentItem.getmShortDesc());
//        holder.mRatingView.setText(currentItem.getmRating());
        StudyArea currentItem = mStudyAreaList.get(position);
        holder.mImageView.setImageBitmap(currentItem.getDisplayPhoto().image);
        holder.mTitleView.setText(currentItem.getName());
        holder.mShortDescView.setText(currentItem.getLocation());
        holder.mRatingView.setText(String.format("%.1f", currentItem.getAverageReview()));

    }

    @Override
    public int getItemCount() {
//        return mStudySpaceItemList.size();
        return mStudyAreaList.size();
    }

    @Override
    public Filter getFilter() {
        return studySpaceFilter;
    }

    private Filter studySpaceFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
//            List<StudySpaceItem> filteredList = new ArrayList<>();
            List<StudyArea> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(mStudySpaceItemListFull);
                filteredList.addAll(mStudyAreaListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

//                for (StudySpaceItem item : mStudySpaceItemListFull) {
//                    if (item.getmTitle().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
                for (StudyArea item : mStudyAreaListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
//            mStudySpaceItemList.clear();
//            mStudySpaceItemList.addAll((List) results.values);
            mStudyAreaList.clear();
            mStudyAreaList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public interface OnCardListener {
        void onCardClick(int position);
    }
}
