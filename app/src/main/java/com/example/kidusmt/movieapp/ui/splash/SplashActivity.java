package com.example.kidusmt.movieapp.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.ui.home.HomeActivity;
import com.example.kidusmt.movieapp.ui.login.LoginActivity;

/**
 * Created by KidusMT on 12/25/2017.
 */

public class SplashActivity extends BaseActivity {

    private static final String KEY = "SEEN";

    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref = getSharedPreferences("MovieApp",MODE_PRIVATE);

        ImageView splashLogo = findViewById(R.id.iv_splash_img);
        //find out if the user has seen the splash screen - always false for now
        if (isSeen()){
            openTourActivity();
            return;
        }

        Animation alphaAnim = AnimationUtils.loadAnimation(this,R.anim.alpha);
//        Animation fadeInAnim = AnimationUtils.loadAnimation(this,R.anim.splash_fade_in);
//        Animation translate = AnimationUtils.loadAnimation(this,R.anim.translate);

        splashLogo.startAnimation(alphaAnim);

        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //SKIP THE CHECKING PART FOR NOW - WILL WORK OUT LATER
//                setSeen();//sets the seenVariable to true
                openTourActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void skipSplashScreen(View v){
        openTourActivity();
    }

    private boolean isSeen(){
        return false;//pref.getBoolean(KEY, false);
    }

    public void openTourActivity(){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    private void setSeen(){
        editor = pref.edit();
        editor.putBoolean(KEY, true);
        editor.apply();
    }
}
