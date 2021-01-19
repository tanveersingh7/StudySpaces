package com.example.studyspaces.search.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.StudySpace;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        ArrayList<StudySpace> recommendations = new ArrayList<>();
        recommendations.add(new StudySpace(R.drawable.ic_android_black_24dp, "Desc1", "Desc2"));
        recommendations.add(new StudySpace(R.drawable.ic_android_black_24dp, "Desc1", "Desc2"));
        recommendations.add(new StudySpace(R.drawable.ic_android_black_24dp, "Desc1", "Desc2"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SearchPageAdapter(recommendations);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
