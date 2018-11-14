package com.example.android.az.moviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private List<Movie> mMovieList;
    final private ListItemClickListener mOnClickListener;
    private Context context;

    public MoviesAdapter(Context context, ListItemClickListener listener) {
        mOnClickListener = listener;
        this.context = context;
    }

    public void setmMovieList(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(Movie currentMovie);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterImage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.iv_movie_image);
            itemView.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         *
         * @param listIndex Position of the item in the list
         */


        void bind(int listIndex) {
            Movie currentMovie = (Movie) mMovieList.get(listIndex);
            // Build the image url
            String baseUrl = "http://image.tmdb.org/t/p/w185";
            String posterAddress = currentMovie.getmPosterImageThumbnail();
            String url = baseUrl + posterAddress;

            // Display the PosterImageThumbnail of the current movie in that ImageView
            Picasso.with(context).load(url).into(posterImage);
        }


        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick((Movie) mMovieList.get(getAdapterPosition()));
        }
    }
}
