package com.example.studyspaces.space.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyspaces.Config;
import com.example.studyspaces.R;
import com.example.studyspaces.data.studyspace.info.Photo;
import com.example.studyspaces.data.studyspace.info.StudyArea;
import com.example.studyspaces.space.page.Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPhoto extends Fragment {
    private static final int PICK_IMAGE = 100;

    private ImageView imageView;

    private Button submitPhotoButton;
    private Button addPhotoButton;

    private Photo photo;
    private StudyArea currentStudyArea;

    private Uri imageUri;

    public AddPhoto() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentStudyArea = Config.selectedStudyArea;

        // Inflate the layout for this fragment
        View addPhotoView = inflater.inflate(R.layout.fragment_add_photo, container,
                false);
        imageView = (ImageView) addPhotoView.findViewById(R.id.add_photo_view);
        final TextView inputPhotoDescription = (TextView) addPhotoView.findViewById(R.id.Input_PhotoDescription);
        addPhotoButton = addPhotoView.findViewById(R.id.add_photo_button);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO see if the button can disappear
                getImage();
            }
        });

        submitPhotoButton = addPhotoView.findViewById(R.id.submit_photo_button);
        submitPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photo != null) {
                    // check if this fragement is called from writing review:
                    photo.description = inputPhotoDescription.getText().toString();
                    if (!Config.AddPhotoFromReview) {
                        ((Page) getActivity()).addPhoto(currentStudyArea, photo);
                    } else {
                        WriteReview.photo = photo;
                    }
                }
                // makes the fragment return to the main activity
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });

        return addPhotoView;
    }

    private void getImage() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, reqCode, data);
        if (resCode == RESULT_OK && reqCode == PICK_IMAGE) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
                photo = new Photo(selectedImage, " ");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
