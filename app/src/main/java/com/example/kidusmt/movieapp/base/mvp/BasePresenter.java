package com.example.kidusmt.movieapp.base.mvp;

/**
 * Interface representation of a base Presenter in the MVP pattern
 */
public interface BasePresenter<V extends BaseView> {

    /**
     * Start the presenter
     */
    void start();

    /**
     * Attach the view
     */
    void attachView(V view);

    /**
     * Detach the view
     */
    void detachView();

    /**
     * Get the view
     */
    V getView();
}
