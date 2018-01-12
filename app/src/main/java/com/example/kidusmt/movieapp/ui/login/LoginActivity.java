package com.example.kidusmt.movieapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.ui.home.HomeActivity;
import com.facebook.login.widget.LoginButton;

/**
 * Created by KidusMT on 1/3/2018.
 */

public class LoginActivity extends BaseActivity {

    Button btn_login, btn_fb_login;
    LoginButton fb_login;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //HIDING SOFT INPUT KEYBOARD
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btn_login = findViewById(R.id.email_sign_in_button);
        fb_login = findViewById(R.id.login_button);
//        btn_fb_sign_up = findViewById(R.id.email_sign_up_button);

        //EMAIL LOGIN AUTHENTICATION
        btn_login.setOnClickListener(a->openHomeActivity());
        //FACEBOOK LOGIN AUTHENTICATION
        fb_login.setOnClickListener(b->openHomeActivity());
        //FOR THE FACEBOOK LOGIN BUTTON ACTION
//        btn_fb_sign_up.setOnClickListener(c->openHomeActivity());

    }

    public void openHomeActivity(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
