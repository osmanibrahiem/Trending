package com.osman.trending.repository.api;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.osman.trending.helper.Constants;
import com.osman.trending.model.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;

class JsonConverter implements JsonDeserializer {

    private static String TAG = JsonConverter.class.getSimpleName();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Movie> moviesList = null;
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray moviesJsonArray = jsonObject.getAsJsonArray(Constants.MOVIES_ARRAY_DATA_TAG);
            moviesList = new ArrayList<>(moviesJsonArray.size());
            for (int i = 0; i < moviesJsonArray.size(); i++) {
                // adding the converted wrapper to our container
                Movie dematerialized = context.deserialize(moviesJsonArray.get(i), Movie.class);
                moviesList.add(dematerialized);
            }
        } catch (JsonParseException e) {
            Log.e(TAG, String.format("Could not convert Movie : %s", json.toString()));
        }
        return moviesList;
    }
}
