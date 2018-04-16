package com.example.kidusmt.movieapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.ui.home.HomeActivity;
import com.example.kidusmt.movieapp.util.Utils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by KidusMT on 1/3/2018.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View{

    Button btn_login;
    ProgressWheel progressWheel;
    LoginContract.Presenter presenter;
    LoginButton fb_login;

    private static final String EMAIL = "email";
    CallbackManager callbackManager;

    private static final int RC_SIGN_IN = 007;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        //HIDING SOFT INPUT KEYBOARD
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        presenter = new LoginPresenter();

        progressWheel = findViewById(R.id.login_progress_wheel);
        btn_login = findViewById(R.id.email_sign_in_button);
        fb_login = findViewById(R.id.login_button);

        // Callback registration
        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                Utils.toast(LoginActivity.this, "Facebook access token " + loginResult.getAccessToken());
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Utils.toast(LoginActivity.this, exception.getMessage());
            }
        });

        //EMAIL LOGIN AUTHENTICATION
        btn_login.setOnClickListener(a->openHomeActivity());
        //FACEBOOK LOGIN AUTHENTICATION
        fb_login.setOnClickListener(b->openHomeActivity());
        //FOR THE FACEBOOK LOGIN BUTTON ACTION
//        btn_fb_sign_up.setOnClickListener(c->openHomeActivity());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected void onPause() {
        presenter.detachView();
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void openSignUpActivity() {
        openHomeActivity();
    }

    public void openHomeActivity(){
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void openFbLoginDialog() {

    }

    @Override
    public void attachPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showLoading(String message) {
        progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressWheel.setVisibility(View.GONE);
    }

    @Override
    public void onUnknownError(String error) {
        Utils.toast(this, error);
    }

    @Override
    public void onTimeout() {
        Utils.toast(this, "connection timeout");
    }

    @Override
    public void onNetworkError() {
        Utils.toast(this, "network error");
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void onConnectionError() {
        Utils.toast(this, "connection error");
    }
}
