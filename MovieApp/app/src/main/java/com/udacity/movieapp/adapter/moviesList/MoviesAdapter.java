package com.udacity.movieapp.adapter.moviesList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.udacity.movieapp.R;
import com.udacity.movieapp.data.model.Movie;
import com.udacity.movieapp.data.networking.ApiManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ahmed agamy on 27/03/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieItemHolder> {

    private MovieItemClickListener itemClickListener;
    private List<Movie> movies;

    public MoviesAdapter(MovieItemClickListener itemClickListener, List<Movie> movies) {
        this.itemClickListener = itemClickListener;
        this.movies = movies;
    }


    @Override
    public MovieItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie_item, parent, false);
        return new MovieItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieItemHolder holder, int position) {
        String movieImagePath = movies.get(holder.getAdapterPosition()).posterPath;
        Glide.with(holder.movieImageView.getContext()).load(ApiManager.IMAGE_URL + movieImagePath).into(holder.movieImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    class MovieItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie_image)
        ImageView movieImageView;

        MovieItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.iv_movie_image)
        void onMovieImageClick(){
            itemClickListener.onMovieClick(movies.get(getAdapterPosition()));
        }
    }
}
