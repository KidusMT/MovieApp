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
import com.example.kidusmt.movieapp.data.movie.Movie;
import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.example.kidusmt.movieapp.data.movie.MovieUpComing;
import com.example.kidusmt.movieapp.data.movie.MoviesResponse;
import com.example.kidusmt.movieapp.data.movie.RepoMovie;
import com.example.kidusmt.movieapp.data.movie.local.MovieLocal;
import com.example.kidusmt.movieapp.data.movie.remote.MovieRemote;
import com.example.kidusmt.movieapp.ui.detail.MovieDetailActivity;
import com.example.kidusmt.movieapp.util.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPopular extends BaseFragment implements HomeContract.View {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    Call<MoviesResponse> callTopRated;
    private HomeContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for just making sure our api key is available
        if (App.API_KEY.isEmpty()) {
            e("Please obtain your API KEY first from TheMovieDB.org");
            return;
        }

        presenter = new HomePresenter(new RepoMovie(
                new MovieLocal(App.boxStore),
                new MovieRemote()));

        callTopRated = MovieRemote.movieService.getPopularMovies(App.API_KEY);

        callTopRated.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                //TODO have to find what to do with movieList
                List<Movie> movieList = response.body().getResults();
                adapter = new MovieAdapter(presenter);
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

    @Override
    public void showPopularMovies(List<MoviePopular> moviePopulars) {
        adapter.(moviePopulars);
    }

    @Override
    public void showUpComingMovies(List<MovieUpComing> movieUpComings) {

    }

    @Override
    public void showInTheaterMovies(List<MovieInTheater> movieInTheaters) {

    }

    @Override
    public void showTopRatedMovies(List<MovieTopRated> movieTopRated) {

    }

    @Override
    public void openMovieDetail(int position) { presenter.onMovieClicked(position);}

    @Override
    public void refresh() {
        //This will notify for any change in the recyclerView for updating the UI
        adapter.notifyDataSetChanged();
    }

    @Override
    public void attachPresenter(HomeContract.Presenter presenter) {
        //TODO: will find out about it
    }

    @Override
    public void close() {
        //TODO: do something like --> finish()
    }
}
