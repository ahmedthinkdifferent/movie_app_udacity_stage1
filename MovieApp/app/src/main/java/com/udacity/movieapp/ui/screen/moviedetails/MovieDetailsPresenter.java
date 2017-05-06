package com.udacity.movieapp.ui.screen.moviedetails;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.udacity.movieapp.data.helper.JsonParser;
import com.udacity.movieapp.data.listener.LoadMoviesListener;
import com.udacity.movieapp.data.model.MovieReview;
import com.udacity.movieapp.data.model.MovieTrailer;
import com.udacity.movieapp.data.model.Movie_Table;
import com.udacity.movieapp.data.networking.ApiManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by ahmed agamy on 06/05/2017.
 */

class MovieDetailsPresenter {


    private MovieDetailsView view;

    void attachView(MovieDetailsView view) {
        this.view = view;
    }


    void getMovieTrailers(String movieId) {
        // check if movie saved in database or not.
        List<MovieTrailer> movieTrailers = SQLite.select().from(MovieTrailer.class).where(Movie_Table.id.eq(movieId)).queryList();
        if (movieTrailers.size() > 0) {
            view.showMovieTrailers(movieTrailers);
            return;
        }
        // connect to server to get data.
        ApiManager.getMovieTrailers(movieId, new LoadMoviesListener() {
            @Override
            public void loadMoviesSuccess(ResponseBody jsonData) {
                try {
                    List<MovieTrailer> trailers = JsonParser.getMovieTrailers(jsonData.string());
                    view.showMovieTrailers(trailers);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                    loadMoviesFail(e);
                }
            }

            @Override
            public void loadMoviesFail(Throwable e) {

            }
        });
    }

    void getMoviePreview(final String movieId) {
        view.showProgress();
        List<MovieReview> movieReviews = SQLite.select().from(MovieReview.class).where(Movie_Table.id.eq(movieId)).queryList();
        if (movieReviews.size() > 0) {
            view.dismissProgress();
            ArrayList<MovieReview> reviews = new ArrayList<>();
            reviews.addAll(movieReviews);
            view.showMovieReviews(reviews);
            return;
        }
        ApiManager.getMovieReviews(movieId, new LoadMoviesListener() {
            @Override
            public void loadMoviesSuccess(ResponseBody jsonData) {
                view.dismissProgress();
                try {
                    List<MovieReview> movieReviewList = JsonParser.getMovieReviews(jsonData.string());
                    ArrayList<MovieReview> reviews = new ArrayList<>();
                    reviews.addAll(movieReviewList);
                    saveReviewsInDb(reviews);
                    view.showMovieReviews(reviews);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            private void saveReviewsInDb(ArrayList<MovieReview> reviews) {
                for (MovieReview review : reviews) {
                    review.save();
                }
            }

            @Override
            public void loadMoviesFail(Throwable e) {
                view.dismissProgress();
                e.printStackTrace();
            }
        });
    }

    public void detachView() {
        view = null;
    }

    public boolean isDetachView() {
        return view == null;
    }

}
