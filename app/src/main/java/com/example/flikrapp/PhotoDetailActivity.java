package com.example.flikrapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        activateToolbar(true);

        Intent intent = getIntent();
        Photo photo =(Photo)intent.getSerializableExtra(PHOTO_TRANSFER);
        if(photo!=null){
            TextView photoTitle = findViewById(R.id.photo_title);
            Resources resourses  = getResources();
            String text = resourses.getString(R.string.photo_title_text,photo.getTitle());
            photoTitle.setText(text);

            TextView photoTags = findViewById(R.id.photo_tags);
            photoTags.setText(resourses.getString(R.string.photo_tags_text,photo.getTag()));
//            photoTags.setText("Tags: "+photo.getTag());

            TextView photoAuthor = findViewById(R.id.photo_author);
            photoAuthor.setText(photo.getAuthor());


            ImageView photoImage = findViewById(R.id.photo_image);
            Picasso.get().load(photo.getLink())//link is the bigger picture
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(photoImage);

        }
    }

}
