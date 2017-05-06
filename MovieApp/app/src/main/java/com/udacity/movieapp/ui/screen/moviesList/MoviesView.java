package com.udacity.movieapp.ui.screen.movieslist;

import android.support.annotation.StringRes;

import com.udacity.movieapp.data.model.Movie;

import java.util.List;

/**
 * Created by ahmed agamy on 27/03/2017.
 */

public interface MoviesView {

    void showProgress();

    void dismissProgress();

    void showMovies(List<Movie> movies);

    void showErrorLoadingDataView(@StringRes int errorMessage);

    void showToastMessage(@StringRes int message);
    void hideErrorLoadingDataView();

    void showMoviesRecyclerList();
    void hideMoviesRecyclerList();
}
