package com.example.studyspaces.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.studyspaces.R;
import com.example.studyspaces.space.search.SearchActivity;
import com.example.studyspaces.users.profile.UserProfile;

public class LoginActivity extends AppCompatActivity {

    private boolean setPreferences = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
    }

    public void goToNextPage(View view){
        if (setPreferences) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, UserProfile.class);
            setPreferences = true;
            startActivity(intent);
        }
    }
}
