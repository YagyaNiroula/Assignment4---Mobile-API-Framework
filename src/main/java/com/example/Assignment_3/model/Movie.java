package com.example.Assignment_3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String studio;
    private String genre;
    private String director;
    private String writers;
    private String actors;
    @Column(name = "`year`")
    private Integer year;
    private Integer length;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "mpa_rating")
    private String mpaRating;
    @Column(name = "poster_link")
    private String posterLink;
    @Column(name = "critics_rating")
    private Double criticsRating;
    public Movie() {}
    public Movie(String title, String studio, String genre, String director, String writers, String actors, Integer year, Integer length, String shortDescription, String mpaRating, String posterLink, Double criticsRating) {
        this.title = title;
        this.studio = studio;
        this.genre = genre;
        this.director = director;
        this.writers = writers;
        this.actors = actors;
        this.year = year;
        this.length = length;
        this.shortDescription = shortDescription;
        this.mpaRating = mpaRating;
        this.posterLink = posterLink;
        this.criticsRating = criticsRating;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getStudio() { return studio; }
    public void setStudio(String studio) { this.studio = studio; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getWriters() { return writers; }
    public void setWriters(String writers) { this.writers = writers; }
    public String getActors() { return actors; }
    public void setActors(String actors) { this.actors = actors; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getLength() { return length; }
    public void setLength(Integer length) { this.length = length; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public String getMpaRating() { return mpaRating; }
    public void setMpaRating(String mpaRating) { this.mpaRating = mpaRating; }
    public String getPosterLink() { return posterLink; }
    public void setPosterLink(String posterLink) { this.posterLink = posterLink; }
    public Double getCriticsRating() { return criticsRating; }
    public void setCriticsRating(Double criticsRating) { this.criticsRating = criticsRating; }
}
