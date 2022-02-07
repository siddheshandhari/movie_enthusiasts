package com.example.filmenthusiasts.data;

import android.util.Log;

import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.network.RestfulApiService;
import com.example.filmenthusiasts.network.RetrofitClientInstance;
import com.example.filmenthusiasts.utils.Constants;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailSource {
    private final String TAG = "MovieDetailSource";

    public MutableLiveData<List<MovieDetails>> mutableMovieDetails;
    public MutableLiveData<Boolean> mutableSuccessful;

    public MovieDetailSource() {
        mutableMovieDetails = new MutableLiveData<>();
        mutableSuccessful = new MutableLiveData<>();
    }

    public void getMovieDetails(int[] ids) {
        RestfulApiService service = RetrofitClientInstance.getRetrofitInstance().create(RestfulApiService.class);
        Call<List<MovieDetails>> call = service.getMovieDetails(Constants.authToken, ids);

        call.enqueue(new Callback<List<MovieDetails>>() {
            @Override
            public void onResponse(Call<List<MovieDetails>> call, Response<List<MovieDetails>> response) {
                if (response.isSuccessful()) {
                    List<MovieDetails> movieDetailsList = response.body();
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
}
