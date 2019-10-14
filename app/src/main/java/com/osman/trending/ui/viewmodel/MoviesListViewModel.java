package com.osman.trending.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.osman.trending.model.Movie;
import com.osman.trending.model.NetworkState;
import com.osman.trending.repository.Repository;

import java.util.ArrayList;

public class MoviesListViewModel extends AndroidViewModel {

    private Repository repository;

    public MoviesListViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void loadMedia(String mediaType) {
        repository.getMoviesList(mediaType);
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return repository.getMovies();
    }

    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }

}
