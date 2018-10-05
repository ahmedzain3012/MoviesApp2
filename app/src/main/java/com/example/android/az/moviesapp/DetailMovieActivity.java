package com.example.android.az.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        // Bind a reference to the {@link TextView and ImageView} in the layout
        ButterKnife.bind(this);

        //get the intent
        Intent intent = getIntent();
        if (intent.hasExtra("movieParcelable")) {
            Movie currentMovie = intent.getParcelableExtra("movieParcelable");
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

    }
}
