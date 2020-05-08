package com.example.imbd.model;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.example.imbd.Service.MovieDataService;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private MovieDataService movieDataService;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        movieDataSource = new MovieDataSource(movieDataService);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
