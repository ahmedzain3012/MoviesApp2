package com.example.android.az.moviesapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "movie")
public class Movie implements Parcelable {
    @PrimaryKey
    private int    mId;
    private String mOriginalTitle;
    private String mReleaseDate;
    private String mPosterImageThumbnail;
    private String mAPlotSynopsis;
    private String mUserRating;
    private int mFav;

    public Movie(int mId,String mOriginalTitle, String mReleaseDate, String mPosterImageThumbnail, String mAPlotSynopsis, String mUserRating,int mFav) {
        this.mId = mId;
        this.mOriginalTitle = mOriginalTitle;
        this.mReleaseDate = mReleaseDate;
        this.mPosterImageThumbnail = mPosterImageThumbnail;
        this.mAPlotSynopsis = mAPlotSynopsis;
        this.mUserRating = mUserRating;
        this.mFav = mFav;
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
        mFav = in.readInt();
    }

    public int getMId() {
        return mId;
    }

    public String getMOriginalTitle() {
        return mOriginalTitle;
    }

    public String getMReleaseDate() {
        return mReleaseDate;
    }

    public String getMPosterImageThumbnail() {
        return mPosterImageThumbnail;
    }

    public String getMAPlotSynopsis() {
        return mAPlotSynopsis;
    }

    public String getMUserRating() {
        return mUserRating;
    }

    public int getMFav() {
        return mFav;
    }

    public void setMFav(int mFav) {
        this.mFav = mFav;
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
        dest.writeInt(mFav);
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
                ", mFav='" + mFav + '\'' +

                '}';
    }
}
