package com.example.surveyapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "survey_response")
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String dob;
    private String contacts;
    private String favoriteFoods;

    private int ratingEatOut;
    private int ratingWatchMovies;
    private int ratingWatchTV;
    private int ratingListenRadio;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }

    public String getFavoriteFoods() { return favoriteFoods; }
    public void setFavoriteFoods(String favoriteFoods) { this.favoriteFoods = favoriteFoods; }

    public int getRatingEatOut() { return ratingEatOut; }
    public void setRatingEatOut(int ratingEatOut) { this.ratingEatOut = ratingEatOut; }

    public int getRatingWatchMovies() { return ratingWatchMovies; }
    public void setRatingWatchMovies(int ratingWatchMovies) { this.ratingWatchMovies = ratingWatchMovies; }

    public int getRatingWatchTV() { return ratingWatchTV; }
    public void setRatingWatchTV(int ratingWatchTV) { this.ratingWatchTV = ratingWatchTV; }

    public int getRatingListenRadio() { return ratingListenRadio; }
    public void setRatingListenRadio(int ratingListenRadio) { this.ratingListenRadio = ratingListenRadio; }
}
