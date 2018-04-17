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
import com.example.kidusmt.movieapp.data.model.Cast;
import com.example.kidusmt.movieapp.data.repo.cast.RepoCast;
import com.example.kidusmt.movieapp.data.repo.cast.local.CastLocal;
import com.example.kidusmt.movieapp.data.repo.cast.remote.CastRemote;
import com.example.kidusmt.movieapp.ui.image.OpenImageActivity;
import com.example.kidusmt.movieapp.util.App;
import com.example.kidusmt.movieapp.util.RecyclerItemClickListener;
import com.example.kidusmt.movieapp.util.Utils;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.kidusmt.movieapp.util.Constants.IMAGE_SELECTED;
import static com.example.kidusmt.movieapp.util.Constants.MOVIE_BACKDROP;
import static com.example.kidusmt.movieapp.util.Constants.MOVIE_ID;
import static com.example.kidusmt.movieapp.util.Constants.MOVIE_REVIEW;
import static com.example.kidusmt.movieapp.util.Constants.TMDB_IMAGE_PATH;

public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {

    RecyclerView recyclerView;
    CastAdapter castAdapter;
    ProgressWheel progressWheel;
    List<Cast> castList;
    TextView reviewDetail;
    ImageView backdrop_img;
    int movieId = 0;
    MovieDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        castList = new ArrayList<>();

        initCollapsingToolbar();

        progressWheel = findViewById(R.id.cast_progress_wheel);
        reviewDetail = findViewById(R.id.tv_review);
        backdrop_img = findViewById(R.id.backdrop);

        if (getIntent() != null) {
            movieId = getIntent().getIntExtra(MOVIE_ID, 0);
            reviewDetail.setText(getIntent().getStringExtra(MOVIE_REVIEW));
            Picasso.with(this)
                    .load(getIntent()
                            .getStringExtra(TMDB_IMAGE_PATH+MOVIE_BACKDROP))
                    .placeholder(R.color.colorAccent)
                    .into(backdrop_img);
        }

        presenter = new MovieDetailPresenter(new RepoCast(
                new CastLocal(App.boxStore),
                new CastRemote()), movieId);

        castAdapter = new CastAdapter(castList, presenter);
        recyclerView = findViewById(R.id.cast_recycler_view);
        LinearLayoutManager layoutManagerStart
                = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManagerStart);

        SnapHelper snapHelperStart = new StartSnapHelper();
        snapHelperStart.attachToRecyclerView(recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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
        castAdapter.update(castList);
    }

    @Override
    public void openImage(String imageURI) {
        Intent intent = new Intent(this, OpenImageActivity.class);
        intent.putExtra(IMAGE_SELECTED, imageURI);
        startActivity(intent);
    }

    @Override
    public void attachPresenter(MovieDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        presenter.detachView();
        super.onPause();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showLoading(String message) {
        progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressWheel.setVisibility(View.GONE);
    }

    @Override
    public void onUnknownError(String error) {
        Utils.toast(this, error);
    }

    @Override
    public void onTimeout() {
        Utils.toast(this, "connection timeout");
    }

    @Override
    public void onNetworkError() {
        Utils.toast(this, "network error");
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void onConnectionError() {
        Utils.toast(this, "connection error");
    }
}
