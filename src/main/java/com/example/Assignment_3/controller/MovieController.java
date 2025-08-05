package com.example.Assignment_3.controller;

import com.example.Assignment_3.model.Movie;
import com.example.Assignment_3.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MovieController {
    private final MovieRepository movieRepository;
    
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    // GET - Retrieve all movies
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return ResponseEntity.ok(movies);
    }
    
    // GET - Retrieve a single movie by ID
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    // POST - Create a new movie
    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(savedMovie);
    }
    
    // PUT - Update an existing movie
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            movie.setTitle(movieDetails.getTitle());
            movie.setStudio(movieDetails.getStudio());
            movie.setGenre(movieDetails.getGenre());
            movie.setDirector(movieDetails.getDirector());
            movie.setWriters(movieDetails.getWriters());
            movie.setActors(movieDetails.getActors());
            movie.setYear(movieDetails.getYear());
            movie.setLength(movieDetails.getLength());
            movie.setShortDescription(movieDetails.getShortDescription());
            movie.setMpaRating(movieDetails.getMpaRating());
            movie.setPosterLink(movieDetails.getPosterLink());
            movie.setCriticsRating(movieDetails.getCriticsRating());
            
            Movie updatedMovie = movieRepository.save(movie);
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE - Delete a movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Legacy endpoint for backward compatibility
    @GetMapping("/top-20-movies")
    public ResponseEntity<List<Movie>> getTop20Movies() {
        List<Movie> topMovies = movieRepository.findAll();
        return ResponseEntity.ok(topMovies);
    }
}
