package com.example.imbd.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.imbd.Service.MovieDataService;
import com.example.imbd.Service.RetrofitInstance;
import com.example.imbd.adapter.GlobalMovieId;
import com.example.imbd.model.similar.ResultSimilar;
import com.example.imbd.model.similar.Similar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimilarMoviesRepository {


    private List<ResultSimilar> similarMoviesList;

    private MutableLiveData<List<ResultSimilar>> mutableLiveDataSimilar = new MutableLiveData<>();

    public MutableLiveData<List<ResultSimilar>> getSimilarMutableliveData() {



        MovieDataService movieDataService = RetrofitInstance.getRetrofitInstance();


        Call<Similar> call = movieDataService.getAllSimilarMovies(GlobalMovieId.movieId);

        call.enqueue(new Callback<Similar>() {
            @Override
            public void onResponse(Call<Similar> call, Response<Similar> response) {
                Similar similarMovies = response.body();

                if (similarMovies != null && similarMovies.getResults() != null) {


                    similarMoviesList = similarMovies.getResults();

                    mutableLiveDataSimilar.setValue(similarMoviesList);


                }
            }

            @Override
            public void onFailure(Call<Similar> call, Throwable t) {

                Log.e("onFailed", "  ******" + t.getMessage() + "*******");


            }
        });

        return mutableLiveDataSimilar;

    }
}
