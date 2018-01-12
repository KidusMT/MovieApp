package com.example.kidusmt.movieapp.ui.detail;

import com.example.kidusmt.movieapp.data.movie.Cast;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter{

    private MovieDetailContract.View view;
    private List<Cast> casts;

    public MovieDetailPresenter(MovieDetailContract.View view, List<Cast> casts){
        this.view = view;
        this.casts = casts;
    }

    @Override
    public void loadDetail() {
            this.view.showDetail(casts);
    }

    @Override
    public void onCardClicked(int position, String imgPath) {
        view.openImage(casts.get(position).getProfilePath());
    }


    @Override
    public void onBackArrowClicked() {

    }
}
