package com.example.android.az.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.az.moviesapp.database.AppDatabase;
import com.example.android.az.moviesapp.database.Movie;
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
    private Movie currentMovieDB;



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
            if (currentMovie.getMOriginalTitle() != null || !currentMovie.getMOriginalTitle().isEmpty()) {
                mOriginalTitle.setText(currentMovie.getMOriginalTitle());
            }
            if (currentMovie.getMPosterImageThumbnail() != null || !currentMovie.getMPosterImageThumbnail().isEmpty()) {
                String baseUrl = "http://image.tmdb.org/t/p/w185";
                String posterAddress = currentMovie.getMPosterImageThumbnail();
                String url = baseUrl + posterAddress;
                Picasso.with(this).
                        load(url).
                        into(mPosterImageThumbnail);
            }
            if (currentMovie.getMAPlotSynopsis() != null || !currentMovie.getMAPlotSynopsis().isEmpty()) {
                mAPlotSynopsis.setText(currentMovie.getMAPlotSynopsis());
            }
            if (currentMovie.getMUserRating() != null || !currentMovie.getMUserRating().isEmpty()) {
                mUserRating.setText(currentMovie.getMUserRating());
            }
            if (currentMovie.getMReleaseDate() != null || !currentMovie.getMReleaseDate().isEmpty()) {

                mReleaseDate.setText(currentMovie.getMReleaseDate());
            }
        }

        mDb = AppDatabase.getsInstance(getApplicationContext());
        currentMovieDB = mDb.movieDAO().loadMovieById(currentMovie.getMId());

        if (currentMovieDB != null)
            mAddFavoriteMovie.setText(R.string.remove_favorite_movie);

    }

    public void addFavoriteMovie(View view) {

        if (currentMovieDB == null){
            currentMovie.setMFav(R.bool.add);
            mDb.movieDAO().insertMovie(currentMovie);
            mAddFavoriteMovie.setText(R.string.remove_favorite_movie);
        } else {
            mDb.movieDAO().deleteMovie(currentMovie);
            mAddFavoriteMovie.setText(getString(R.string.add_favorite_movie));
            currentMovie.setMFav(R.bool.remove);
        }
    }
}
