package com.example.android.az.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.az.moviesapp.database.AppDatabase;
import com.example.android.az.moviesapp.database.MovieEntry;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {
    // Automatically finds each field by the specified ID.
    @BindView(R.id.tv_original_title)
    TextView mOriginalTitle;
    @BindView(R.id.iv_Poster_image_thumbnail)
    ImageView mPosterImageThumbnail;
    @BindView(R.id.tv_a_plot_synopsis)
    TextView mAPlotSynopsis;
    @BindView(R.id.tv_user_rating)
    TextView mUserRating;
    @BindView(R.id.tv_release_date)
    TextView mReleaseDate;
    @BindView(R.id.add_favorite_movie)
    Button mAddFavoriteMovie;

    private AppDatabase mDb;
    private Movie currentMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        // Bind a reference to the {@link TextView and ImageView} in the layout
        ButterKnife.bind(this);

        //get the intent
        Intent intent = getIntent();
        if (intent.hasExtra("movieParcelable")) {
            currentMovie = intent.getParcelableExtra("movieParcelable");
            if (currentMovie.getmOriginalTitle() != null || !currentMovie.getmOriginalTitle().isEmpty()) {
                mOriginalTitle.setText(currentMovie.getmOriginalTitle());
            }
            if (currentMovie.getmPosterImageThumbnail() != null || !currentMovie.getmPosterImageThumbnail().isEmpty()) {
                String baseUrl = "http://image.tmdb.org/t/p/w185";
                String posterAddress = currentMovie.getmPosterImageThumbnail();
                String url = baseUrl + posterAddress;
                Picasso.with(this).
                        load(url).
                        into(mPosterImageThumbnail);
            }
            if (currentMovie.getmAPlotSynopsis() != null || !currentMovie.getmAPlotSynopsis().isEmpty()) {
                mAPlotSynopsis.setText(currentMovie.getmAPlotSynopsis());
            }
            if (currentMovie.getmUserRating() != null || !currentMovie.getmUserRating().isEmpty()) {
                mUserRating.setText(currentMovie.getmUserRating());
            }
            if (currentMovie.getmReleaseDate() != null || !currentMovie.getmReleaseDate().isEmpty()) {

                mReleaseDate.setText(currentMovie.getmReleaseDate());
            }
        }
        mDb = AppDatabase.getsInstance(getApplicationContext());
        if (currentMovie.getmFav() != 0)
            mAddFavoriteMovie.setText(R.string.remove_favorite_movie);

    }

    public void addFavoriteMovie(View view) {
        MovieEntry currentMovieEntry = new MovieEntry(
                currentMovie.getmId(),
                currentMovie.getmOriginalTitle(),
                currentMovie.getmReleaseDate(),
                currentMovie.getmPosterImageThumbnail(),
                currentMovie.getmAPlotSynopsis(),
                currentMovie.getmUserRating(),
                1);
        if (currentMovie.getmFav() == 0) {
            mDb.movieDAO().insertMovie(currentMovieEntry);
            mAddFavoriteMovie.setText(R.string.remove_favorite_movie);
            currentMovie.setmFav(1);

        } else {
            mDb.movieDAO().deleteMovie(currentMovieEntry);
            mAddFavoriteMovie.setText(getString(R.string.add_favorite_movie));
            currentMovie.setmFav(0);


        }
    }
}
