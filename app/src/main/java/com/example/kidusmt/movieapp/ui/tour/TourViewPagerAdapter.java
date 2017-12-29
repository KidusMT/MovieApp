package com.example.kidusmt.movieapp.ui.tour;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Adapter responsible for providing the Fragment elements to the ViewPager
 */
public class TourViewPagerAdapter extends FragmentPagerAdapter {

    /**
     * Constructor for the TourViewPagerAdapter (required)
     *
     * @param fm FragmentManager used to show and hide fragments
     */
    public TourViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Retrieve a Fragment for the given position - called by the ViewPager to request a fragment.
     *
     * @param position Fragment position to use for retrieval
     * @return Fragment for the given position
     */
    @Override//FACTORY METHOD DESIGN PATTERN
    public Fragment getItem(int position) {
        return TourFragment.newInstance(position);
    }

    /**
     * Retrieve the count of Fragments to show. This is used by the ViewPager to know when to stop.
     * @return
     */
    @Override
    public int getCount() {
        return TourActivity.PAGE_COUNT;
    }
}