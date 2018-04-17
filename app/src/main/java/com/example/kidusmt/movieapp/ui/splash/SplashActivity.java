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
import com.example.kidusmt.movieapp.ui.login.LoginActivity;
import com.example.kidusmt.movieapp.ui.tour.TourActivity;
import com.example.kidusmt.movieapp.util.App;
import com.facebook.FacebookSdk;

import static com.example.kidusmt.movieapp.util.Constants.APP_NAME;
import static com.example.kidusmt.movieapp.util.Constants.KEY;

/**
 * Created by KidusMT on 12/25/2017.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    private SplashContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(new RepoGenre(
                new GenreLocal(App.boxStore),
                new GenreRemote()
        ));

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
        close();
    }

    private void setSeen() {
        editor = pref.edit();
        editor.putBoolean(KEY, true);
        editor.apply();
    }

    @Override
    protected void onPause() {
        presenter.detachView();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void attachPresenter(SplashContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showLoading(String message) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onUnknownError(String error) {
    }

    @Override
    public void onTimeout() {
    }

    @Override
    public void onNetworkError() {
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void onConnectionError() {
    }
}
