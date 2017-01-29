package com.slabadniak.web.service;

import com.slabadniak.web.dao.DefaultDAO;
import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;
import com.slabadniak.web.content.MovieContent;

import java.util.Iterator;
import java.util.List;


public class SearchMoviesService {

    public static MovieContent search(String movie) throws ServiceExeption {
        MovieContent content = MainContentService.movies();

        //filter movies
        List<Movie> allMovies = content.get();
        allMovies.removeIf(nextMovie -> !nextMovie.getTitle().toUpperCase().equals(movie.toUpperCase()));

        return content;
    }
}
