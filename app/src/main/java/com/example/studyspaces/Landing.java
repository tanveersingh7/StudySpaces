package com.example.studyspaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studyspaces.ui.login.LoginActivity;
import com.example.studyspaces.ui.login.NewUser;

public class Landing extends AppCompatActivity {
    final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1701;
    final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1702;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestFilePermission();
        setContentView(R.layout.activity_landing);
    }

    public void gotToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToNewUserActivity(View view) {
        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }

    private void requestFilePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:

                break;
            case PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:

                break;
            default:
                return;
        }

        if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Read Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
