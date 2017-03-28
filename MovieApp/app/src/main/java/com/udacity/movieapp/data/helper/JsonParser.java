package com.udacity.movieapp.data.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.movieapp.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed agamy on 27/03/2017.
 */

public class JsonParser {


    public static List<Movie> getMovies(String jsonData) throws JSONException {
        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        JSONObject founderJson = new JSONObject(jsonData);
        JSONArray jsonArray = founderJson.getJSONArray("results");
        return gson.fromJson(jsonArray.toString().trim(), founderListType);

    }
}
