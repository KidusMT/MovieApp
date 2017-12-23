package com.example.kidusmt.movieapp.base.mvp;

/**
 * Interface representation of a base View in the MVP pattern
 */
public interface BaseView<T extends BasePresenter> {

    /**
     * Attach the Presenter to the view
     */
    void attachPresenter(T presenter);

    /**
     * Dismiss the View from the UI
     */
    void close();
}