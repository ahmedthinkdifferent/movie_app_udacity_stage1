
package com.udacity.movieapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.udacity.movieapp.data.local.MovieDatabase;


@Table(database = MovieDatabase.class)
public class Movie extends BaseModel implements Parcelable{

    @SerializedName("id")
    @Expose
    @Column
    @PrimaryKey
    public String id;
    @SerializedName("poster_path")
    @Expose
    @Column
    public String posterPath;
    @SerializedName("adult")
    @Expose
    @Column
    public boolean adult;
    @SerializedName("overview")
    @Expose
    @Column
    public String overview;
    @SerializedName("release_date")
    @Expose
    @Column
    public String releaseDate;
    @SerializedName("original_title")
    @Expose
    @Column
    public String originalTitle;
    @SerializedName("original_language")
    @Expose
    @Column
    public String originalLanguage;
    @SerializedName("title")
    @Expose
    @Column
    public String title;
    @SerializedName("backdrop_path")
    @Expose
    @Column
    public String backdropPath;
    @SerializedName("popularity")
    @Expose
    @Column
    public double popularity;
    @SerializedName("vote_count")
    @Expose
    @Column
    public int voteCount;
    @SerializedName("video")
    @Expose
    @Column
    public boolean video;
    @SerializedName("vote_average")
    @Expose
    @Column
    public double voteAverage;

    public Movie(){

    }

    protected Movie(Parcel in) {
        id = in.readString();
        posterPath = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        releaseDate = in.readString();
        originalTitle = in.readString();
        originalLanguage = in.readString();
        title = in.readString();
        backdropPath = in.readString();
        popularity = in.readDouble();
        voteCount = in.readInt();
        video = in.readByte() != 0;
        voteAverage = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(posterPath);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(originalTitle);
        dest.writeString(originalLanguage);
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeDouble(popularity);
        dest.writeInt(voteCount);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(voteAverage);
    }
}
