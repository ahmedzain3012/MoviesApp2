package com.example.android.az.moviesapp.database;

public class MovieDetailTrailer {
private String mKey;
private String mName;

    public MovieDetailTrailer(String mKey, String mName) {
        this.mKey = mKey;
        this.mName = mName;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
