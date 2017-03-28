package com.udacity.movieapp.data.networking;

import com.udacity.movieapp.data.listener.LoadMoviesListener;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ahmed agamy on 26/03/2017.
 */

public class ApiManager {

    private static final String API_URL = "http://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "";
    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    private interface RestApi {

        @GET("{type}/")
        Observable<ResponseBody> getMovies(@Path("type") String type, @Query("api_key") String key);
    }


    private static RestApi getApi() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(RestApi.class);
    }


    public static void loadMovies(String type, final LoadMoviesListener moviesListener) {
        getApi().getMovies(type, API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable error) {
                moviesListener.loadMoviesFail(error);
            }

            @Override
            public void onNext(ResponseBody body) {
                moviesListener.loadMoviesSuccess(body);
            }
        });
    }

}
