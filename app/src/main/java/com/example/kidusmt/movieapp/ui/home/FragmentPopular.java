package com.example.kidusmt.movieapp.ui.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseFragment;
import com.example.kidusmt.movieapp.data.Movie;
import com.example.kidusmt.movieapp.data.MoviesResponse;
import com.example.kidusmt.movieapp.data.rest.ApiClient;
import com.example.kidusmt.movieapp.util.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPopular extends BaseFragment {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    Call<MoviesResponse> callTopRated;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for just making sure our api key is available
        if (App.API_KEY.isEmpty()) {
            e("Please obtain your API KEY first from themoviedb.org");
            return;
        }

        callTopRated = ApiClient.getApiService().getPopularMovies(App.API_KEY);

        callTopRated.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movieList = response.body().getResults();
                adapter = new MovieAdapter(getActivity(),movieList);//movieList is needed
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                e(t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_top_rated,container,false);

        recyclerView = v.findViewById(R.id.recycler_view_top_rated);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(
                new App.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return v;
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
