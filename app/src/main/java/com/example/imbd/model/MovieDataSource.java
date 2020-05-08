package com.example.imbd.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.imbd.Service.MovieDataService;
import com.example.imbd.Service.RetrofitInstance;
import com.example.imbd.model.movies.Movies;
import com.example.imbd.model.movies.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {

    private MovieDataService movieDataService;

    public MovieDataSource(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Result> callback) {

        movieDataService = RetrofitInstance.getRetrofitInstance();
        Call<Movies> call = movieDataService.getAllMoviesWithPaginig(1);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();
                List<Result> moviesList ;

                moviesList = movies.getResults();

                callback.onResult(moviesList,null,(long)2);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });



    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull  final  LoadCallback<Long, Result> callback) {


        movieDataService = RetrofitInstance.getRetrofitInstance();
        Call<Movies> call = movieDataService.getAllMoviesWithPaginig(params.key );

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();
                List<Result> moviesList ;
                if (movies != null && movies.getResults() != null){
                    moviesList = movies.getResults();

                    callback.onResult(moviesList,params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });


    }

}
