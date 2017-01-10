package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultDAO extends AbstractDAO {
    private static final String MOVIES = "SELECT title,rating,icon,year,country,description FROM movie;";
    private static final String SPECIFICGENRE = "SELECT title,rating,icon,year,country,description FROM movie JOIN genre on movie.movie_id = genre.movie_id JOIN genre_kind on genre.genre_id = genre_kind.genre_id WHERE genre_kind.name = ?;";
    private static final String SIGNIN = "INSERT INTO user (login,email,password,gender,icon,banned,admin,status_id) VALUE (?,?,?,?,'/img/photo.png',FALSE,FALSE,'beginer');";
    private static final String LOGIN = "SELECT login,email,status_id,banned,gender,icon,admin FROM user where login = ? && password = ?;";
    private static final String GENRES = "SELECT genre_kind.name FROM movie JOIN genre USING(movie_id) JOIN genre_kind USING(genre_id) WHERE movie.title = ?;";
    private static final String ALLGENRES = "SELECT name FROM genre_kind;";
    private static final String CHECKUSEREXIST = "SELECT user_id FROM user WHERE login = ? && email = ?;";
    private static final String ACTORS = "SELECT f_name,s_name,birthday,birth_place,person,profession FROM movie JOIN role USING(movie_id) JOIN actor USING(actor_id) WHERE movie.title = ?;";
    private static final String COMMENTS = "SELECT comment,mark,user.login,update_time FROM assessment JOIN user on assessment.user_id = user.user_id where movie_id = (SELECT movie_id from movie WHERE title = ?) && comment IS NOT NULL && comment != ''  ORDER BY update_time DESC;";
    private static final String SEARCHMOVIE = "SELECT title,rating,icon,year,country,description FROM movie WHERE title REGEXP CONCAT('^', ? ,'.*') ;";

    public DefaultDAO(Wrapper wrapper) {
        super(wrapper);
    }

    public ResultSet comments(String movie) {
        PreparedStatement ps = null;
        ResultSet res = null;

        try {
            System.out.println(movie);
            ps = getConnection().prepareStatement(COMMENTS);
            ps.setString(1, movie);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet actors(String movie) {
        PreparedStatement ps = null;
        ResultSet res = null;

        try {
            ps = getConnection().prepareStatement(ACTORS);
            ps.setString(1, movie);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet genres(String movie) {
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(GENRES);
            ps.setString(1, movie);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


    public ResultSet movies() {
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(MOVIES);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void signIn(User user) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(CHECKUSEREXIST);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ResultSet res = ps.executeQuery();
            if (!res.next()) {
                ps = getConnection().prepareStatement(SIGNIN);
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getGender());

                ps.executeUpdate();
            } else {
                //User exist
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet LogIn(User user) {
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(LOGIN);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            res = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;

    }


    public ResultSet allGenres() {
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(ALLGENRES);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public ResultSet specificGenre(String genre) {
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(SPECIFICGENRE);
            ps.setString(1, genre);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;

    }

    public ResultSet searchMovies(String movie) {
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(SEARCHMOVIE);
            ps.setString(1, movie);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;

    }
}
