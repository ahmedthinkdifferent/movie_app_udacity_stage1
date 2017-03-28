package com.udacity.movieapp.ui.screen.moviesList;

import com.udacity.movieapp.R;
import com.udacity.movieapp.data.helper.JsonParser;
import com.udacity.movieapp.data.listener.LoadMoviesListener;
import com.udacity.movieapp.data.networking.ApiManager;
import com.udacity.movieapp.util.ConnectionHelper;

import okhttp3.ResponseBody;

/**
 * Created by ahmed agamy on 27/03/2017.
 */

class MoviesPresenter implements LoadMoviesListener {

    private ConnectionHelper connectionHelper;
    private MoviesView moviesView;

    MoviesPresenter(ConnectionHelper connectionHelper, MoviesView moviesView) {
        this.connectionHelper = connectionHelper;
        this.moviesView = moviesView;

    }


    void loadMovies(String moviesType) {
        if (!connectionHelper.isInternetAvailable()) {
            // internet not available.
            moviesView.hideMoviesRecyclerList();
            moviesView.showErrorLoadingDataView(R.string.internet_not_available);
        }
        // internet available.
        moviesView.hideErrorLoadingDataView();
        moviesView.showProgress();
        ApiManager.loadMovies(moviesType, this);
    }


    @Override
    public void loadMoviesSuccess(ResponseBody jsonData) {
        try {
            moviesView.showMoviesRecyclerList();
            moviesView.showMovies(JsonParser.getMovies(jsonData.string()));
            moviesView.showToastMessage(R.string.load_movies_successfully);
        } catch (Exception e) {
            e.printStackTrace();
            moviesView.hideMoviesRecyclerList();
            moviesView.showErrorLoadingDataView(R.string.cannot_load_movies);
        }
        moviesView.dismissProgress();
    }

    @Override
    public void loadMoviesFail(Throwable error) {
        error.printStackTrace();
        moviesView.dismissProgress();
        moviesView.hideMoviesRecyclerList();
        moviesView.showErrorLoadingDataView(R.string.cannot_load_movies);
    }
}
