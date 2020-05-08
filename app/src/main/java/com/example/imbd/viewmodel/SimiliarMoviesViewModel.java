package com.example.imbd.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.imbd.model.similar.ResultSimilar;
import com.example.imbd.model.similar.Similar;
import com.example.imbd.repository.SimilarMoviesRepository;

import java.util.List;

public class SimiliarMoviesViewModel extends ViewModel {

    private SimilarMoviesRepository similarMoviesRepository;

    public SimiliarMoviesViewModel() {
        similarMoviesRepository = new SimilarMoviesRepository();
    }

    public LiveData<List<ResultSimilar>> getAllSimilarMovies(){

        return similarMoviesRepository.getSimilarMutableliveData();
    }
}
