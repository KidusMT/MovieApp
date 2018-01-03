package com.example.kidusmt.movieapp.base.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.kidusmt.movieapp.util.Utils;


/**
 * BaseActivity for supporting the rest of the Activity classes, providing common functionality
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d("< ------------------------------ onCreate(Bundle) ------------------------------ >");
    }

    @Override
    protected void onStart() {
        super.onStart();
        d("< ------------------------------ onStart() ------------------------------ >");
    }

    @Override
    protected void onResume() {
        super.onResume();
        d("< ------------------------------ onResume() ------------------------------ >");
    }

    @Override
    protected void onPause() {
        super.onPause();
        d("< ------------------------------ onPause() ------------------------------ >");
    }

    @Override
    protected void onStop() {
        super.onStop();
        d("< ------------------------------ onStop() ------------------------------ >");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        d("< ------------------------------ onDestroy() ------------------------------ >");
    }


    protected void d(String message) {
        Utils.d(this, message);
    }

    protected void e(String message) {
        Utils.e(this, message);
    }


    protected void toast(String message) {
        toast(message, Toast.LENGTH_SHORT);
    }


    protected void toast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }
}
