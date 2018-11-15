package com.example.android.az.moviesapp.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movie")
public class MovieEntry {
    @PrimaryKey
    private int mdId;
    private String mdOriginalTitle;
    private String mdReleaseDate;
    private String mdPosterImageThumbnail;
    private String mdAPlotSynopsis;
    private String mdUserRating;
    private int mdFav;

    public MovieEntry(int mdId, String mdOriginalTitle, String mdReleaseDate, String mdPosterImageThumbnail, String mdAPlotSynopsis, String mdUserRating, int mdFav) {
        this.mdId = mdId;
        this.mdOriginalTitle = mdOriginalTitle;
        this.mdReleaseDate = mdReleaseDate;
        this.mdPosterImageThumbnail = mdPosterImageThumbnail;
        this.mdAPlotSynopsis = mdAPlotSynopsis;
        this.mdUserRating = mdUserRating;
        this.mdFav = mdFav;
    }

    public int getMdId() {
        return mdId;
    }

    public void setMdId(int mdId) {
        this.mdId = mdId;
    }

    public String getMdOriginalTitle() {
        return mdOriginalTitle;
    }

    public void setMdOriginalTitle(String mdOriginalTitle) {
        this.mdOriginalTitle = mdOriginalTitle;
    }

    public String getMdReleaseDate() {
        return mdReleaseDate;
    }

    public void setMdReleaseDate(String mdReleaseDate) {
        this.mdReleaseDate = mdReleaseDate;
    }

    public String getMdPosterImageThumbnail() {
        return mdPosterImageThumbnail;
    }

    public void setMdPosterImageThumbnail(String mdPosterImageThumbnail) {
        this.mdPosterImageThumbnail = mdPosterImageThumbnail;
    }

    public String getMdAPlotSynopsis() {
        return mdAPlotSynopsis;
    }

    public void setMdAPlotSynopsis(String mdAPlotSynopsis) {
        this.mdAPlotSynopsis = mdAPlotSynopsis;
    }

    public String getMdUserRating() {
        return mdUserRating;
    }

    public void setMdUserRating(String mdUserRating) {
        this.mdUserRating = mdUserRating;
    }

    public int getMdFav() {
        return mdFav;
    }

    public void setMdFav(int mdFav) {
        this.mdFav = mdFav;
    }
}

