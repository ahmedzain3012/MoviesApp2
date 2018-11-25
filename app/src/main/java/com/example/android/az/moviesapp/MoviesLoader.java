package com.example.android.az.moviesapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.az.moviesapp.database.Movie;

import java.util.List;

public class MoviesLoader extends AsyncTaskLoader<List<Movie>> {

    /** Tag for log messages */
    private static final String LOG_TAG = MoviesLoader.class.getName();

    /** Query URL */
    private String mUrl;
    List<Movie> movies;

    /**
     * Constructs a new {@link MoviesLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public MoviesLoader(Context context, String url) {
        super(context);
        mUrl = url;
        movies = null;
    }

    @Override
    protected void onStartLoading() {
        if (movies != null){
            deliverResult(movies);
        }
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Movie> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of movies.
        movies = QueryUtils.fetchMoviesData(mUrl);
        return movies;
    }
}
