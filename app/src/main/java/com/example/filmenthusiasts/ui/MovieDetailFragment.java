package com.example.filmenthusiasts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmenthusiasts.R;
import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.viewModel.MovieDetailViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {

    private static final String POSITION = "param1";
    private int position;
    private MovieDetailViewModel movieDetailViewModel;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MovieDetailFragment.
     */
    public static MovieDetailFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        int[] rank = {position};
        movieDetailViewModel.getMovieDetails(rank);
        movieDetailViewModel.getMutableMovieDetails().observe(getViewLifecycleOwner(), movieDetailsList -> {
            MovieDetails movieDetails = movieDetailsList.get(0);

            TextView textViewName = view.findViewById(R.id.textview_name);
            TextView textViewRank = view.findViewById(R.id.textview_rank);
            TextView textViewDuration = view.findViewById(R.id.textview_duration);
            TextView textViewDescription = view.findViewById(R.id.textview_description);
            TextView textViewDirector = view.findViewById(R.id.textview_director);
            TextView textViewGenres = view.findViewById(R.id.textview_genres);
            TextView textViewActors = view.findViewById(R.id.textview_actors);
            ImageView imageviewPoster = view.findViewById(R.id.imageview_poster);
            Button buttonBook = view.findViewById(R.id.book_now_button);

            textViewName.setText(movieDetails.Name);
            textViewRank.setText(String.valueOf(rank));
            textViewDuration.setText(movieDetails.Duration);
            textViewDescription.setText(movieDetails.Description);
            textViewDirector.setText(movieDetails.Director);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < movieDetails.Genres.length; i++) {
                sb.append(movieDetails.Genres[i]);
                if (i != movieDetails.Genres.length - 1)
                    sb.append(", ");
            }
            textViewGenres.setText(String.valueOf(sb));
            sb = new StringBuilder();
            for (int i = 0; i < movieDetails.Actors.length; i++) {
                sb.append(movieDetails.Actors[i]);
                if (i != movieDetails.Actors.length - 1)
                    sb.append(", ");
            }
            textViewActors.setText(String.valueOf(sb));

            String url = "https://via.placeholder.com/300X200.png?text=" + rank;
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.error_300_200)
                    .error(R.drawable.error_300_200)
                    .into(imageviewPoster);
        });
    }
}