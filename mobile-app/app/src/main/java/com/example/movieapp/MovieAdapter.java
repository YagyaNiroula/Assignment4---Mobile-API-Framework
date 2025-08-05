package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;
    private OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
        void onEditClick(Movie movie);
        void onDeleteClick(Movie movie);
    }

    public MovieAdapter(Context context, List<Movie> movies, OnMovieClickListener listener) {
        this.context = context;
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        
        holder.titleText.setText(movie.getTitle());
        holder.yearText.setText(String.valueOf(movie.getYear()));
        holder.genreText.setText(movie.getGenre());
        holder.ratingText.setText(String.format("Rating: %.1f", movie.getCriticsRating()));
        holder.directorText.setText("Director: " + movie.getDirector());
        
        // Load movie poster using Glide
        if (movie.getPosterLink() != null && !movie.getPosterLink().isEmpty()) {
            Glide.with(context)
                    .load(movie.getPosterLink())
                    .placeholder(R.drawable.movie_placeholder)
                    .error(R.drawable.movie_placeholder)
                    .into(holder.posterImage);
        } else {
            holder.posterImage.setImageResource(R.drawable.movie_placeholder);
        }

        // Set click listeners
        holder.editButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(movie);
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(movie);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMovieClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateMovies(List<Movie> newMovies) {
        this.movies = newMovies;
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;
        TextView titleText, yearText, genreText, ratingText, directorText;
        TextView editButton, deleteButton;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.poster_image);
            titleText = itemView.findViewById(R.id.title_text);
            yearText = itemView.findViewById(R.id.year_text);
            genreText = itemView.findViewById(R.id.genre_text);
            ratingText = itemView.findViewById(R.id.rating_text);
            directorText = itemView.findViewById(R.id.director_text);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
} 