package com.example.studyspaces.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;

public class FullImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        Intent intent = getIntent();

        Bitmap showImg = Config.fullDisplayImg.image;
        ImageView imgView = (ImageView) findViewById(R.id.fullImage);
        imgView.setImageBitmap(showImg);

        TextView Disp_PhotoDescription = (TextView) findViewById(R.id.Disp_PhotoDescription);
        Disp_PhotoDescription.setText(Config.fullDisplayImg.description);
    }
}
