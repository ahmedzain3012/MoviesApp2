package com.example.android.az.moviesapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>, MoviesAdapter.ListItemClickListener {
    private static final int NUM_LIST_COLUMNS = 2;

    private MoviesAdapter mAdapter;
    private RecyclerView mMovieList;
    /**
     * Constant value for the Movies loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    LoaderManager loaderManager;
    private static final int MOVIES_LOADER_ID = 1;
    /**
     * URL for movies data from the movies dataset
     */
    private static final String REQUEST_URL =
            "https://api.themoviedb.org/3/movie/";
    /**
     * REQUEST_SECTION URL for movies data from the movies dataset
     */
    String REQUEST_SECTION = "popular";
    /**
     * if no internet or no data
     */
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieList = findViewById(R.id.rv_movie);
        GridLayoutManager layoutManager = new GridLayoutManager(this, NUM_LIST_COLUMNS);
        mMovieList.setLayoutManager(layoutManager);
        mMovieList.setHasFixedSize(true);
        // Find a reference to the {@link TextView} in the layout
        emptyView = findViewById(R.id.tv_empty_view);
        emptyView.setVisibility(View.GONE);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        loaderManager = getLoaderManager();
        //initiate the loader by method
        executeLoader(REQUEST_SECTION);

    }

    /**
     * executeLoader method to initiate loader with diff url request section
     *
     * @param requestSection
     */
    void executeLoader(String requestSection) {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            //assign value for REQUEST_SECTION before initiate loader
            REQUEST_SECTION = requestSection;
            /**
             * if isRunning false mean first run then restartLoader
             * else mean we already has loader then initLoader
             */
            if (null != loaderManager.getLoader(MOVIES_LOADER_ID)) {
                loaderManager.restartLoader(MOVIES_LOADER_ID, null, this);

            } else {
                loaderManager.initLoader(MOVIES_LOADER_ID, null, this);
            }

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.pb_loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            //Update empty state with no connection error message
            emptyView.setText(R.string.no_internet_connection);
            emptyView.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        /**
         * create request url from REQUEST_URL + REQUEST_SECTION
         * then parse it as Uri
         * the using uri builder fro append format and api_key
         */
        String REQUEST_URL_SECTION = REQUEST_URL + REQUEST_SECTION;
        Uri baseUri = Uri.parse(REQUEST_URL_SECTION);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("api_key", BuildConfig.API_KEY);
        //return the MoviesLoader by url
        return new MoviesLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.pb_loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        if (data == null || data.isEmpty()) {
            // Set empty state text to display "No movies found."
            emptyView.setText(R.string.no_data);
            emptyView.setVisibility(View.VISIBLE);

        } else {
            mAdapter = new MoviesAdapter(this, data, this);
            mMovieList.setAdapter(mAdapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //create menu with my own menu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu option to execute loader with different url
        switch (item.getItemId()) {
            case R.id.action_most_popular:
                executeLoader(getString(R.string.most_popular_menu_item_val));
                return true;
            case R.id.action_highest_rated:
                executeLoader(getString(R.string.highest_rated_menu_item_val));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
