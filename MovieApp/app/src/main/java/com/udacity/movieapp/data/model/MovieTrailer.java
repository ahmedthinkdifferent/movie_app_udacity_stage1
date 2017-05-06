package com.udacity.movieapp.data.model;

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
 * Created by ahmed agamy on 28/03/2017.
 */

@Table(database = MovieDatabase.class)
public class MovieTrailer extends BaseModel {

    @SerializedName("id")
    @Column
    @PrimaryKey
    public String id;
    @SerializedName("iso_639_1")
    @Expose
    @Column
    public String iso6391;
    @SerializedName("iso_3166_1")
    @Expose
    @Column
    public String iso31661;
    @SerializedName("key")
    @Expose
    @Column
    public String key;
    @SerializedName("name")
    @Expose
    @Column
    public String name;
    @SerializedName("site")
    @Expose
    @Column
    public String site;
    @SerializedName("size")
    @Expose
    @Column
    public Integer size;
    @SerializedName("type")
    @Expose
    @Column
    public String type;
    @ForeignKey(saveForeignKeyModel = true, onDelete = ForeignKeyAction.CASCADE)
    public Movie movie;
}
