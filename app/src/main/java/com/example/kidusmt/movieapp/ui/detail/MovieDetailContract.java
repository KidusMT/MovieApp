package com.example.kidusmt.movieapp.ui.detail;

import com.example.kidusmt.movieapp.base.mvp.BasePresenter;
import com.example.kidusmt.movieapp.base.mvp.BaseView;
import com.example.kidusmt.movieapp.data.model.Cast;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface MovieDetailContract {

    interface View extends BaseView<Presenter> {
        void showDetail(List<Cast> castList);
        void openImage(String imageURI);
    }

    interface Presenter extends BasePresenter<View> {
        void loadDetail();
        void onCardClicked(String imgPath);
    }
}
