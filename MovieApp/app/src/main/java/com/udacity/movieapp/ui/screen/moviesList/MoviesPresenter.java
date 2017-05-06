package com.udacity.movieapp.ui.screen.movieslist;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.udacity.movieapp.R;
import com.udacity.movieapp.data.helper.JsonParser;
import com.udacity.movieapp.data.listener.LoadMoviesListener;
import com.udacity.movieapp.data.model.Movie;
import com.udacity.movieapp.data.networking.ApiManager;
import com.udacity.movieapp.util.ConnectionHelper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.ResponseBody;

/**
 * Created by ahmed agamy on 27/03/2017.
 */

class MoviesPresenter implements LoadMoviesListener {

    private ConnectionHelper connectionHelper;
    private MoviesView moviesView;
    private Map<String, List<Movie>> moviesList = new TreeMap<>();
    private String moviesType;

    MoviesPresenter(ConnectionHelper connectionHelper, MoviesView moviesView) {
        this.connectionHelper = connectionHelper;
        this.moviesView = moviesView;

    }


    void loadMovies(String moviesType) {
        this.moviesType = moviesType;
        if (moviesList.containsKey(moviesType)) {
            moviesView.showMovies(moviesList.get(moviesType));
            return;
        }

        if (moviesType.equals("favourites")) {
            List<Movie> movies = SQLite.select().from(Movie.class).queryList();
            moviesView.showMovies(movies);
            return;
        }

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
            List<Movie> movies = JsonParser.getMovies(jsonData.string());
            moviesList.put(moviesType, movies);
            moviesView.showMovies(movies);
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
