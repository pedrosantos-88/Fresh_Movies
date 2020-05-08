package com.example.imbd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.imbd.R;
import com.example.imbd.model.movies.Result;
import com.example.imbd.view.DetailsActivity;


public class MovieAdapter extends PagedListAdapter<Result,MovieAdapter.MovieViewHolder> {

    private Context context;

    public MovieAdapter(Context context){
        super(Result.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Result currentData = getItem(position);




        holder.movieNameTv.setText(currentData.getTitle());
        holder.movieRatingTv.setText(String.valueOf(currentData.getVoteAverage()));
        holder.movieDateTv.setText("Date: " + currentData.getReleaseDate());
        holder.movieVotesTv.setText("Votes: " + currentData.getVoteCount());
        holder.movieOriginalTitleTv.setText("Original: " + currentData.getOriginalTitle());
        holder.movieLanguageTv.setText("Language: " + currentData.getOriginalLanguage());



        int colorGreen = ContextCompat.getColor(context, R.color.colorGreen);
        int colorYellow = ContextCompat.getColor(context, R.color.colorYellow);
        int colorRed = ContextCompat.getColor(context, R.color.colorRed);


        if (currentData.getVoteAverage() >= 7.6){
            holder.movieRatingTv.setTextColor(colorGreen);
        }

        if (currentData.getVoteAverage() >= 5 && currentData.getVoteAverage() <= 7.6){
            holder.movieRatingTv.setTextColor(colorYellow);
        }

        if (currentData.getVoteAverage() <= 5){
            holder.movieRatingTv.setTextColor(colorRed);
        }

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + currentData.getPosterPath())
                .into(holder.moviePosterIV);



    }




    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private TextView movieNameTv;
        private TextView movieRatingTv;
        private TextView movieDateTv;
        private ImageView moviePosterIV;
        private TextView movieVotesTv;
        private TextView movieLanguageTv;
        private TextView movieOriginalTitleTv;

        private MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieNameTv = itemView.findViewById(R.id.name_movie_tv);
            movieRatingTv = itemView.findViewById(R.id.rating_movie_tv);
            moviePosterIV = itemView.findViewById(R.id.movie_poster_iv);
            movieDateTv = itemView.findViewById(R.id.date_movie_tv);
            movieVotesTv = itemView.findViewById(R.id.votes_movie_tv);
            movieOriginalTitleTv = itemView.findViewById(R.id.original_title_tv);
            movieLanguageTv = itemView.findViewById(R.id.language_movie_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    Result selectedMovie = getItem(position);

                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movie_id",selectedMovie.getId());

                    context.startActivity(intent);


                }});
        }
    }
}
