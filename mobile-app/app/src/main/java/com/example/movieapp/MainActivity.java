package com.example.movieapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private FloatingActionButton fabAddMovie;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        fabAddMovie = findViewById(R.id.fab_add_movie);
        
        // Setup RecyclerView
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movieList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        // Setup FAB click listener
        fabAddMovie.setOnClickListener(v -> showAddMovieDialog());

        // Load movies from API
        loadMovies();
    }

    private void loadMovies() {
        progressBar.setVisibility(View.VISIBLE);
        
        // Debug log
        System.out.println("DEBUG: Starting to load movies...");
        
        ApiService apiService = ApiClient.getApiService();
        Call<List<Movie>> call = apiService.getAllMovies();
        
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                progressBar.setVisibility(View.GONE);
                
                // Debug logs
                System.out.println("DEBUG: API Response Code: " + response.code());
                System.out.println("DEBUG: API Response Body: " + response.body());
                
                if (response.isSuccessful() && response.body() != null) {
                    movieList.clear();
                    movieList.addAll(response.body());
                    movieAdapter.notifyDataSetChanged();
                    
                    System.out.println("DEBUG: Loaded " + movieList.size() + " movies");
                    
                    if (movieList.isEmpty()) {
                        Toast.makeText(MainActivity.this, "No movies found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Loaded " + movieList.size() + " movies", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    System.out.println("DEBUG: API Error - " + response.errorBody());
                    Toast.makeText(MainActivity.this, "Error loading movies: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("DEBUG: Network Error - " + t.getMessage());
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showAddMovieDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_movie_form, null);
        
        EditText titleEdit = dialogView.findViewById(R.id.edit_title);
        EditText studioEdit = dialogView.findViewById(R.id.edit_studio);
        EditText genreEdit = dialogView.findViewById(R.id.edit_genre);
        EditText directorEdit = dialogView.findViewById(R.id.edit_director);
        EditText yearEdit = dialogView.findViewById(R.id.edit_year);
        EditText ratingEdit = dialogView.findViewById(R.id.edit_rating);
        EditText posterEdit = dialogView.findViewById(R.id.edit_poster);
        EditText descriptionEdit = dialogView.findViewById(R.id.edit_description);

        builder.setView(dialogView)
                .setTitle("Add New Movie")
                .setPositiveButton("Add", (dialog, which) -> {
                    Movie newMovie = new Movie();
                    newMovie.setTitle(titleEdit.getText().toString());
                    newMovie.setStudio(studioEdit.getText().toString());
                    newMovie.setGenre(genreEdit.getText().toString());
                    newMovie.setDirector(directorEdit.getText().toString());
                    newMovie.setYear(Integer.parseInt(yearEdit.getText().toString()));
                    newMovie.setCriticsRating(Double.parseDouble(ratingEdit.getText().toString()));
                    newMovie.setPosterLink(posterEdit.getText().toString());
                    newMovie.setShortDescription(descriptionEdit.getText().toString());
                    
                    createMovie(newMovie);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void createMovie(Movie movie) {
        ApiService apiService = ApiClient.getApiService();
        Call<Movie> call = apiService.createMovie(movie);
        
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Movie added successfully", Toast.LENGTH_SHORT).show();
                    loadMovies(); // Refresh the list
                } else {
                    Toast.makeText(MainActivity.this, "Error adding movie", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMovieClick(Movie movie) {
        // Show movie details (optional)
        Toast.makeText(this, "Selected: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClick(Movie movie) {
        showEditMovieDialog(movie);
    }

    @Override
    public void onDeleteClick(Movie movie) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Movie")
                .setMessage("Are you sure you want to delete '" + movie.getTitle() + "'?")
                .setPositiveButton("Delete", (dialog, which) -> deleteMovie(movie.getId()))
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showEditMovieDialog(Movie movie) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_movie_form, null);
        
        EditText titleEdit = dialogView.findViewById(R.id.edit_title);
        EditText studioEdit = dialogView.findViewById(R.id.edit_studio);
        EditText genreEdit = dialogView.findViewById(R.id.edit_genre);
        EditText directorEdit = dialogView.findViewById(R.id.edit_director);
        EditText yearEdit = dialogView.findViewById(R.id.edit_year);
        EditText ratingEdit = dialogView.findViewById(R.id.edit_rating);
        EditText posterEdit = dialogView.findViewById(R.id.edit_poster);
        EditText descriptionEdit = dialogView.findViewById(R.id.edit_description);

        // Pre-fill the form with existing data
        titleEdit.setText(movie.getTitle());
        studioEdit.setText(movie.getStudio());
        genreEdit.setText(movie.getGenre());
        directorEdit.setText(movie.getDirector());
        yearEdit.setText(String.valueOf(movie.getYear()));
        ratingEdit.setText(String.valueOf(movie.getCriticsRating()));
        posterEdit.setText(movie.getPosterLink());
        descriptionEdit.setText(movie.getShortDescription());

        builder.setView(dialogView)
                .setTitle("Edit Movie")
                .setPositiveButton("Update", (dialog, which) -> {
                    movie.setTitle(titleEdit.getText().toString());
                    movie.setStudio(studioEdit.getText().toString());
                    movie.setGenre(genreEdit.getText().toString());
                    movie.setDirector(directorEdit.getText().toString());
                    movie.setYear(Integer.parseInt(yearEdit.getText().toString()));
                    movie.setCriticsRating(Double.parseDouble(ratingEdit.getText().toString()));
                    movie.setPosterLink(posterEdit.getText().toString());
                    movie.setShortDescription(descriptionEdit.getText().toString());
                    
                    updateMovie(movie);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void updateMovie(Movie movie) {
        ApiService apiService = ApiClient.getApiService();
        Call<Movie> call = apiService.updateMovie(movie.getId(), movie);
        
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Movie updated successfully", Toast.LENGTH_SHORT).show();
                    loadMovies(); // Refresh the list
                } else {
                    Toast.makeText(MainActivity.this, "Error updating movie", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteMovie(Long movieId) {
        ApiService apiService = ApiClient.getApiService();
        Call<Void> call = apiService.deleteMovie(movieId);
        
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Movie deleted successfully", Toast.LENGTH_SHORT).show();
                    loadMovies(); // Refresh the list
                } else {
                    Toast.makeText(MainActivity.this, "Error deleting movie", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
} 