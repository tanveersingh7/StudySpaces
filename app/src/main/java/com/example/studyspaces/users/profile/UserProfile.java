package com.example.studyspaces.users.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.space.search.SearchActivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class UserProfile extends AppCompatActivity {

    private ToggleButton lighting;
    private ToggleButton quiet;
    private ToggleButton outlets;
    private ToggleButton studyaids;
    private ToggleButton food;
    private ToggleButton coffee;
    private ToggleButton fastnetwork;
    private Button submit_button;

    public int[] preference = new int[7];

    final String FILE_NAME = "userPreference.txt";
    public String user_preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        submit_button = findViewById(R.id.button);
        lighting = findViewById(R.id.toggleButton_lighting);
        quiet = findViewById(R.id.toggleButton_quiet);
        outlets = findViewById(R.id.toggleButton_outlets);
        studyaids = findViewById(R.id.toggleButton_studyaids);
        food = findViewById(R.id.toggleButton_food);
        coffee = findViewById(R.id.toggleButton_coffee);
        fastnetwork = findViewById(R.id.toggleButton_fastnetwork);
        // read user's preference if exist
        int[] ReadPreference = ReadUserPreference();
        if (ReadPreference != null){
            preference = ReadPreference;
            lighting.setChecked(preference[0] == 1);
            quiet.setChecked(preference[1] == 1);
            outlets.setChecked(preference[2] == 1);
            studyaids.setChecked(preference[3] == 1);
            food.setChecked(preference[4] == 1);
            coffee.setChecked(preference[5] == 1);
            fastnetwork.setChecked(preference[6] == 1);
        }
        //
        lighting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preference[0] = 1;
                } else{
                    preference[0] = 0;
                }
            }
        });
        quiet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preference[1] = 1;
                } else{
                    preference[1] = 0;
                }
            }
        });
        outlets.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preference[2] = 1;
                } else{
                    preference[2] = 0;
                }
            }
        });
        studyaids.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preference[3] = 1;
                } else{
                    preference[3] = 0;
                }
            }
        });
        food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preference[4] = 1;
                } else{
                    preference[4] = 0;
                }
            }
        });
        coffee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preference[5] = 1;
                } else{
                    preference[5] = 0;
                }
            }
        });
        fastnetwork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preference[6] = 1;
                } else{
                    preference[6] = 0;
                }
            }
        });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_preference = "";
                for(int i=0;i<preference.length;i++){
                    user_preference += preference[i] + ",";
                }
                //write the user profile to sd
                String FileName = Config.GetFullPath(FILE_NAME);
                try {
                    File file = new File(FileName);
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(user_preference);
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                goToSearchActivity();

            }
        });
    }

    public void goToSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public int[] ReadUserPreference(){
        String FileName = Config.GetFullPath(FILE_NAME);
        int[] readPreference = new int[7];
        try {
            File file = new File(FileName);
            if(!file.exists())
                return null;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] preferences = line.split(",");
            for (int i=0; i<preferences.length; i++){
                readPreference[i] = Integer.parseInt(preferences[i]);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readPreference;

    }
}
