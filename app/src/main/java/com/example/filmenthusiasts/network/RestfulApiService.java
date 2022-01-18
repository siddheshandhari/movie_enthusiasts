package com.example.filmenthusiasts.network;

import com.example.filmenthusiasts.model.AllMoviesModel;
import com.example.filmenthusiasts.model.MovieDetails;
import com.example.filmenthusiasts.model.MoviesByRankModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestfulApiService {

    @GET("AllMovies/")
    Call<List<AllMoviesModel>> getAllMovies(@Query("authToken") String authToken);

    @GET("MoviesByRank/")
    Call<List<MoviesByRankModel>> getMoviesByRank(@Query("authToken") String authToken, @Query("startRankIndex") int startRankIndex
            , @Query("numMovies") int numMovies);

    @GET("MovieDetails/")
    Call<List<MovieDetails>> getMovieDetails(@Query("authToken") String authToken, @Query("movieIds") int[] movieIds);
}
