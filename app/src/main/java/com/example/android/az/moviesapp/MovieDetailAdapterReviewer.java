package com.example.android.az.moviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.az.moviesapp.database.MovieDetailReviewer;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailAdapterReviewer extends RecyclerView.Adapter<MovieDetailAdapterReviewer.MovieDetailViewHolder> {
    private static final String TAG = MovieDetailAdapterReviewer.class.getSimpleName();
    private List<MovieDetailReviewer> mMovieDetailReviewerList;
    private Context context;

    public MovieDetailAdapterReviewer(Context context) {
        this.context = context;
    }

    public void setmMovieDetailReviewerList(List<Object> mMovieDetailReviewerList) {
        List<MovieDetailReviewer> currentMovieDetailReviewer = new ArrayList<>();
        for (Object object : mMovieDetailReviewerList) {
            if (object instanceof MovieDetailReviewer) {
                currentMovieDetailReviewer.add((MovieDetailReviewer) object);
            }
        }
        this.mMovieDetailReviewerList = currentMovieDetailReviewer;
    }

    @NonNull
    @Override
    public MovieDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_reviewer_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieDetailViewHolder viewHolder = new MovieDetailViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MovieDetailViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (mMovieDetailReviewerList == null)
            return 0;
        return mMovieDetailReviewerList.size();
    }

    public class MovieDetailViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView content;

        public MovieDetailViewHolder(View itemView) {
            super(itemView);
            author =  itemView.findViewById(R.id.tv_author_id);

            content = itemView.findViewById(R.id.tv_content_id);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         *
         * @param listIndex Position of the item in the list
         */


        void bind(int listIndex) {
            MovieDetailReviewer currentMovieDetailReviewer = (MovieDetailReviewer) mMovieDetailReviewerList.get(listIndex);
            author.setText(currentMovieDetailReviewer.getmAuthor());
            content.setText(currentMovieDetailReviewer.getmContent());
            Log.v("Leen",currentMovieDetailReviewer.getmAuthor());

        }

    }
}
