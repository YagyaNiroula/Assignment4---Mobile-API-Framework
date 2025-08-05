package com.example.Assignment_3;

import com.example.Assignment_3.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Assignment3ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMovieCreation() {
		Movie movie = new Movie();
		movie.setTitle("Test Movie");
		movie.setGenre("Action");
		movie.setYear(2023);
		movie.setDirector("Test Director");
		
		assertEquals("Test Movie", movie.getTitle());
		assertEquals("Action", movie.getGenre());
		assertEquals(2023, movie.getYear());
		assertEquals("Test Director", movie.getDirector());
	}

	@Test
	void testMovieConstructor() {
		Movie movie = new Movie("Test Movie", "Test Studio", "Action", "Test Director", 
			"Test Writers", "Test Actors", 2023, 120, "Test Description", "PG-13", 
			"http://example.com/poster.jpg", 8.5);
		
		assertEquals("Test Movie", movie.getTitle());
		assertEquals("Test Studio", movie.getStudio());
		assertEquals("Action", movie.getGenre());
		assertEquals("Test Director", movie.getDirector());
		assertEquals("Test Writers", movie.getWriters());
		assertEquals("Test Actors", movie.getActors());
		assertEquals(2023, movie.getYear());
		assertEquals(120, movie.getLength());
		assertEquals("Test Description", movie.getShortDescription());
		assertEquals("PG-13", movie.getMpaRating());
		assertEquals("http://example.com/poster.jpg", movie.getPosterLink());
		assertEquals(8.5, movie.getCriticsRating());
	}
}
