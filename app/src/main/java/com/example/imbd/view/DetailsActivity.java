package com.example.imbd.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.imbd.R;

import com.example.imbd.adapter.GlobalMovieId;
import com.example.imbd.adapter.SimilarMovieAdapter;
import com.example.imbd.model.details.MovieResponse;
import com.example.imbd.model.similar.ResultSimilar;
import com.example.imbd.model.video.ResultVideo;
import com.example.imbd.viewmodel.DetailsActivityViewModel;
import com.example.imbd.viewmodel.SimiliarMoviesViewModel;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.List;


public class DetailsActivity extends AppCompatActivity {

    private String imageConstruction = "https://image.tmdb.org/t/p/original";
    private DetailsActivityViewModel detailsActivityViewModel;
    private SimiliarMoviesViewModel similiarMoviesViewModel;
    private List<ResultSimilar> similarMoviesList;
    private SimilarMovieAdapter similarMovieAdapter;
    private RecyclerView recyclerView;
    private ImageView moviePosterIv;
    private Intent intent;
    private TextView movieRunTimeTv;
    private TextView movieNameTV;
    private TextView movieAboutTv;
    private static int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        moviePosterIv = findViewById(R.id.movie_poster__details_iv);
        movieNameTV = findViewById(R.id.movie_name_tv);
        movieAboutTv = findViewById(R.id.movie_about_tv);
        movieRunTimeTv = findViewById(R.id.movie_runtime_tv);

        detailsActivityViewModel = new ViewModelProvider(this).get(DetailsActivityViewModel.class);

        intent = getIntent();
        movieId = intent.getIntExtra("movie_id", 0);
        GlobalMovieId.movieId = movieId;

        getMovieDetails();
        getSimilarMovies();
        getMovieTrailer();


    }


    public void getMovieDetails() {

        detailsActivityViewModel.getDetailsMovie(movieId).observe(this, new Observer<MovieResponse>() {
            @Override
            public void onChanged(MovieResponse movieResponse) {
                movieNameTV.setText(movieResponse.getTitle());
                movieAboutTv.setText(movieResponse.getOverview());
                movieRunTimeTv.setText(movieResponse.getRuntime() + " min");

                Glide.with(getApplicationContext())
                        .load(imageConstruction + movieResponse.getPosterPath())
                        .into(moviePosterIv);
            }
        });
    }


    public void getSimilarMovies() {

        similiarMoviesViewModel = new ViewModelProvider(this).get(SimiliarMoviesViewModel.class);
        similiarMoviesViewModel.getAllSimilarMovies().observe(this, new Observer<List<ResultSimilar>>() {
            @Override
            public void onChanged(List<ResultSimilar> mutableResultSimilars) {
                similarMoviesList = mutableResultSimilars;

                showMoviesList();
            }
        });

    }

    public void  getMovieTrailer(){

        detailsActivityViewModel.getMovieTrailer(movieId).observe(this, new Observer<ResultVideo>() {
            @Override
            public void onChanged(ResultVideo resultVideo) {




            }
        });
    }

    public void showMoviesList() {

        recyclerView = findViewById(R.id.recyclerViewSimilar);
        similarMovieAdapter = new SimilarMovieAdapter(similarMoviesList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(similarMovieAdapter);
        similarMovieAdapter.notifyDataSetChanged();

    }


}
