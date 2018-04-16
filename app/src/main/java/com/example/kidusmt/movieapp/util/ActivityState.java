package com.example.kidusmt.movieapp.util;

import static com.example.kidusmt.movieapp.util.Constants.STATE_COMPLETED;
import static com.example.kidusmt.movieapp.util.Constants.STATE_ERROR;
import static com.example.kidusmt.movieapp.util.Constants.STATE_IDLE;
import static com.example.kidusmt.movieapp.util.Constants.STATE_LOADING;

/**
 * Created by KidusMT on 3/26/2018.
 */

public class ActivityState {

    private static ActivityState instance;

    public static ActivityState getInstance(){
        return instance == null ? instance = new ActivityState() : instance;
    }

    private int state;
    private Throwable error;

    public void setStateLoading(){
        state = STATE_LOADING;
    }

    public boolean loading() {
        return state == STATE_LOADING;
    }

    public void setStateCompleted(){
        state = STATE_COMPLETED;
    }

    public boolean completed() {
        return state == STATE_COMPLETED;
    }

    public boolean error(){
        return state == STATE_COMPLETED;
    }

    public void setStateError(Throwable error){
        state = STATE_ERROR;
        this.error = error;
    }

    public Throwable getError(){
        return error;
    }

    public void reset(){
        state = STATE_IDLE;
        error = null;
    }
}
