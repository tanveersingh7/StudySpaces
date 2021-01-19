package com.example.studyspaces.space.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.MainDBManager;
import com.example.studyspaces.data.studyspace.info.Photo;
import com.example.studyspaces.data.studyspace.info.PhotoDBManager;
import com.example.studyspaces.data.studyspace.info.StudyArea;
import com.example.studyspaces.reviews.ReviewDBManager;
import com.example.studyspaces.space.StudySpaceItem;
import com.example.studyspaces.space.page.Page;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements StudySpaceAdapter.OnCardListener{

    private RecyclerView mRecyclerView;
    private StudySpaceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//    private List<StudySpaceItem> studySpaceItemList;
    private List<StudyArea> studyAreas;

    public MainDBManager myMainDBManager;
    public static PhotoDBManager myPhotoDBManager;
    public static ReviewDBManager myReviewDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        LoadDatabase();
        setUpRecyclerView();

    }

    private void LoadDatabase(){

        myPhotoDBManager = new PhotoDBManager(this);
        myReviewDBManager = new ReviewDBManager(this);
        myMainDBManager = new MainDBManager(this, myPhotoDBManager, myReviewDBManager);

        studyAreas = new ArrayList<>();

        ReadDatabase();

        if(studyAreas.size() == 0) LoadStudyArea();


    }

    private void LoadStudyArea(){
        StudyArea studyAreak1 = new StudyArea("Keller Hall",
                "1st floor, next to 2-111",
                0x10,
                15,
                3,
                1,
                5,
                4,
                true, true, true, false, false, true, true);
        addPhoto(studyAreak1, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.keller), ""));
        myMainDBManager.add(studyAreak1);

        StudyArea studyAreak2 = new StudyArea("Keller Hall",
                "3rd floor, near main entrance",
                0x11,
                125,
                65,
                4,
                5,
                3,
                true, true, true, true, true, true, true);
        addPhoto(studyAreak2, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.k2), ""));
        myMainDBManager.add(studyAreak2);

        StudyArea studyAreak3 = new StudyArea("Keller Hall",
                "4th floor, next to Gopher Way",
                0x12,
                12,
                5,
                1,
                5,
                3,
                true, true, true, true, true, true, true);
        addPhoto(studyAreak3, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.k3), ""));
        myMainDBManager.add(studyAreak3);

        StudyArea studyAreaw1 = new StudyArea("Walter Library",
                "2rd floor, main library",
                0x20,
                120,
                40,
                1,
                3,
                5,
                true, true, true, false, true, true, false);
        addPhoto(studyAreaw1, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.walter), ""));
        myMainDBManager.add(studyAreaw1);

        StudyArea studyAreaw2 = new StudyArea("Walter Library",
                "3rd floor, second library",
                0x21,
                50,
                10,
                1,
                4,
                3,
                true, true, true, false, true, false, false);
        addPhoto(studyAreaw2, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.walter), ""));
        myMainDBManager.add(studyAreaw2);

        StudyArea studyAreac1 = new StudyArea("Coffman Memorial Union",
                "ground floor, main entrance",
                0x30,
                60,
                4,
                5,
                5,
                1,
                true, true, true, false, true, false, false);
        addPhoto(studyAreac1, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.coffman), ""));
        myMainDBManager.add(studyAreac1);

        StudyArea studyAreac2 = new StudyArea("Coffman Memorial Union",
                "2nd floor, next to room 210",
                0x31,
                21,
                2,
                2,
                4,
                3,
                true, false, true, false, false, false, false);
        addPhoto(studyAreac2, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.coffman), ""));
        myMainDBManager.add(studyAreac2);

        StudyArea studyArea4 = new StudyArea("Bruiniks Hall",
                "2nd floor, next to main stairs",
                0x04,
                3,
                0,
                1,
                4,
                5,
                true, true, true, false, false, false, false);
        addPhoto(studyArea4, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.bruininks), ""));
        myMainDBManager.add(studyArea4);

        StudyArea studyArea5 = new StudyArea("Akerman Hall",
                "1st floor, by main entrance",
                0x05,
                42,
                6,
                2,
                5,
                4,
                true, false, true, false, false, true, false);
        addPhoto(studyArea5, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.akerman), ""));
        myMainDBManager.add(studyArea5);

        StudyArea studyArea6 = new StudyArea("Mechanical Engineering",
                "2nd floor, next to room 215",
                0x06,
                16,
                2,
                3,
                4,
                4,
                true, true, true, false, false, false, false);
        addPhoto(studyArea6, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.mech_eng), ""));
        myMainDBManager.add(studyArea6);

        StudyArea studyArea7 = new StudyArea("Lind Hall",
                "1st floor, by main entrance",
                0x07,
                146,
                25,
                5,
                5,
                5,
                true, true, true, false, false, false, true);
        addPhoto(studyArea7, new Photo(BitmapFactory.decodeResource(getResources(), R.mipmap.lind_hall), ""));
        myMainDBManager.add(studyArea7);

        ReadDatabase();
    }

    public void addPhoto(StudyArea studyArea, Photo photo){
        studyArea.AddPhoto(photo);
        photo.setAreaID(studyArea);
        myPhotoDBManager.add(photo);
    }

    private void ReadDatabase(){
        studyAreas = myMainDBManager.Query();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager= new LinearLayoutManager(this);
//        mAdapter = new StudySpaceAdapter(studySpaceItemList, this);
        mAdapter = new StudySpaceAdapter(studyAreas, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.study_space_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Study Spaces here");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mAdapter.getFilter().filter("");
                return false;
            }
        });
        return true;
    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(this, Page.class);
        Config.selectedStudyArea = studyAreas.get(position);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        for (StudyArea studyArea:studyAreas){
            studyArea.getAverageReview();
        }
        setUpRecyclerView();
    }
}
