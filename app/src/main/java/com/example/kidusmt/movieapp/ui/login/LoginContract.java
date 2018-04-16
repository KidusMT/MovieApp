package com.example.kidusmt.movieapp.ui.login;

import com.example.kidusmt.movieapp.base.mvp.BasePresenter;
import com.example.kidusmt.movieapp.base.mvp.BaseView;

/**
 * Created by KidusMT on 4/17/2018.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter>{
        void openSignUpActivity();
        void openHomeActivity();
        void openFbLoginDialog();
    }

    interface Presenter extends BasePresenter<View>{
        void onSingInClicked();
        void onSignUpClicked();
        void fbLoginClicked();
    }
}
