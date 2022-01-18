package com.example.filmenthusiasts.activities.main_activity;

import android.os.Bundle;
import android.widget.Toast;
import com.example.filmenthusiasts.R;
import com.example.filmenthusiasts.adapter.AllMovieAdapter;
import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.model.MoviesByRankModel;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    RecyclerView recyclerView;
    AllMovieAdapter allMoviesAdapter;
    MainActivityViewModel mainActivityViewModel;
    private List<MoviesByRankModel> moviesByRankModels;
    private List<MovieDetails> movieDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getMutableMoviesByRankModel().observe(this, moviesByRankModels -> {
            this.moviesByRankModels = moviesByRankModels;
        });
        mainActivityViewModel.getMutableMovieDetails().observe(this, movieDetailsList -> {
            this.movieDetailsList = movieDetailsList;
        });
        mainActivityViewModel.getMutableSuccessful().observe(this, b -> {
            if(b)
                showMoviesByRankList();
            else
                Toast.makeText(this, "Please reopen the app", Toast.LENGTH_SHORT).show();
        });
    }

    public void showMoviesByRankList() {
        recyclerView = findViewById(R.id.main_activity_recyclerview);
        allMoviesAdapter = new AllMovieAdapter(this, moviesByRankModels, movieDetailsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(allMoviesAdapter);
    }
}