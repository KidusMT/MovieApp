package com.example.kidusmt.movieapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.ui.login.LoginActivity;
import com.example.kidusmt.movieapp.R;
import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by KidusMT on 12/22/2017.
 */

public class MainActivity extends BaseActivity {

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        for controlling the logout action
//        logout = findViewById(R.id.R.logout);
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logout();
//            }
//        });

        //if you're not logged in user then you will be redirected to login screen
       // loggedIn = AccessToken.getCurrentAccessToken() == null;

    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
    }
}
