package com.example.photogallery.api;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchItemTask {
    private static final String BASE_URL = "https://api.flickr.com/";
    private static Retrofit retrofit = null;
    private static FlickrFetch flickrFetch;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit;
        }
        return retrofit;
    }
}
