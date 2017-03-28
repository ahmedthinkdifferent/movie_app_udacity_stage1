package com.udacity.movieapp.data.helper;

import com.udacity.movieapp.data.model.MovieTrailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed agamy on 28/03/2017.
 */

public class Repository {



    public static List<MovieTrailer> getMovieTrailers(){
        List<MovieTrailer> movieTrailers = new ArrayList<>();
        movieTrailers.add(new MovieTrailer("trailer1","89Kq8SDyvfg"));
        movieTrailers.add(new MovieTrailer("trailer2","x8-7mHT9edg"));
        return movieTrailers;
    }
}
