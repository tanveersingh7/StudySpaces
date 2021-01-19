package com.example.studyspaces.space.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.StudyArea;

public class MoreDetails extends AppCompatActivity {

    private String[] noiseleveldesp = {"quite", "somewhat quite", "median", "somewhat noisy", "noisy"};
    private String[] networkspeeddesp = {"slow", "median slow", "median", "median fast", "fast"};
    private String[] lightleveldesp = {"bad", "not satisfying", "median", "somewhat satisfying", "good"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        StudyArea currentStudyArea = Config.selectedStudyArea;
        String intentLocation = currentStudyArea.getName();
        String intentSubAddress = currentStudyArea.getLocation();
        float averageRating = currentStudyArea.getAverageReview();
        int maxOccupancy = currentStudyArea.getMaxOccupancy();
        int powerOutlet = currentStudyArea.getPowerOutlet();
        String noiseLevel = noiseleveldesp[currentStudyArea.noiseLevel-1];
        String networkSpeed = networkspeeddesp[currentStudyArea.networkSpeed-1];
        String lightLevel = lightleveldesp[currentStudyArea.lightLevel-1];
        String wifi = currentStudyArea.wiFi?"yes":"no";
        String water = currentStudyArea.waterFountain?"yes":"no";
        String restroom = currentStudyArea.restroom?"yes":"no";
        String food = currentStudyArea.food?"yes":"no";
        String coffee = currentStudyArea.coffeeShop?"yes":"no";
        String whiteBoard = currentStudyArea.whiteBoard?"yes":"no";
        String bigScreen = currentStudyArea.bigScreen?"yes":"no";

        TextView location = (TextView) findViewById(R.id.LocationDetails);
        TextView subAddress = (TextView) findViewById(R.id.subAddressDetails);
        TextView text_avgrate = (TextView) findViewById(R.id.averageRatingValueTextView);
        TextView text_maxocc = (TextView) findViewById(R.id.occupancyValueTextView);
        TextView text_powerout = (TextView) findViewById(R.id.powerOutletValueTextView);
        TextView text_noiselevel = (TextView) findViewById(R.id.noiseLevelValueTextView);
        TextView text_networkspeed = (TextView) findViewById(R.id.networkSpeedValueTextView);
        TextView text_lightlevel = (TextView) findViewById(R.id.lightLevelValueTextView);
        TextView text_wifi = (TextView) findViewById(R.id.wifiValueTextView);
        TextView text_water = (TextView) findViewById(R.id.waterfountainValueTextView);
        TextView text_restroom = (TextView) findViewById(R.id.restroomValueTextView);
        TextView text_food = (TextView) findViewById(R.id.foodValueTextView);
        TextView text_coffee = (TextView) findViewById(R.id.coffeeshopValueTextView);
        TextView text_whitboard = (TextView) findViewById(R.id.whiteboardValueTextView);
        TextView text_bigscreen = (TextView) findViewById(R.id.bigscreenValueTextView);

        location.setText(intentLocation);
        subAddress.setText(intentSubAddress);
        text_avgrate.setText(String.valueOf(averageRating));
        text_maxocc.setText(String.valueOf(maxOccupancy));
        text_powerout.setText(String.valueOf(powerOutlet));
        text_noiselevel.setText(noiseLevel);
        text_networkspeed.setText(networkSpeed);
        text_lightlevel.setText(lightLevel);
        text_wifi.setText(wifi);
        text_water.setText(water);
        text_restroom.setText(restroom);
        text_food.setText(food);
        text_coffee.setText(coffee);
        text_whitboard.setText(whiteBoard);
        text_bigscreen.setText(bigScreen);

    }
}
