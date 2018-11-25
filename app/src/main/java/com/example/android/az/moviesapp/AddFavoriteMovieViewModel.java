package com.example.android.az.moviesapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.az.moviesapp.database.AppDatabase;
import com.example.android.az.moviesapp.database.Movie;

class AddFavoriteMovieViewModel extends ViewModel {
    private LiveData<Movie> movie;

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public AddFavoriteMovieViewModel(AppDatabase mDb, int mMovieId) {
        movie = mDb.movieDAO().loadMovieById(mMovieId);
    }
}
