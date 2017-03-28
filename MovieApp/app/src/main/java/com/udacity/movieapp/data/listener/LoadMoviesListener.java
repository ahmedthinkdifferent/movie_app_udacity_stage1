package com.udacity.movieapp.data.listener;

import okhttp3.ResponseBody;

/**
 * Created by ahmed agamy on 26/03/2017.
 */

public interface LoadMoviesListener {


    void loadMoviesSuccess(ResponseBody jsonData);

    void loadMoviesFail(Throwable e);
}
