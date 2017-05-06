package com.udacity.movieapp.ui.screen.moviedetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.udacity.movieapp.R;
import com.udacity.movieapp.adapter.movieTrailers.MovieTrailerClickListener;
import com.udacity.movieapp.adapter.movieTrailers.MovieTrailersAdapter;
import com.udacity.movieapp.data.model.Movie;
import com.udacity.movieapp.data.model.MovieReview;
import com.udacity.movieapp.data.model.MovieTrailer;
import com.udacity.movieapp.data.model.Movie_Table;
import com.udacity.movieapp.data.networking.ApiManager;
import com.udacity.movieapp.ui.screen.moviereviews.MovieReviewsActivity;
import com.udacity.movieapp.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends AppCompatActivity implements MovieTrailerClickListener, MovieDetailsView {
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
    RecyclerView movieTrailers;
    //Object Members.
    Movie movie;
    @BindView(R.id.layout_movie_title)
    LinearLayout layoutMovieTitle;
    @BindView(R.id.btn_bookmark_movie)
    LikeButton btnBookmarkMovie;
    @BindView(R.id.tv_movie_reviews)
    TextView tvMovieReviews;
    private MovieDetailsPresenter presenter;
    private ProgressDialog progressDialog;
    //Variables.
    private boolean isMovieBookmarked;
    private List<MovieTrailer> trailers;
    private ArrayList<MovieReview> movieReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        initializeActionBar();
        // get movie from intent.
        movie = getIntent().getParcelableExtra(MOVIE_KEY);
        showMovieDateToViews(movie);
        presenter = new MovieDetailsPresenter();
        presenter.attachView(this);
        presenter.getMovieTrailers(movie.id);
    }


    private void initializeActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.movie_details);
    }


    private void showMovieDateToViews(Movie movie) {
        // touch listener in listView.
        // data.
        Glide.with(this).load(ApiManager.IMAGE_URL + movie.posterPath).into(movieImage);
        movieTitle.setText(movie.title);
        movieProductionDate.setText(movie.releaseDate);
        movieRate.setText(String.format("%s", movie.voteAverage));
        movieTimeLength.setText("210 Min");
        movieOverview.setText(movie.overview);
        isMovieBookmarked = SQLite.select().from(Movie.class).where(Movie_Table.id.eq(movie.id)).querySingle() != null;
        btnBookmarkMovie.setLiked(isMovieBookmarked);
        tvMovieReviews.setPaintFlags(tvMovieReviews.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.tv_movie_reviews)
    void movieReviews() {
        presenter.getMoviePreview(movie.id);
    }

    @OnClick(R.id.btn_bookmark_movie)
    void bookmarkMovieButtonClick() {
        if (isMovieBookmarked) {
            // remove movie from bookmarks.
            if (movie.delete()) isMovieBookmarked = false;
        } else {
            // add movie to bookmarks.
            if (movie.save()) {
                isMovieBookmarked = true;
            }
            for (MovieTrailer trailer : trailers) {
                trailer.save();
            }
        }
        btnBookmarkMovie.setLiked(isMovieBookmarked);
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
        Utility.openYoutubeVideo(this, movieTrailer.key);
    }

    @Override
    public void showMovieTrailers(List<MovieTrailer> trailers) {
        this.trailers = trailers;
        movieTrailers.setLayoutManager(new LinearLayoutManager(this));
        movieTrailers.setAdapter(new MovieTrailersAdapter(trailers, this));
    }

    @Override
    public void showMovieReviews(ArrayList<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
        startActivity(new Intent(this, MovieReviewsActivity.class).putParcelableArrayListExtra("movie_reviews", movieReviews));
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }
}
