package com.example.kidusmt.movieapp.ui.tour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseFragment;
import com.example.kidusmt.movieapp.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

/**
 * Fragment used by the tour screen, for displaying the images.
 */
public class TourFragment extends BaseFragment {

    private static final String ARG_POSITION = "POSITION";

    /**
     * Static method for creating a Fragment object.
     *
     * @param position int value used to determine which image to show in the fragment.
     * @return created TourFragment
     */
    public static TourFragment newInstance(int position) {
        // Create a new Fragment
        TourFragment fragment = new TourFragment();

        // Create a Bundle arg value and attach an int position to it (for the image)
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);

        // Add the Bundle arg value to the Fragment and return the fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tour, container, false);

        Bundle args = getArguments();
        if (args == null) throw new NullPointerException("Bundle args cannot be null");

        final int position = args.getInt(ARG_POSITION, 0);

        ImageView tourImageView = root.findViewById(R.id.tourFragmentImageView);
        int image;
        switch (position) {
            case 0:
                image = R.drawable.splash_movie_2;
                break;
            case 1:
                image = R.drawable.splash_movies;
                break;
            default:
                image = R.drawable.splash_movie_2;
        }

        if (position == 0) {
            tourImageView.setImageResource(R.drawable.splash_movie_2);
        } else {
            Picasso.with(getContext())
                    .load(image)
                    .fit()
                    .into(tourImageView);

        }

        root.setOnClickListener(v -> {
            if (position != TourActivity.PAGE_COUNT - 1) return;
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });

        return root;
    }
}
