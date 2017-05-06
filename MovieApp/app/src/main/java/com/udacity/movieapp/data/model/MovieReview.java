package com.udacity.movieapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.udacity.movieapp.data.local.MovieDatabase;

/**
 * Created by ahmed agamy on 06/05/2017.
 */

@Table(database = MovieDatabase.class)
public class MovieReview extends BaseModel implements Parcelable {

    @SerializedName("id")
    @Expose
    @Column
    @PrimaryKey
    public String id;
    @SerializedName("author")
    @Expose
    @Column
    public String author;
    @SerializedName("content")
    @Expose
    @Column
    public String content;
    @SerializedName("url")
    @Expose
    @Column
    public String url;
    @ForeignKey(saveForeignKeyModel = true, onDelete = ForeignKeyAction.CASCADE)
    public Movie movie;

    public MovieReview() {
    }

    protected MovieReview(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
        movie = in.readParcelable(Movie.class.getClassLoader());
    }

    public static final Creator<MovieReview> CREATOR = new Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);
        dest.writeParcelable(movie, flags);
    }
}
