package com.example.movieapp;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    private Long id;
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("studio")
    private String studio;
    
    @SerializedName("genre")
    private String genre;
    
    @SerializedName("director")
    private String director;
    
    @SerializedName("writers")
    private String writers;
    
    @SerializedName("actors")
    private String actors;
    
    @SerializedName("year")
    private Integer year;
    
    @SerializedName("length")
    private Integer length;
    
    @SerializedName("shortDescription")
    private String shortDescription;
    
    @SerializedName("mpaRating")
    private String mpaRating;
    
    @SerializedName("posterLink")
    private String posterLink;
    
    @SerializedName("criticsRating")
    private Double criticsRating;

    // Constructor
    public Movie() {}

    // Getters and Setters
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