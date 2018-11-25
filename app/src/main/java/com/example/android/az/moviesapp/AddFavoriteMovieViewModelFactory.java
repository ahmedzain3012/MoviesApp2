package com.example.android.az.moviesapp;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.az.moviesapp.database.AppDatabase;

public class AddFavoriteMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private AppDatabase mDb;
    private final int mMovieId;

    public AddFavoriteMovieViewModelFactory(AppDatabase mDb, int mMovieId) {
        this.mDb = mDb;
        this.mMovieId = mMovieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddFavoriteMovieViewModel(mDb, mMovieId);
    }
}
