package com.udacity.movieapp.adapter.movieTrailers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.movieapp.R;
import com.udacity.movieapp.data.model.MovieTrailer;

import java.util.List;

/**
 * Created by ahmed agamy on 28/03/2017.
 */

public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailersAdapter.MovieTrailerViewHolder> implements View.OnClickListener {


    private final List<MovieTrailer> movieTrailers;
    private final MovieTrailerClickListener clickListener;

    public MovieTrailersAdapter(List<MovieTrailer> movieTrailers, MovieTrailerClickListener clickListener) {
        this.movieTrailers = movieTrailers;
        this.clickListener = clickListener;
    }


    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_movie_trailer, parent, false);
        MovieTrailerViewHolder trailerViewHolder = new MovieTrailerViewHolder(convertView);
        convertView.setTag(trailerViewHolder);
        trailerViewHolder.itemView.setOnClickListener(this);
        return trailerViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        MovieTrailer movieTrailer = movieTrailers.get(position);
        holder.movieTrailerName.setText(movieTrailer.name);
        holder.itemView.setTag(R.id.item_position, position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return movieTrailers.size();
    }


    @Override
    public void onClick(View v) {
        int itemPosition = (int) v.getTag(R.id.item_position);
        clickListener.onMovieTrailerClick(movieTrailers.get(itemPosition));
    }


    class MovieTrailerViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView movieTrailerName;

        MovieTrailerViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            movieTrailerName = (TextView) itemView.findViewById(R.id.tv_movie_trailer);
        }
    }

}
