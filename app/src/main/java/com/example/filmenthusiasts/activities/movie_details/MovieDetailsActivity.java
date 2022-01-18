package com.example.filmenthusiasts.activities.movie_details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmenthusiasts.R;
import com.example.filmenthusiasts.activities.website_webview.WebsiteActivity;
import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.utils.Constants;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {
    private final String TAG = "MovieDetailsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView textViewName = findViewById(R.id.textview_name);
        TextView textViewRank = findViewById(R.id.textview_rank);
        TextView textViewDuration = findViewById(R.id.textview_duration);
        TextView textViewDescription = findViewById(R.id.textview_description);
        TextView textViewDirector = findViewById(R.id.textview_director);
        TextView textViewGenres = findViewById(R.id.textview_genres);
        TextView textViewActors = findViewById(R.id.textview_actors);
        ImageView imageviewPoster = findViewById(R.id.imageview_poster);
        Button buttonBook = findViewById(R.id.book_now_button);

        MovieDetails movieDetails = (MovieDetails) getIntent().getSerializableExtra(Constants.movieDetails);
        int rank = getIntent().getIntExtra(Constants.rank, 0);

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

        buttonBook.setOnClickListener((view) -> {
            Intent i = new Intent(this, WebsiteActivity.class);
            startActivity(i);
        });
    }
}
