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
import com.example.kidusmt.movieapp.data.rest.ApiService;
import com.example.kidusmt.movieapp.util.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNowPlaying extends BaseFragment {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    Call<MoviesResponse> callInTheater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for just making sure our api key is available
        if (App.API_KEY.isEmpty()) {
            e("Please obtain your API KEY first from themoviedb.org");
            return;
        }

        callInTheater = ApiClient.getApiService().getNowPlayingMovies(App.API_KEY);

        callInTheater.enqueue(new Callback<MoviesResponse>() {
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
        View v = inflater.inflate(R.layout.fragment_fragment_now_playing,container, false);

        recyclerView = v.findViewById(R.id.recycler_view_now_playing);
        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(
                new App.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return v;
    }
//
//    /**
//     * Adding few Movie for testing
//     */
//    private void prepareMovie(RecyclerView.Adapter adapter) {
//        int[] covers = new int[]{
//                R.drawable.poster_i_am_wrath,
//                R.drawable.poster_civil_war,
//                R.drawable.poster_hunts_man,
//                R.drawable.poster_jungle_book,
//                R.drawable.poster_neighbour,
//                R.drawable.poster_pandemic
//        };
//
//        Movie a = new Movie("I Am Wrath", "Action",covers[0]);
//        movieList.add(a);
//
//        a = new Movie("Civil War","Action, Adventure",covers[1]);
//        movieList.add(a);
//
//        a = new Movie("HuntsMan","Adventure, Action",covers[2]);
//        movieList.add(a);
//
//        a = new Movie("Jungle Book", "Adventure",covers[3]);
//        movieList.add(a);
//
//        a = new Movie("Neighbor", "Comedy",covers[4]);
//        movieList.add(a);
//
//        a = new Movie("Pandemic","Drama",covers[5]);
//        movieList.add(a);
//
//        adapter.notifyDataSetChanged();
//    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
