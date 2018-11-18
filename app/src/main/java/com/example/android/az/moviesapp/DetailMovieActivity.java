package com.example.android.az.moviesapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.az.moviesapp.database.AppDatabase;
import com.example.android.az.moviesapp.database.Movie;
import com.example.android.az.moviesapp.database.MovieDetailTrailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.az.moviesapp.MainActivity.REQUEST_URL;

public class DetailMovieActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Object>>,
        MovieDetailAdapterTrailer.ListItemClickListener {
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

    private RecyclerView mMovieListTrailer;
    private MovieDetailAdapterTrailer mAdapterTrailer;

    private RecyclerView mMovieListReviewer;
    private MovieDetailAdapterTrailer mAdapterViewer;

    public static final int MOVIE_TRAILER_LOADER_ID = 2;
    public static final int MOVIE_REVIEWER_LOADER_ID = 3;

    /**
     * if no internet or no data
     */
    private TextView emptyViewTrailer;
    private TextView emptyViewReview;


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

        getLoaderManager().initLoader(MOVIE_TRAILER_LOADER_ID, null, this);
        getLoaderManager().initLoader(MOVIE_REVIEWER_LOADER_ID, null, this);


        mDb = AppDatabase.getsInstance(getApplicationContext());
        currentMovieDB = mDb.movieDAO().loadMovieById(currentMovie.getMId());

        if (currentMovieDB != null) {
            mAddFavoriteMovie.setText(R.string.remove_favorite_movie);
        }


        /**
         * Movie Trailer
         */
        mMovieListTrailer = findViewById(R.id.rv_movie_detail_trailer);
        LinearLayoutManager layoutManagerTrailer = new LinearLayoutManager(this);

        mMovieListTrailer.setLayoutManager(layoutManagerTrailer);
        mMovieListTrailer.setHasFixedSize(true);
        // Find a reference to the {@
        // link TextView} in the layout
        emptyViewTrailer = findViewById(R.id.tv_empty_view_trailer);
        emptyViewTrailer.setVisibility(View.GONE);

        /**
         * Movie Reviewer
         */
        mMovieListReviewer = findViewById(R.id.rv_movie_detail_reviewer);
        LinearLayoutManager layoutManagerReviewer = new LinearLayoutManager(this);

        mMovieListReviewer.setLayoutManager(layoutManagerReviewer);
        mMovieListReviewer.setHasFixedSize(true);
        // Find a reference to the {@
        // link TextView} in the layout
        emptyViewReview = findViewById(R.id.tv_empty_view_reviewer);
        emptyViewReview.setVisibility(View.GONE);


    }

    public void addFavoriteMovie(View view) {

        if (currentMovieDB == null) {
            currentMovie.setMFav(R.bool.add);
            mDb.movieDAO().insertMovie(currentMovie);
            currentMovieDB = mDb.movieDAO().loadMovieById(currentMovie.getMId());
            mAddFavoriteMovie.setText(R.string.remove_favorite_movie);
        } else {
            mDb.movieDAO().deleteMovie(currentMovie);
            currentMovieDB = mDb.movieDAO().loadMovieById(currentMovie.getMId());
            mAddFavoriteMovie.setText(getString(R.string.add_favorite_movie));
            currentMovie.setMFav(R.bool.remove);
        }
    }

    @NonNull
    @Override
    public Loader<List<Object>> onCreateLoader(int id, @Nullable Bundle args) {
        Uri baseUri;
        Uri.Builder uriBuilder;
        String REQUEST_URL_SECTION;
        Log.v("Moaaz2701 : ", String.valueOf(id));
        if (id == MOVIE_TRAILER_LOADER_ID) {
            REQUEST_URL_SECTION = REQUEST_URL + currentMovie.getMId() + "/videos";
        } else {
            REQUEST_URL_SECTION = REQUEST_URL + currentMovie.getMId() + "/reviews";
        }
        baseUri = Uri.parse(REQUEST_URL_SECTION);
        uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("api_key", BuildConfig.API_KEY);

        //return the MoviesLoader by url
        return new MovieDetailLoader(this, uriBuilder.toString());

    }


    @Override
    public void onLoadFinished(@NonNull Loader<List<Object>> loader, List<Object> data) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicatorTrailer = findViewById(R.id.pb_loading_indicator_trailer);
        loadingIndicatorTrailer.setVisibility(View.GONE);
        MovieDetailAdapterTrailer mAdapterTrailer = new MovieDetailAdapterTrailer(this, this);
        if (data == null || data.isEmpty()) {
            // Set empty state text to display "No movies found."
            emptyViewTrailer.setText(R.string.no_data);
            emptyViewTrailer.setVisibility(View.VISIBLE);

        } else {
            mAdapterTrailer.setmMovieDetailTrailerList(data);
        }

        // Hide loading indicator because the data has been loaded
        View loadingIndicatorReviewer = findViewById(R.id.pb_loading_indicator_reviewer);
        loadingIndicatorReviewer.setVisibility(View.GONE);
        MovieDetailAdapterReviewer mAdapterReviewer = new MovieDetailAdapterReviewer(this);
        if (data == null || data.isEmpty()) {
            // Set empty state text to display "No movies found."
            emptyViewReview.setText(R.string.no_data);
            emptyViewReview.setVisibility(View.VISIBLE);

        } else {
            mAdapterReviewer.setmMovieDetailReviewerList(data);
        }
        if (loader.getId() == MOVIE_TRAILER_LOADER_ID) {
            mMovieListTrailer.setAdapter(mAdapterTrailer);
        } else {
            mMovieListReviewer.setAdapter(mAdapterReviewer);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Object>> loader) {

    }

    @Override
    public void onListItemClick(MovieDetailTrailer currentMovieDetailTrailer) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + currentMovieDetailTrailer.getmKey()));
        startActivity(intent);

    }
}
