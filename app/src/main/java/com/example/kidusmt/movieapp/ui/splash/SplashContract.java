package com.example.kidusmt.movieapp.ui.splash;

import com.example.kidusmt.movieapp.base.mvp.BasePresenter;
import com.example.kidusmt.movieapp.base.mvp.BaseView;

/**
 * Created by KidusMT on 4/17/2018.
 */

public interface SplashContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter<View>{
        void loadGenres();
    }
}
