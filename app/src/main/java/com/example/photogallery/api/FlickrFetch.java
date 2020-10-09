package com.example.photogallery.api;

import com.model.Example;
import com.model.Photos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrFetch {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=189fdce9190bd83ac4ccecc3befa0f84&extras=url_s&format=json&nojsoncallback=1")
    Call<Example> getRecent();
    @GET("services/rest/?method=flickr.photos.search&api_key=189fdce9190bd83ac4ccecc3befa0f84I&extras=url_s&format=json&nojsoncallback=1")
    Call<Example> getSearchPhotos(@Query("text") String keyWord );
}

