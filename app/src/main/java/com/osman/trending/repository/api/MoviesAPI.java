package com.osman.trending.repository.api;

import com.osman.trending.helper.Constants;
import com.osman.trending.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesAPI {

    @GET("{" + Constants.MEDIA_TYPE_REQUEST_PARAM + "}/{" + Constants.TIME_WINDOW_REQUEST_PARAM + "}")
    Call<ArrayList<Movie>> getMovies(@Path(Constants.MEDIA_TYPE_REQUEST_PARAM) String mediaType,
                                     @Path(Constants.TIME_WINDOW_REQUEST_PARAM) String timeWindow,
                                     @Query(Constants.API_KEY_REQUEST_PARAM) String apiKey);
}
