package com.osman.trending.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.osman.trending.model.Movie;

public class MovieDetailsViewModel extends ViewModel {

    final private MutableLiveData movie;

    public MovieDetailsViewModel() {
        movie = new MutableLiveData<Movie>();
    }

    public MutableLiveData<Movie> getMovie() {
        return movie;
    }
}
