package com.example.kidusmt.movieapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.ui.login.LoginActivity;
import com.example.kidusmt.movieapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KidusMT on 12/22/2017.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        MoviesFragment[] movieTabs = {
                MoviesFragment.newInstance(Constants.FCATEGORY_POPULAR),
                MoviesFragment.newInstance(Constants.FCATEGORY_TOP_RATED),
                MoviesFragment.newInstance(Constants.FCATEGORY_UPCOMING),
                MoviesFragment.newInstance(Constants.FCATEGORY_IN_THEATER)
        };

        adapter.addFragment(movieTabs[0], Constants.CATEGORY_POPULAR);
        adapter.addFragment(movieTabs[1], Constants.CATEGORY_TOP_RATED);
        adapter.addFragment(movieTabs[2], Constants.CATEGORY_UPCOMING);
        adapter.addFragment(movieTabs[3], Constants.CATEGORY_IN_THEATER);

        viewPager.setAdapter(adapter);
    }

    //for logout from navigationDrawer
    public void logout() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    //CONTROLS THE SELECTION AND SWIPE FOR THE TABS
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
//            return null;
        }
    }

}
