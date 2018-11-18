package com.example.android.az.moviesapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

class MovieDetailLoader extends AsyncTaskLoader<List<Object>> {
    private String mUrl;

    public MovieDetailLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
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
        List<Object> movieDetail = QueryUtils.fetchMoviesDataDetail(mUrl, getId());

        if (movieDetail == null) {
            return null;
        }
        return movieDetail;
    }

}
