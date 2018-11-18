package com.example.android.az.moviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.az.moviesapp.database.MovieDetailTrailer;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailAdapterTrailer extends RecyclerView.Adapter<MovieDetailAdapterTrailer.MovieDetailViewHolder> {
    private static final String TAG = MovieDetailAdapterTrailer.class.getSimpleName();
    private List<MovieDetailTrailer> mMovieDetailTrailerList;
    final private ListItemClickListener mOnClickListener;
    private Context context;

    public MovieDetailAdapterTrailer(Context context, ListItemClickListener listener) {
        mOnClickListener = listener;
        this.context = context;
    }

    public void setmMovieDetailTrailerList(List<Object> mMovieDetailTrailerList) {
        List<MovieDetailTrailer> currentMovieDetailTrailer = new ArrayList<>();
        for (Object object : mMovieDetailTrailerList) {
            if (object instanceof MovieDetailTrailer) {
                currentMovieDetailTrailer.add((MovieDetailTrailer) object);
            }
        }
        this.mMovieDetailTrailerList = currentMovieDetailTrailer;
    }

    @NonNull
    @Override
    public MovieDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_trailer_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieDetailViewHolder viewHolder = new MovieDetailViewHolder(view);
        return viewHolder;
    }

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(MovieDetailTrailer currentMovieDetailTrailer);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (mMovieDetailTrailerList == null)
            return 0;
        return mMovieDetailTrailerList.size();
    }

    public class MovieDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        public MovieDetailViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_movie_detail_id);
            itemView.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         *
         * @param listIndex Position of the item in the list
         */


        void bind(int listIndex) {
            MovieDetailTrailer currentMovieDetailTrailer = (MovieDetailTrailer) mMovieDetailTrailerList.get(listIndex);
            name.setText("Trailer " + listIndex);

            // Build the image url
//            String baseUrl = "http://image.tmdb.org/t/p/w185";
//            String posterAddress = currentMovieDetailTrailer.getMPosterImageThumbnail();
//            String url = baseUrl + posterAddress;

            // Display the PosterImageThumbnail of the current movie in that ImageView
//            Picasso.with(context).load(url).into(posterImage);
        }


        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick((MovieDetailTrailer) mMovieDetailTrailerList.get(getAdapterPosition()));
        }
    }
}
