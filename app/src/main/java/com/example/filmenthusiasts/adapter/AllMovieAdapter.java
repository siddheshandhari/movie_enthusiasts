package com.example.filmenthusiasts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filmenthusiasts.R;
import com.example.filmenthusiasts.model.MoviesByRankModel;
import com.example.filmenthusiasts.ui.MovieDetailFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AllMovieAdapter extends RecyclerView.Adapter<AllMovieAdapter.AllMovieViewHolder> {
    List<MoviesByRankModel> moviesByRankModelList;
    Context context;
    AppCompatActivity activity;

    public AllMovieAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<MoviesByRankModel> moviesByRankModelList) {
        this.moviesByRankModelList = moviesByRankModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_row, parent, false);
        activity = (AppCompatActivity) view.getContext();
        return new AllMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMovieViewHolder holder, int position) {
        holder.textView.setText(moviesByRankModelList.get(position).Name);
        holder.textView.setOnClickListener(view -> {
            activity.getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.main_fragment, new MovieDetailFragment().newInstance(moviesByRankModelList.get(position).getId()))
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return (moviesByRankModelList == null) ? 0 : moviesByRankModelList.size();
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
