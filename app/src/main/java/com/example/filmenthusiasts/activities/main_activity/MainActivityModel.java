package com.example.filmenthusiasts.activities.main_activity;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.filmenthusiasts.model.AllMoviesModel;
import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.model.MoviesByRankModel;
import com.example.filmenthusiasts.network.RestfulApiService;
import com.example.filmenthusiasts.network.RetrofitClientInstance;
import com.example.filmenthusiasts.utils.Constants;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainActivityModel {
    private String TAG = "MainActivityModel";
    MutableLiveData<Boolean> mutableSuccessful;
    MutableLiveData<List<MovieDetails>> mutableMovieDetails;
    MutableLiveData<List<MoviesByRankModel>> mutableMoviesByRankModel;

    public MainActivityModel() {
        mutableMoviesByRankModel = new MutableLiveData<>();
        mutableMovieDetails = new MutableLiveData<>();
        mutableSuccessful = new MutableLiveData<>();
    }

    public void getMoviesByRank() {
        RestfulApiService service = RetrofitClientInstance.getRetrofitInstance().create(RestfulApiService.class);
        Call<List<MoviesByRankModel>> call = service.getMoviesByRank(Constants.authToken, 1, 10);

        call.enqueue(new Callback<List<MoviesByRankModel>>() {
            @Override
            public void onResponse(Call<List<MoviesByRankModel>> call, Response<List<MoviesByRankModel>> response) {
                if (response.isSuccessful()) {
                    List<MoviesByRankModel> moviesByRankModels = response.body();
                    Log.d(TAG, "onResponse: " + moviesByRankModels.get(0).Name);
                    mutableMoviesByRankModel.setValue(moviesByRankModels);
                    getMovieDetails(moviesByRankModels);
                } else {
                    mutableSuccessful.setValue(false);
                    Log.d(TAG, "onResponse: Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<MoviesByRankModel>> call, Throwable t) {
                mutableSuccessful.setValue(false);
                Log.d(TAG, "onFailure: Error: " + t.getMessage());
            }
        });
    }

    public void getMovieDetails(List<MoviesByRankModel> moviesByRankModels) {
        int[] ids = new int[10];
        for (int i = 0; i < moviesByRankModels.size(); i++) {
            ids[i] = moviesByRankModels.get(i).getId();
        }
        RestfulApiService service = RetrofitClientInstance.getRetrofitInstance().create(RestfulApiService.class);
        Call<List<MovieDetails>> call = service.getMovieDetails(Constants.authToken, ids);

        call.enqueue(new Callback<List<MovieDetails>>() {
            @Override
            public void onResponse(Call<List<MovieDetails>> call, Response<List<MovieDetails>> response) {
                if (response.isSuccessful()) {
                    List<MovieDetails> movieDetailsList = response.body();
                    Log.d(TAG, "onResponse: " + moviesByRankModels.get(0).Name);
                    mutableMovieDetails.setValue(movieDetailsList);
                    mutableSuccessful.setValue(true);
                } else {
                    mutableSuccessful.setValue(false);
                    Log.d(TAG, "onResponse: Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<MovieDetails>> call, Throwable t) {
                mutableSuccessful.setValue(false);
                Log.d(TAG, "onFailure: Error: " + t.getMessage());
            }
        });
    }

    public void getAllMovies() {
        RestfulApiService service = RetrofitClientInstance.getRetrofitInstance().create(RestfulApiService.class);
        Call<List<AllMoviesModel>> call = service.getAllMovies(Constants.authToken);

        call.enqueue(new Callback<List<AllMoviesModel>>() {
            @Override
            public void onResponse(Call<List<AllMoviesModel>> call, Response<List<AllMoviesModel>> response) {
                if (response.isSuccessful()) {
                    List<AllMoviesModel> allMoviesModels = response.body();
                    Log.d(TAG, "onResponse: " + allMoviesModels.get(0).Name);
//                    showAllMoviesList(allMoviesModels);
                } else {
                    Log.d(TAG, "onResponse: Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<AllMoviesModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: Error: " + t.getMessage());
            }
        });
    }

}
