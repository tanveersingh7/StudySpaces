package com.example.studyspaces.search.information;

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
import com.example.studyspaces.data.studyspace.info.StudySpace;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchPageAdapter extends RecyclerView.Adapter<SearchPageAdapter.StudySpaceViewHolder> implements Filterable {
    private ArrayList<StudySpace> studySpaceArrayList;
    private ArrayList<StudySpace> studySpaceRecommendationsList;
    private ArrayList<StudySpace> searchResults;


    public static class StudySpaceViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public StudySpaceViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.studySpaceThumbnail);
            textView1 = itemView.findViewById(R.id.searchSpaceName);
            textView2 = itemView.findViewById(R.id.studySpaceDescription);
        }
    }

    public SearchPageAdapter(ArrayList<StudySpace> studySpacesList) {
        this.studySpaceArrayList = studySpacesList;
        this.studySpaceRecommendationsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public StudySpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studyspace_card, parent, false);
        StudySpaceViewHolder studySpaceViewHolder = new StudySpaceViewHolder(v);
        return  studySpaceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudySpaceViewHolder holder, int position) {
        StudySpace currentSpace = studySpaceArrayList.get(position);
        holder.imageView.setImageResource(currentSpace.getmImageResource());
        holder.textView1.setText(currentSpace.getDesc1());
        holder.textView2.setText(currentSpace.getDesc2());
    }

    @Override
    public int getItemCount() {
        return studySpaceArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return studySpaceFilter;
    }

    private Filter studySpaceFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // search logic
            List<StudySpace> filteredStudySpaces = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredStudySpaces.addAll(studySpaceRecommendationsList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (StudySpace item: studySpaceArrayList) {
                    if (item.getDesc1().toLowerCase().contains(filterPattern));
                    filteredStudySpaces.add(item);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredStudySpaces;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            searchResults.clear();
            searchResults.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
