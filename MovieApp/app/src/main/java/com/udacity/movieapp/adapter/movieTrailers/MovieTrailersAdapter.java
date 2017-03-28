package com.udacity.movieapp.adapter.movieTrailers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udacity.movieapp.R;
import com.udacity.movieapp.data.model.MovieTrailer;

import java.util.List;

/**
 * Created by ahmed agamy on 28/03/2017.
 */

public class MovieTrailersAdapter extends BaseAdapter implements View.OnClickListener {


    private final List<MovieTrailer> movieTrailers;
    private final MovieTrailerClickListener clickListener;

    public MovieTrailersAdapter(List<MovieTrailer> movieTrailers, MovieTrailerClickListener clickListener) {
        this.movieTrailers = movieTrailers;
        this.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return movieTrailers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieTrailerViewHolder trailerViewHolder;
        if (convertView == null) {
            // fin views.
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_movie_trailer, parent, false);
            trailerViewHolder = new MovieTrailerViewHolder(convertView);
            convertView.setTag(trailerViewHolder);
            trailerViewHolder.itemView.setOnClickListener(this);
        } else {
            // get views holder.
            trailerViewHolder = (MovieTrailerViewHolder) convertView.getTag();
        }
        //set data to views.
        MovieTrailer movieTrailer = movieTrailers.get(position);
        trailerViewHolder.movieTrailerName.setText(movieTrailer.title);
        trailerViewHolder.itemView.setTag(R.id.item_position, position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int itemPosition = (int) v.getTag(R.id.item_position);
        clickListener.onMovieTrailerClick(movieTrailers.get(itemPosition));
    }


    private class MovieTrailerViewHolder {
        View itemView;
        TextView movieTrailerName;

        MovieTrailerViewHolder(View itemView) {
            this.itemView = itemView;
            movieTrailerName = (TextView) itemView.findViewById(R.id.tv_movie_trailer);
        }
    }

}
