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

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        MoviesFragment[] movieTabs = {MoviesFragment.newInstance(Constants.CATEGORY_POPULAR),
//                MoviesFragment.newInstance(Constants.CATEGORY_TOP_RATED),
//                MoviesFragment.newInstance(Constants.CATEGORY_UPCOMING),
//                MoviesFragment.newInstance(Constants.CATEGORY_IN_THEATER)};

        adapter.addFragment(new MoviesFragment(),Constants.CATEGORY_POPULAR);
        adapter.addFragment(new MoviesFragment(),Constants.CATEGORY_TOP_RATED);
        adapter.addFragment(new MoviesFragment(),Constants.CATEGORY_UPCOMING);
        adapter.addFragment(new MoviesFragment(),Constants.CATEGORY_IN_THEATER);

//        for(int i = 0; i< 4; i++){
//            adapter.addFragment(movieTabs[i]);
//        }
        viewPager.setAdapter(adapter);
    }

    //for logout from navigationDrawer
    public void logout(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    //CONTROLS THE SELECTION AND SWIPE FOR THE TABS
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager){
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
