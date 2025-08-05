package com.example.movieapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    // GET - Retrieve all movies
    @GET("api/movies")
    Call<List<Movie>> getAllMovies();
    
    // GET - Retrieve a single movie by ID
    @GET("api/movies/{id}")
    Call<Movie> getMovieById(@Path("id") Long id);
    
    // POST - Create a new movie
    @POST("api/movies")
    Call<Movie> createMovie(@Body Movie movie);
    
    // PUT - Update an existing movie
    @PUT("api/movies/{id}")
    Call<Movie> updateMovie(@Path("id") Long id, @Body Movie movie);
    
    // DELETE - Delete a movie
    @DELETE("api/movies/{id}")
    Call<Void> deleteMovie(@Path("id") Long id);
    
    // Legacy endpoint for backward compatibility
    @GET("api/top-20-movies")
    Call<List<Movie>> getTop20Movies();
} 