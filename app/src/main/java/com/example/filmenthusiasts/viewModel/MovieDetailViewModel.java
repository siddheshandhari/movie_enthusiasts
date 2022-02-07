package com.example.filmenthusiasts.viewModel;

import com.example.filmenthusiasts.data.MovieDetailSource;
import com.example.filmenthusiasts.model.MovieDetails;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailViewModel extends ViewModel {

    private final MutableLiveData<List<MovieDetails>> mutableMovieDetails;
    private final MovieDetailSource movieDetailSource;

    public MovieDetailViewModel() {
        movieDetailSource = new MovieDetailSource();
        mutableMovieDetails = movieDetailSource.mutableMovieDetails;
    }

    public MutableLiveData<List<MovieDetails>> getMutableMovieDetails() {
        return mutableMovieDetails;
    }

    public void getMovieDetails(int[] rank) {
        movieDetailSource.getMovieDetails(rank);
    }

}
