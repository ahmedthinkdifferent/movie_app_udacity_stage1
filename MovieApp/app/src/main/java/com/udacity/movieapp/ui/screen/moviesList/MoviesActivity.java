package com.udacity.movieapp.ui.screen.movieslist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.movieapp.R;
import com.udacity.movieapp.adapter.moviesList.MovieItemClickListener;
import com.udacity.movieapp.adapter.moviesList.MoviesAdapter;
import com.udacity.movieapp.data.model.Movie;
import com.udacity.movieapp.ui.screen.moviedetails.MovieDetailsActivity;
import com.udacity.movieapp.util.ConnectionHelper;
import com.udacity.movieapp.util.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity implements MoviesView, MovieItemClickListener {

    private static final String POPULAR_MOVIES = "popular";
    private static final String TOP_RATED_MOVIES = "top_rated";
    private static final String FAVOURITES = "favourites";

    @BindView(R.id.rv_movies)
    RecyclerView moviesList;
    private String moviesCategory = POPULAR_MOVIES;
    private MoviesPresenter moviesPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        moviesPresenter = new MoviesPresenter(new ConnectionHelper(this), this);
        moviesPresenter.loadMovies(moviesCategory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_popular) {
            moviesCategory = POPULAR_MOVIES;
        } else if (itemId == R.id.action_top_rated) {
            moviesCategory = TOP_RATED_MOVIES;
        } else {
            moviesCategory = FAVOURITES;
        }

        moviesPresenter.loadMovies(moviesCategory);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressDialog = Utility.createProgressDialog(this, R.string.loading_movies);
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showMovies(List<Movie> movies) {
        moviesList.setLayoutManager(new GridLayoutManager(this, 2));
        moviesList.setAdapter(new MoviesAdapter(this, movies));
    }

    @Override
    public void showErrorLoadingDataView(@StringRes int errorMessage) {
        View errorView = findViewById(R.id.layout_error);
        errorView.setVisibility(View.VISIBLE);
        TextView errorTextView = (TextView) errorView.findViewById(R.id.tv_error_info);
        errorTextView.setText(errorMessage);
        Button tryAgainButton = (Button) errorView.findViewById(R.id.btn_try_again);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesPresenter.loadMovies(moviesCategory);
            }
        });
    }

    @Override
    public void showToastMessage(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideErrorLoadingDataView() {
        View errorView = findViewById(R.id.layout_error);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showMoviesRecyclerList() {
        moviesList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMoviesRecyclerList() {
        View errorView = findViewById(R.id.layout_error);
        errorView.setVisibility(View.GONE);
    }


    @Override
    public void onMovieClick(Movie movie) {
        startActivity(new Intent(this, MovieDetailsActivity.class).putExtra(MovieDetailsActivity.MOVIE_KEY, movie));
    }
}
