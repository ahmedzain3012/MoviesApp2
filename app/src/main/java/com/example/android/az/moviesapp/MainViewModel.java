package com.example.android.az.moviesapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.az.moviesapp.database.AppDatabase;
import com.example.android.az.moviesapp.database.Movie;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Movie>> movies;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getsInstance(this.getApplication());
        movies = database.movieDAO().loadAllMovie();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
