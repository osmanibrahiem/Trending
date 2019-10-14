package com.osman.trending.helper;

import com.osman.trending.model.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Constants {

    public static final String MOVIES_ARRAY_DATA_TAG = "results";
    public static final Type LIST_CLASS_TYPE = (new ArrayList<Movie>()).getClass();
    public static final String BASE_URL = "https://api.themoviedb.org/3/trending/";
    public static final String GLIDE_TIMEOUT = "com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout";

    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/";
    public static final String SMALL_IMAGE_URL = IMAGE_URL + "w300";
    public static final String BIG_IMAGE_URL = IMAGE_URL + "w500";
    public static final String ORIGINAL_IMAGE_URL = IMAGE_URL + "original";

    public static final String API_KEY_REQUEST_PARAM = "api_key";
    public static final String MEDIA_TYPE_REQUEST_PARAM = "media_type";
    public static final String TIME_WINDOW_REQUEST_PARAM = "time_window";

    public static final String API_KEY = "5954db931e9993ddb65982259ca72648";
    public static final String ALL_MEDIA = "all";
    public static final String MOVIES_MEDIA = "movie";
    public static final String TV_MEDIA = "tv";
    public static final String PERSON_MEDIA = "person";
    public static final String DEFAULT_TIME_WINDOW = "day";


    public static final String DATABASE_NAME = "Trending.db";
    public static final String TABLE_NAME = "movies";
    public static final int NUMBERS_OF_THREADS = 3;
}
