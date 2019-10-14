package com.osman.trending.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.osman.trending.R;
import com.osman.trending.helper.Constants;
import com.osman.trending.helper.Utils;
import com.osman.trending.model.Movie;
import com.osman.trending.model.NetworkState;
import com.osman.trending.repository.api.MovieClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final String TAG = "NetWorkLogging";

    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<ArrayList<Movie>> moviesList;

    private Context context;


    public Repository(Context context) {
        this.context = context;
        networkState = new MutableLiveData<>();
        moviesList = new MutableLiveData<>();
    }

    public void getMoviesList(String mediaType) {
        networkState.setValue(NetworkState.LOADING);
        if (Utils.isNetworkAvailable(context)) {
            MovieClient.getInstance().getService().getMovies(mediaType, Constants.DEFAULT_TIME_WINDOW, Constants.API_KEY)
                    .enqueue(new Callback<ArrayList<Movie>>() {
                        @Override
                        public void onResponse(@NonNull Call<ArrayList<Movie>> call, @NonNull Response<ArrayList<Movie>> response) {
                            final Gson gson = new Gson();
                            Log.wtf(TAG, "onResponse: raw: " + response.raw().toString() + " body: " + gson.toJson(response.body()));
                            Log.wtf(TAG, "onResponse: body: " + gson.toJson(response.body()));
                            Log.wtf(TAG, "onResponse: url: " + call.request().url().toString());
                            Log.wtf(TAG, "onResponse: response: " + gson.toJson(response));

                            Log.wtf(TAG, "onResponse: response isSuccessful ? = " + response.isSuccessful());
                            if (response.isSuccessful()) {// response.isSuccessful() is true if the response code is 2xx
                                Log.wtf(TAG, "onResponse: response.body() = " + response.body());

                                if (response.body() != null && response.body().size() > 0) {
                                    Log.wtf(TAG, "onResponse: response.body() != null && response.body().size() > 0");
                                    Log.wtf(TAG, "onResponse: List size = " + response.body().size());

                                    networkState.setValue(NetworkState.LOADED);
                                    moviesList.setValue(response.body());
                                } else {
                                    moviesList.setValue(null);
                                    networkState.setValue(new NetworkState(NetworkState.Status.FAILED, context.getString(R.string.empty_list)));
                                }
                            } else {
                                moviesList.setValue(null);
                                networkState.setValue(new NetworkState(NetworkState.Status.FAILED, context.getString(R.string.empty_list)));
                                Log.d(TAG, "onResponse: errorBody: " + gson.toJson(response.errorBody()));
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ArrayList<Movie>> call, @NonNull Throwable t) {
                            Log.d(TAG, "onFailure: Throwable: " + t);
                            Log.d(TAG, "onFailure: url: " + call.request().url().toString());

                            moviesList.setValue(null);
                            networkState.setValue(new NetworkState(NetworkState.Status.FAILED, context.getString(R.string.message_no_internet)));
                        }
                    });
        } else {
            moviesList.setValue(null);
            networkState.setValue(new NetworkState(NetworkState.Status.FAILED, context.getString(R.string.message_no_internet)));
        }
    }

    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public MutableLiveData<ArrayList<Movie>> getMovies() {
        return moviesList;
    }
}
