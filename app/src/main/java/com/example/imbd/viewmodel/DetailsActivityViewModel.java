package com.example.imbd.viewmodel;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.imbd.model.details.MovieResponse;
import com.example.imbd.model.video.ResultVideo;
import com.example.imbd.repository.DetailsMovieRepository;


public class DetailsActivityViewModel extends ViewModel {
   private DetailsMovieRepository detailsMovieRepository;


    public DetailsActivityViewModel() {
        detailsMovieRepository = new DetailsMovieRepository();
    }

    public LiveData<MovieResponse> getDetailsMovie(int idMovie){

        return detailsMovieRepository.getMutableLiveDataDetails();
    }

        public LiveData<ResultVideo> getMovieTrailer(int idMovie){

        return  detailsMovieRepository.getMutableLiveDataVideo();

        }


}
