package com.example.kidusmt.movieapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseActivity;
import com.example.kidusmt.movieapp.data.movie.Cast;
import com.example.kidusmt.movieapp.data.movie.CastResponse;
import com.example.kidusmt.movieapp.data.movie.remote.MovieRemote;
import com.example.kidusmt.movieapp.ui.home.HomeActivity;
import com.example.kidusmt.movieapp.util.App;
import com.example.kidusmt.movieapp.util.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {

    RecyclerView recyclerView;
    CastAdapter castAdapter;
    Call<CastResponse> callDetail;
    List<Cast> castList;
    TextView reviewDetail;
    ImageView backdrop_img;

    MovieDetailContract.Presenter presenter;

    public MovieDetailActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initCollapsingToolbar();

        reviewDetail = findViewById(R.id.tv_review);
        backdrop_img = findViewById(R.id.backdrop);

        int movieId = getIntent().getIntExtra("movie_id",0);

        reviewDetail.setText(getIntent().getStringExtra("movie_review"));
        Picasso.with(this)
                .load(getIntent().getStringExtra("movie_backdrop"))
                .placeholder(R.color.colorAccent)
                .into(backdrop_img);

        //for just making sure our api key is available
        if (App.API_KEY.isEmpty()) {
            e("Please obtain your API KEY first from themoviedb.org");
            return;
        }

        recyclerView = findViewById(R.id.cast_recycler_view);
        LinearLayoutManager layoutManagerStart
                = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManagerStart);

        SnapHelper snapHelperStart = new StartSnapHelper();
        snapHelperStart.attachToRecyclerView(recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        callDetail = MovieRemote.getApiService().getCastList(movieId, App.API_KEY);

        callDetail.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                castList = response.body().getCast();

            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                //TODO: display error message here, find a way for better display
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //TODO: handles the onclick listener
                        toast("working click");
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        //TODO: display the credit's name
                        toast("credit's name");//actors name for the clicked image of the actor
                    }
                }));

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void showDetail(List<Cast> castList) {
        castAdapter = new CastAdapter(this, castList);
        recyclerView.setAdapter(castAdapter);
    }

    @Override
    public void openImage(String imageURI) {
        //TODO I have to implements the opening of an image when clicked
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void openHomeActivity() {
        //TODO think about how to go back to the specific tab you where working to rather than opening HomeActivity by default
        startActivity(new Intent(this, HomeActivity.class));
    }
}
