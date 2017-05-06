package com.udacity.movieapp.ui.screen.moviedetails;

import com.udacity.movieapp.data.model.MovieReview;
import com.udacity.movieapp.data.model.MovieTrailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed agamy on 06/05/2017.
 */

interface MovieDetailsView {
    void showMovieTrailers(List<MovieTrailer> movieTrailers);

    void showMovieReviews(ArrayList<MovieReview> movieReviews);

    void showProgress();

    void dismissProgress();
}
