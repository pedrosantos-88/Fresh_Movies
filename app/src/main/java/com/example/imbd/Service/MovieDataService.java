package com.example.imbd.Service;

import com.example.imbd.model.details.MovieResponse;
import com.example.imbd.model.movies.Movies;
import com.example.imbd.model.similar.Similar;
import com.example.imbd.model.video.ResultVideo;
import com.example.imbd.model.video.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDataService {



    String api_key = "5bbf68dcf3b4ad3875ef7b2ed5ddfe1a";

    @GET("3/movie/now_playing?api_key="+api_key+"&language=en-US&page=1")
    Call<Movies> getAllMovies();

    @GET("3/movie/{movie_id}}?api_key="+api_key+"&language=en-US")
        Call<MovieResponse> getMovieDetails(@Path("movie_id") int movieId) ;

    @GET("3/movie/{movie_id}}/similar?api_key="+api_key+"&language=en-US&page=1")
    Call<Similar> getAllSimilarMovies(@Path("movie_id") int movieId);

    @GET("https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key="+api_key+"&language=en-US")
    Call<ResultVideo> getVideoId(@Path("movie_id") int movieId);


    @GET("3/movie/now_playing?api_key="+api_key+"&language=en-US&page=")
    Call<Movies> getAllMoviesWithPaginig(@Query("page") long page);
}
