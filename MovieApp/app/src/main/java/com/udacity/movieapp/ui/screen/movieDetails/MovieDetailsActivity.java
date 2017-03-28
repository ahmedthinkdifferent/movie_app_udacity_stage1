package com.udacity.movieapp.ui.screen.movieDetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.udacity.movieapp.R;
import com.udacity.movieapp.adapter.movieTrailers.MovieTrailerClickListener;
import com.udacity.movieapp.adapter.movieTrailers.MovieTrailersAdapter;
import com.udacity.movieapp.data.helper.Repository;
import com.udacity.movieapp.data.model.Movie;
import com.udacity.movieapp.data.model.MovieTrailer;
import com.udacity.movieapp.data.networking.ApiManager;
import com.udacity.movieapp.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements MovieTrailerClickListener {
    // Constants.
    public static final String MOVIE_KEY = "movie";
    //GUI References.
    @BindView(R.id.iv_movie_image)
    ImageView movieImage;
    @BindView(R.id.tv_movie_title)
    TextView movieTitle;
    @BindView(R.id.tv_movie_prod_year)
    TextView movieProductionDate;
    @BindView(R.id.tv_movie_time_length)
    TextView movieTimeLength;
    @BindView(R.id.tv_movie_rate)
    TextView movieRate;
    @BindView(R.id.tv_movie_description)
    TextView movieOverview;
    @BindView(R.id.lv_movie_trailers)
    ListView movieTrailers;
    //Object Members.
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        initializeActionBar();
        // get movie from intent.
        movie = getIntent().getParcelableExtra(MOVIE_KEY);
        showMovieDateToViews(movie);
    }


    private void initializeActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.movie_details);
    }


    private void showMovieDateToViews(Movie movie) {
        // touch listener in listView.
        movieTrailers.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        // data.
        Glide.with(this).load(ApiManager.IMAGE_URL + movie.posterPath).into(movieImage);
        movieTitle.setText(movie.title);
        movieProductionDate.setText(movie.releaseDate);
        movieRate.setText(movie.voteAverage + "");
        movieTimeLength.setText("210 Min");
        movieOverview.setText(movie.overview);
        movieTrailers.setAdapter(new MovieTrailersAdapter(Repository.getMovieTrailers(), this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieTrailerClick(MovieTrailer movieTrailer) {
        Utility.openYoutubeVideo(this, movieTrailer.id);
    }
}
