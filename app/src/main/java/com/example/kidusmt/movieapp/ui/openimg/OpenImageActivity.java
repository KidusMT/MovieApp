package com.example.kidusmt.movieapp.ui.openimg;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.R;

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

        Uri uri = new Uri.Builder().path(getIntent().getStringExtra("img_full")).build();
        img_full.setImageURI(uri);
    }
}
