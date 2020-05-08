package com.example.imbd.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.imbd.R;
import com.example.imbd.adapter.MovieAdapter;
import com.example.imbd.model.movies.Result;
import com.example.imbd.viewmodel.MainActivityViewModel;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private PagedList<Result> moviesList;
    private MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        mainActivityViewModel.getMoviePagedLIst().observe(this, new Observer<PagedList<Result>>() {
         @Override
         public void onChanged(PagedList<Result> moviesLiveData) {
                moviesList = moviesLiveData;
                showRecyclerView();

         }
     });



    }

    public void showRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this);
        movieAdapter.submitList(moviesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }
}
