package com.example.studyspaces.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studyspaces.Landing;
import com.example.studyspaces.R;

public class NewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void gotToLanding(View view) {
        Intent intent = new Intent(this, Landing.class);
        startActivity(intent);
    }
}
