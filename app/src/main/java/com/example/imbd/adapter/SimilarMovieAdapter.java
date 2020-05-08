package com.example.imbd.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imbd.R;
import com.example.imbd.model.similar.ResultSimilar;
import com.example.imbd.view.DetailsActivity;

import java.util.List;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.SimilarViewHolder> {

    private List<ResultSimilar> similarMoviesList;
    private Context context;

    public SimilarMovieAdapter(List<ResultSimilar> similarMoviesList, Context context) {
        this.similarMoviesList = similarMoviesList;
        this.context = context;
    }

    @NonNull
    @Override
    public SimilarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.similar_movies, parent, false);

        return new SimilarViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull SimilarViewHolder holder, int position) {

        ResultSimilar currentData = similarMoviesList.get(position);

        holder.movieNameTv.setText(String.valueOf(currentData.getVoteAverage()));


        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + currentData.getPosterPath())
                .into(holder.moviePosterIV);

    }


    @Override
    public int getItemCount() {
        return similarMoviesList.size();
    }

    public class SimilarViewHolder extends RecyclerView.ViewHolder {


        private TextView movieNameTv;
        private ImageView moviePosterIV;


        private SimilarViewHolder(@NonNull View itemView) {
            super(itemView);

            movieNameTv = itemView.findViewById(R.id.movie_rating_similar);
            moviePosterIV = itemView.findViewById(R.id.movie_image_similar_iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    ResultSimilar selectedMovie = similarMoviesList.get(position);

                    Intent intent = new Intent(context, DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("movie_id", selectedMovie.getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }
}
