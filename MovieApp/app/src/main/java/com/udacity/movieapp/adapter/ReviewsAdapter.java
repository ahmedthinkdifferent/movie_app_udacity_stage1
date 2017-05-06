package com.udacity.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udacity.movieapp.R;
import com.udacity.movieapp.data.model.MovieReview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed agamy on 06/05/2017.
 */

public class ReviewsAdapter extends BaseAdapter {

    private List<MovieReview> reviews;

    public ReviewsAdapter(List<MovieReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public int getCount() {
        return reviews.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_review_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MovieReview movieReview = reviews.get(position);
        viewHolder.authorName.setText(movieReview.author);
        viewHolder.content.setText(movieReview.content);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.author_name)
        TextView authorName;
        @BindView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
