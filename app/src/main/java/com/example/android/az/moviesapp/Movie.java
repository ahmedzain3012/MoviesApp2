package com.example.android.az.moviesapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int    mId;
    private String mOriginalTitle;
    private String mReleaseDate;
    private String mPosterImageThumbnail;
    private String mAPlotSynopsis;
    private String mUserRating;

    public Movie(int mId,String mOriginalTitle, String mReleaseDate, String mPosterImageThumbnail, String mAPlotSynopsis, String mUserRating) {
        this.mId = mId;
        this.mOriginalTitle = mOriginalTitle;
        this.mReleaseDate = mReleaseDate;
        this.mPosterImageThumbnail = mPosterImageThumbnail;
        this.mAPlotSynopsis = mAPlotSynopsis;
        this.mUserRating = mUserRating;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        mId = in.readInt();
        mOriginalTitle = in.readString();
        mReleaseDate = in.readString();
        mPosterImageThumbnail = in.readString();
        mAPlotSynopsis = in.readString();
        mUserRating = in.readString();
    }

    public int getmId() {
        return mId;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmPosterImageThumbnail() {
        return mPosterImageThumbnail;
    }

    public String getmAPlotSynopsis() {
        return mAPlotSynopsis;
    }

    public String getmUserRating() {
        return mUserRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mOriginalTitle);
        dest.writeString(mReleaseDate);
        dest.writeString(mPosterImageThumbnail);
        dest.writeString(mAPlotSynopsis);
        dest.writeString(mUserRating);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mId='" + mId + '\'' +
                "mOriginalTitle='" + mOriginalTitle + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mPosterImageThumbnail='" + mPosterImageThumbnail + '\'' +
                ", mAPlotSynopsis='" + mAPlotSynopsis + '\'' +
                ", mUserRating='" + mUserRating + '\'' +
                '}';
    }
}
