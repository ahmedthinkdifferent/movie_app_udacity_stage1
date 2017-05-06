package com.udacity.movieapp.ui.screen.moviereviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.udacity.movieapp.R;
import com.udacity.movieapp.adapter.ReviewsAdapter;
import com.udacity.movieapp.data.model.MovieReview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewsActivity extends AppCompatActivity {

    @BindView(R.id.list_reviews)
    ListView listReviews;
    private List<MovieReview> movieReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_reviews);
        ButterKnife.bind(this);
        movieReviews = getIntent().getParcelableArrayListExtra("movie_reviews");
        initializeViews();
    }

    private void initializeViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.movie_reviews);
        listReviews.setAdapter(new ReviewsAdapter(movieReviews));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
