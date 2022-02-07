package com.example.filmenthusiasts.viewModel;

import android.app.Application;

import com.example.filmenthusiasts.data.MovieByRankSource;
import com.example.filmenthusiasts.model.MoviesByRankModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MovieListViewModel extends AndroidViewModel {

    private final MutableLiveData<List<MoviesByRankModel>> mutableMoviesByRankModel;
    private final MovieByRankSource movieByRankSource;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        movieByRankSource = new MovieByRankSource();
        mutableMoviesByRankModel = movieByRankSource.mutableMoviesByRankModel;
        getMoviesByRank();
    }

    private void getMoviesByRank() {
        movieByRankSource.getMoviesByRank();
    }

    public MutableLiveData<List<MoviesByRankModel>> getMutableMoviesByRankModel() {
        return mutableMoviesByRankModel;
    }
}
