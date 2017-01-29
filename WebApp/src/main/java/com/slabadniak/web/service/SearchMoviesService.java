package com.slabadniak.web.service;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.content.MovieContent;

import java.util.List;


public class SearchMoviesService {

    public static MovieContent search(String movie) throws ServiceExeption {
        MovieContent content = MainContentService.movies();
        List<Movie> allMovies = content.get();

        //filter movies
        allMovies.removeIf(movie1 -> movie1.getTitle().length() < movie.length() || !movie1.getTitle().substring(0, movie.length()).toUpperCase().equals(movie.toUpperCase()));

        return content;
    }
}
