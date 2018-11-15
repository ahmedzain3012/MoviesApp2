package com.example.android.az.moviesapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MovieDAO {

    @Query("SELECT * FROM movie")
    List<MovieEntry> loadAllMovie();

    @Insert
    void insertMovie(MovieEntry movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(MovieEntry movie);

    @Delete
    void deleteMovie(MovieEntry movie);

}
