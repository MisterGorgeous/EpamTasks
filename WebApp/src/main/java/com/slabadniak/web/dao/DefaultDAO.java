package com.slabadniak.web.dao;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represent all guests's command.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class DefaultDAO extends AbstractDAO {
    private static final String MOVIES = "SELECT title,rating,icon,year,country,description FROM movie;";
    private static final String SPECIFICGENRE = "SELECT title,rating,icon,year,country,description FROM movie JOIN genre on movie.movie_id = genre.movie_id JOIN genre_kind on genre.genre_id = genre_kind.genre_id WHERE genre_kind.name = ?;";
    private static final String SIGNIN = "INSERT INTO user (login,email,password,gender,icon,banned,admin,status_id) VALUE (?,?,?,?,'/img/photo.png',FALSE,FALSE,'beginer');";
    private static final String LOGIN = "SELECT login,email,password,status_id,banned,gender,icon,admin FROM user where login = ? && password = ?;";
    private static final String GENRES = "SELECT genre_kind.name FROM movie JOIN genre USING(movie_id) JOIN genre_kind USING(genre_id) WHERE movie.title = ? and movie.year = ?;";
    private static final String ALLGENRES = "SELECT name FROM genre_kind;";
    private static final String CHECKLOGIN = "SELECT user_id FROM user WHERE login = ?;";
    private static final String CHECKEMAIL = "SELECT user_id FROM user WHERE email = ?;";
    private static final String CHECKPASSWORD = "SELECT user_id FROM user WHERE login = ? && password = ?;";
    private static final String COMMENTS = "SELECT comment,mark,user.login,update_time FROM assessment JOIN user on assessment.user_id = user.user_id where movie_id = (SELECT movie_id from movie WHERE title = ? and movie.year = ?) && comment IS NOT NULL && comment != ''  ORDER BY update_time DESC;";
    private static final String ACTORS = "SELECT f_name,s_name,birthday,birth_place,person,profession FROM movie JOIN role USING(movie_id) JOIN actor USING(actor_id) WHERE movie.title = ? and movie.year = ?;";
    private static final String SEARCHMOVIE = "SELECT title,rating,icon,year,country,description FROM movie WHERE title REGEXP CONCAT('^', ? ,'.*') ;";


    public DefaultDAO(Wrapper wrapper) {
        super(wrapper);
    }

    public ResultSet comments(Movie movie) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(COMMENTS);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getYear());
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return res;
    }

    public ResultSet actors(Movie movie) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(ACTORS);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getYear());
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return res;
    }

    public ResultSet genres(Movie movie) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(GENRES);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getYear());
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }

        return res;
    }


    public ResultSet movies() throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(MOVIES);
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return res;
    }

    public boolean checkUsersLogin(User user) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        boolean done;
        try {
            ps = wrapper.prepareStatement(CHECKLOGIN);
            ps.setString(1, user.getLogin());
            res = ps.executeQuery();
            done = res.next();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return done;
    }

    public boolean checkUserPassword(User user) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        boolean done;
        try {
            ps = wrapper.prepareStatement(CHECKPASSWORD);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            res = ps.executeQuery();
            done = res.next();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return done;
    }

    public boolean checkUsersEmail(User user) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        boolean done;
        try {
            ps = wrapper.prepareStatement(CHECKEMAIL);
            ps.setString(1, user.getEmail());
            res = ps.executeQuery();
            done = res.next();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return done;
    }


    public ResultSet LogIn(User user) throws DAOException {
        PreparedStatement ps ;
        ResultSet res;
        try {
            ps = wrapper.prepareStatement(LOGIN);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return res;

    }


    public void signIn(User user) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(SIGNIN);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getGender());
            ps.executeUpdate();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }

    }




    public ResultSet allGenres() throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(ALLGENRES);
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return res;
    }


    public ResultSet specificGenre(String genre) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(SPECIFICGENRE);
            ps.setString(1, genre);
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return res;

    }

    public ResultSet searchMovies(String movie) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(SEARCHMOVIE);
            ps.setString(1, movie);
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }

        return res;
    }
}
