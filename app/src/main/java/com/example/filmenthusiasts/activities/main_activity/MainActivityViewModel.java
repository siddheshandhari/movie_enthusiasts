package com.example.filmenthusiasts.activities.main_activity;

import android.app.Application;
import android.util.Log;

import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.model.MoviesByRankModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private final MutableLiveData<List<MoviesByRankModel>> mutableMoviesByRankModel;
    private final MutableLiveData<List<MovieDetails>> mutableMovieDetails;
    private final MutableLiveData<Boolean> mutableSuccessful;
    private final MainActivityModel mainActivityModel;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mainActivityModel = new MainActivityModel();
        mutableSuccessful = mainActivityModel.mutableSuccessful;
        mutableMoviesByRankModel = mainActivityModel.mutableMoviesByRankModel;
        mutableMovieDetails = mainActivityModel.mutableMovieDetails;
        getMoviesByRank();
    }

    public void getMoviesByRank() {
        mainActivityModel.getMoviesByRank();
    }

    public MutableLiveData<List<MoviesByRankModel>> getMutableMoviesByRankModel() {
        return mutableMoviesByRankModel;
    }

    public MutableLiveData<List<MovieDetails>> getMutableMovieDetails() {
        return mutableMovieDetails;
    }

    public MutableLiveData<Boolean> getMutableSuccessful() {
        return mutableSuccessful;
    }

    @Override
    protected void onCleared() {
        Log.i("MainActivityViewModel", "onCleared: ");
        super.onCleared();
    }
}
