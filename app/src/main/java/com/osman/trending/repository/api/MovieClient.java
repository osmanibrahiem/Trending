package com.osman.trending.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osman.trending.helper.Constants;
import com.osman.trending.model.Movie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {

    private final Retrofit retrofit;
    private static MovieClient instance;
    private MoviesAPI service;

    private MovieClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Constants.LIST_CLASS_TYPE, new JsonConverter())
                .create();

        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    public synchronized static MovieClient getInstance() {
        if (instance == null) {
            instance = new MovieClient();
        }
        return instance;
    }

    public MoviesAPI getService() {
        if (service == null) {
            this.service = retrofit.create(MoviesAPI.class);
        }
        return service;
    }
}
