package com.example.filmenthusiasts.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filmenthusiasts.R;
import com.example.filmenthusiasts.activities.movie_details.MovieDetailsActivity;
import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.model.MoviesByRankModel;
import com.example.filmenthusiasts.utils.Constants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllMovieAdapter extends RecyclerView.Adapter<AllMovieAdapter.AllMovieViewHolder> {
    List<MoviesByRankModel> moviesByRankModelList;
    List<MovieDetails> movieDetailsList;
    Context context;

    public AllMovieAdapter(Context context, List<MoviesByRankModel> moviesByRankModelList, List<MovieDetails> movieDetailsList) {
        this.context = context;
        this.moviesByRankModelList = moviesByRankModelList;
        this.movieDetailsList = movieDetailsList;
    }

    @NonNull
    @Override
    public AllMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_row, parent, false);
        return new AllMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMovieViewHolder holder, int position) {
        holder.textView.setText(moviesByRankModelList.get(position).Name);
        holder.textView.setOnClickListener(view -> {
            Intent i = new Intent(context, MovieDetailsActivity.class);
            i.putExtra(Constants.movieDetails, movieDetailsList.get(position));
            i.putExtra(Constants.rank, position + 1);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return moviesByRankModelList.size();
    }

    class AllMovieViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        TextView textView;

        public AllMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            textView = view.findViewById(R.id.movie_name_textview);
        }
    }
}
