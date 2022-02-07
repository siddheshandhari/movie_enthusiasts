package com.example.filmenthusiasts.data;

import android.util.Log;

import com.example.filmenthusiasts.model.AllMoviesModel;
import com.example.filmenthusiasts.model.MoviesByRankModel;
import com.example.filmenthusiasts.network.RestfulApiService;
import com.example.filmenthusiasts.network.RetrofitClientInstance;
import com.example.filmenthusiasts.utils.Constants;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieByRankSource {
    private final String TAG = "MovieByRankSource";
    public MutableLiveData<Boolean> mutableSuccessful;
    public MutableLiveData<List<MoviesByRankModel>> mutableMoviesByRankModel;

    public MovieByRankSource() {
        mutableMoviesByRankModel = new MutableLiveData<>();
        mutableSuccessful = new MutableLiveData<>();
    }

    public void getMoviesByRank() {
        RestfulApiService service = RetrofitClientInstance.getRetrofitInstance().create(RestfulApiService.class);
        Call<List<MoviesByRankModel>> call = service.getMoviesByRank(Constants.authToken, 1, 10);

        call.enqueue(new Callback<List<MoviesByRankModel>>() {
            @Override
            public void onResponse(Call<List<MoviesByRankModel>> call, Response<List<MoviesByRankModel>> response) {
                if (response.isSuccessful()) {
                    List<MoviesByRankModel> moviesByRankList = response.body();
                    Log.d(TAG, "onResponse: " + moviesByRankList.get(0).Name);
                    mutableMoviesByRankModel.setValue(moviesByRankList);
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
