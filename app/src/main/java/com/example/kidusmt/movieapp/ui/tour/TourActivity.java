package com.example.kidusmt.movieapp.ui.tour;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.ui.login.LoginActivity;

import static com.example.kidusmt.movieapp.util.Constants.APP_NAME;
import static com.example.kidusmt.movieapp.util.Constants.KEY;

/**
 * Tour activity for showing the person what the app is about
 */
public class TourActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    public static int PAGE_COUNT = 3;

    private ViewPager viewPager;
    private TourViewPagerAdapter adapter;
    private TextView tourInfoLabel;

    private LinearLayout pagerIndicatorContainer;
    private SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getSharedPreferences(APP_NAME, MODE_PRIVATE);
        if (notFirstTime()){//if not first time
            openLoginActivity();
        }

        setContentView(R.layout.activity_tour);

        // Retrieve the ViewPager and the tour info label
        viewPager = findViewById(R.id.tourViewPager);
        tourInfoLabel = findViewById(R.id.tourInfoLabel);
        pagerIndicatorContainer = findViewById(R.id.tourIndicatorContainer);

        // Create the TourViewPagerAdapter and attach it to the ViewPager
        adapter = new TourViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // Attach an OnPageChangeListener to the ViewPager to detect changes
        viewPager.addOnPageChangeListener(this);
        generateIndicators();
    }

    private void generateIndicators() {
        for (int i = 0; i < PAGE_COUNT; i++) {
            View indicator = getLayoutInflater().inflate(R.layout.pager_indicator_layout,
                    pagerIndicatorContainer, false);
            pagerIndicatorContainer.addView(indicator);
        }
        //selectIndicator(0);
    }

    @Override
    public void onPageSelected(int position) {
        int id;
        switch (position) {
            case 0:
                id = R.string.tour_info_label_0;
                break;
            case 1:
                id = R.string.tour_info_label_1;
                break;
            default:
                id = R.string.tour_info_label_2;
        }
        tourInfoLabel.setText(id);
        selectIndicator(position);
    }

    private void selectIndicator(int position) {
        for (int i = 0; i < PAGE_COUNT; i++) {
            View indicator = pagerIndicatorContainer.getChildAt(i);
            int background = position == i ? R.drawable.pager_indicator_selected : R.drawable.pager_indicator_normal;
            indicator.setBackgroundResource(background);
        }
    }

    private boolean notFirstTime(){
        return pref.getBoolean(KEY, false);
    }

    public void openLoginActivity(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}