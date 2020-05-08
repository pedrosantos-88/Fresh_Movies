package com.example.imbd.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.imbd.Service.MovieDataService;
import com.example.imbd.Service.RetrofitInstance;

import com.example.imbd.adapter.GlobalMovieId;
import com.example.imbd.model.details.MovieResponse;
import com.example.imbd.model.video.ResultVideo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsMovieRepository {

     private MutableLiveData<MovieResponse> mutableLiveDataDetails = new MutableLiveData<>();
     private MutableLiveData<ResultVideo> mutableLiveDataVideo = new MutableLiveData<>();



     public MutableLiveData<MovieResponse> getMutableLiveDataDetails(){

         final MovieDataService movieDataService = RetrofitInstance.getRetrofitInstance();

         Call<MovieResponse> call = movieDataService.getMovieDetails(GlobalMovieId.movieId);
         call.enqueue(new Callback<MovieResponse>() {
             @Override
             public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                 MovieResponse moviesResponse = response.body();

                 if (moviesResponse != null && moviesResponse.getTitle() != null) {
                     mutableLiveDataDetails.setValue(moviesResponse);

                 }
             }

             @Override
             public void onFailure(Call<MovieResponse> call, Throwable t) {
                 }
         });

         return mutableLiveDataDetails;
     }

     public LiveData<ResultVideo> getMutableLiveDataVideo(){

         final MovieDataService movieDataService = RetrofitInstance.getRetrofitInstance();

         Call<ResultVideo> call = movieDataService.getVideoId(GlobalMovieId.movieId);
         call.enqueue(new Callback<ResultVideo>() {
             @Override
             public void onResponse(Call<ResultVideo> call, Response<ResultVideo> response) {
                 ResultVideo resultVideo = response.body();

                 if (resultVideo != null && resultVideo.getKey() != null) {
                     mutableLiveDataVideo.setValue(resultVideo);
                 }
             }

             @Override
             public void onFailure(Call<ResultVideo> call, Throwable t) {

             }
         });

         return mutableLiveDataVideo;
     }



}
