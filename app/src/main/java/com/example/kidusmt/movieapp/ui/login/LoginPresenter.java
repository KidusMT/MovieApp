package com.example.kidusmt.movieapp.ui.login;

import com.example.kidusmt.movieapp.util.ActivityState;

/**
 * Created by KidusMT on 4/17/2018.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private ActivityState state;
    private LoginContract.View view;
    public LoginPresenter(){
        state = ActivityState.getInstance();
    }

    @Override
    public void start() {
        if (state.loading()) return;
        view.hideLoading();
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public LoginContract.View getView() {
        return this.view;
    }

    @Override
    public void onSingInClicked() {
        view.openHomeActivity();
    }

    @Override
    public void onSignUpClicked() {
        view.openSignUpActivity();
    }

    @Override
    public void fbLoginClicked() {

    }
}
