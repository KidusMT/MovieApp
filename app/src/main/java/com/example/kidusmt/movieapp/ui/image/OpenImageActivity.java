package com.example.kidusmt.movieapp.ui.image;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.util.Constants;

/**
 * Created by KidusMT on 1/3/2018.
 */

public class OpenImageActivity extends BaseActivity {

    ImageView img_full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_img);

        img_full = findViewById(R.id.iv_open_img);

        if (getIntent() != null) {
            Uri uri = new Uri.Builder().path(Constants.TMDB_IMAGE_PATH+getIntent().getStringExtra("img_full")).build();
            img_full.setImageURI(uri);
        }
    }
}
