package com.example.android.az.moviesapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

class MovieDetailLoader extends AsyncTaskLoader<List<Object>> {
    private String mUrl;
    List<Object> movieDetail;

    public MovieDetailLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
        movieDetail = null;
    }

    @Override
    protected void onStartLoading() {
        if (movieDetail != null){
            deliverResult(movieDetail);
        }
        forceLoad();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public List<Object> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        movieDetail = QueryUtils.fetchMoviesDataDetail(mUrl, getId());

        if (movieDetail == null) {
            return null;
        }
        return movieDetail;
    }

}
