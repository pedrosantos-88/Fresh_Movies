package com.example.imbd.repository;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.imbd.Service.MovieDataService;
import com.example.imbd.Service.RetrofitInstance;
import com.example.imbd.model.movies.Movies;
import com.example.imbd.model.movies.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRespository {

    private List<Result> moviesList ;
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();




    public MutableLiveData<List<Result>> getMutableLiveData(){

        MovieDataService movieDataService= RetrofitInstance.getRetrofitInstance();

        Call<Movies> call = movieDataService.getAllMovies();

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();
                    if (movies != null && movies.getResults() != null){

                            moviesList = movies.getResults();
                            mutableLiveData.setValue(moviesList);
                    }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return  mutableLiveData;
    }
}
