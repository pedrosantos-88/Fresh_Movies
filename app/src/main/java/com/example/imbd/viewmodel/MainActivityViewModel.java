package com.example.imbd.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.imbd.Service.MovieDataService;
import com.example.imbd.Service.RetrofitInstance;
import com.example.imbd.model.MovieDataSource;
import com.example.imbd.model.MovieDataSourceFactory;
import com.example.imbd.model.movies.Movies;
import com.example.imbd.repository.MovieRespository;
import com.example.imbd.model.movies.Result;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends ViewModel {


    LiveData<MovieDataSource> movieDataSourceLiveData;

    private Executor executor;
    private LiveData<PagedList<Result>> moviePagedLIst;

    public MainActivityViewModel() {


        MovieDataService movieDataService = RetrofitInstance.getRetrofitInstance();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setMaxSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        moviePagedLIst = (new LivePagedListBuilder<Long, Result>(factory,config))
                .setFetchExecutor(executor).build();

    }

  /*  public LiveData<List<Result>> getAllmovies(){
        return movieRespository.getMutableLiveData();
    }*/

    public LiveData<PagedList<Result>> getMoviePagedLIst() { return moviePagedLIst; }




}
