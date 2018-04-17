package com.example.kidusmt.movieapp.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.data.repo.genre.RepoGenre;
import com.example.kidusmt.movieapp.data.repo.genre.local.GenreLocal;
import com.example.kidusmt.movieapp.data.repo.genre.remote.GenreRemote;
import com.example.kidusmt.movieapp.ui.tour.TourActivity;
import com.example.kidusmt.movieapp.App;
import com.facebook.FacebookSdk;

import static com.example.kidusmt.movieapp.util.Constants.APP_NAME;
import static com.example.kidusmt.movieapp.util.Constants.KEY;

/**
 * Created by KidusMT on 12/25/2017.
 */

public class SplashActivity extends BaseActivity {

    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_splash);

        pref = getSharedPreferences(APP_NAME, MODE_PRIVATE);

        ImageView splashLogo = findViewById(R.id.iv_splash_img);

        //Its possible to change the animation type anytime
        Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);

        splashLogo.startAnimation(alphaAnim);

        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setSeen();
                openTourActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void openTourActivity(){
        startActivity(new Intent(this, TourActivity.class));
        finish();
    }

    private void setSeen() {
        editor = pref.edit();
        editor.putBoolean(KEY, true);
        editor.apply();
    }

}
