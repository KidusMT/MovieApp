package com.example.kidusmt.movieapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.ui.tour.TourActivity;

/**
 * Created by KidusMT on 12/25/2017.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    //for the intro activity - tour page
    public void openTour(View view) {
        startActivity(new Intent(this, TourActivity.class));
        finish();
    }
}
