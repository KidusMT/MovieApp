package com.example.kidusmt.movieapp.ui.detail;

import com.example.kidusmt.movieapp.base.mvp.BasePresenter;
import com.example.kidusmt.movieapp.base.mvp.BaseView;
import com.example.kidusmt.movieapp.data.remote.cast.Cast;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface MovieDetailContract {

    interface View extends BaseView<Presenter> {

        /**
         * opens
         * @param castList
         */
        void showDetail(List<Cast> castList);

        /**
         * opens the image after the card has been clicked
         * @param imageURI
         */
        void openImage(String imageURI);

        /**
         * closes the detail activity
         */
        void closeActivity();

        /**
         * opens the homeActivity after the current activity has been closed
         */
        void openHomeActivity();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * load the credit's card for all the casts
         */
        void loadDetail();

        /**
         * controls the click on the image
         */
        void onCardClicked(int position, String imgPath);

        /**
         * controls the on BackArrowClick for closing the DetailActivity and open HomeActivity
         */
        void onBackArrowClicked();
    }
}
