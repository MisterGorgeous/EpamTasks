package com.slabadniak.web.dao;

import com.slabadniak.web.entity.Actor;
import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Represent all admin's command.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class AdminDAO extends AbstractDAO {
    public static final String USERS = "select login,email,password,status_id,banned,gender,icon,admin from user WHERE admin = FALSE;";
    public static final String BANING = "UPDATE user set banned = ? WHERE login = ?;";
    public static final String CHANGESTATUS = "UPDATE user set status_id = ? WHERE login = ?;";
    public static final String ADDMOVIE = "REPLACE INTO movie (title, rating, icon, year, country, description ) VALUE (?,?,?,?,?,?);";
    public static final String CLEANGENRES = "delete FROM genre WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? LIMIT 1);";
    public static final String ADDGENRES = "REPLACE INTO genre(movie_id, genre_id) VALUE ((SELECT movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT genre_id FROM genre_kind WHERE name = ?));";
    public static final String MOVIEID = "SELECT movie_id FROM movie WHERE title = ? && year = ?;";
    public static final String ACTORID = "SELECT actor_id FROM actor WHERE f_name = ? && s_name = ? && birthday = ? && birth_place = ?;";
    public static final String ADDACTOR = "INSERT INTO actor(f_name, s_name,birthday,birth_place) VALUE (?,?,?,?);";
    public static final String ADDROLE = "INSERT INTO role(movie_id,actor_id,person,profession) VALUE (?,?,?,'actor');";


    public AdminDAO(Wrapper wrapper) {
        super(wrapper);
    }

    private int getActorId(Actor actor) throws DAOException {
       PreparedStatement ps;
       ResultSet res ;
        try {
            ps = wrapper.prepareStatement(ACTORID);
            ps.setString(1, actor.getFirstName());
            ps.setString(2, actor.getSeccondName());
            ps.setString(3, actor.getBirthday());
            ps.setString(4, actor.getBirthplace());
            res = ps.executeQuery();
            if (res.next()) {
                return res.getInt(1);
            }
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return 0;
    }

    public int movieId(String movie, String year) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        int movieId;
        try {
            ps = wrapper.prepareStatement(MOVIEID);
            ps.setString(1, movie);
            ps.setString(2, year);
            res = ps.executeQuery();
            if (res.next()) {
                movieId = res.getInt(1);
            } else {
                movieId = 0;
            }
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return movieId;
    }


    public void addActors(List<Actor> actors, int movieId) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        int actorId;
        try {
            wrapper.setAutoCommit(false);

            for (Actor actor : actors) {
                actorId = getActorId(actor);
                if (actorId == 0) {
                    ps = wrapper.prepareStatement(ADDACTOR);
                    ps.setString(1, actor.getFirstName());
                    ps.setString(2, actor.getSeccondName());
                    ps.setString(3, actor.getBirthday());
                    ps.setString(4, actor.getBirthplace());
                    ps.executeUpdate();
                    actorId = getActorId(actor);
                }

                ps = wrapper.prepareStatement(ADDROLE);
                ps.setInt(1, movieId);
                ps.setInt(2, actorId);
                ps.setString(3, actor.getRole());
                ps.executeUpdate();
            }

            wrapper.commit();
            wrapper.setAutoCommit(true);
        } catch (SQLException | WrapperException e) {
            try {
                wrapper.rollback();
                wrapper.setAutoCommit(true);
            } catch (WrapperException e1) {
                throw new DAOException("SQL exception", e);
            }
            throw new DAOException("SQL exception", e);
        }
    }


    public void addMovie(Movie movie, List<String> movieGenres) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            wrapper.setAutoCommit(false);

            ps = wrapper.prepareStatement(ADDMOVIE);
            ps.setString(1, movie.getTitle());
            ps.setFloat(2, movie.getRating());
            ps.setString(3, movie.getIcon());
            ps.setString(4, movie.getYear());
            ps.setString(5, movie.getCountry());
            ps.setString(6, movie.getDescription());
            ps.executeUpdate();
            ps = wrapper.prepareStatement(CLEANGENRES);
            ps.setString(1, movie.getTitle());
            ps.executeUpdate();
            ps = wrapper.prepareStatement(ADDGENRES);
            for (String genre : movieGenres) {
                ps.setString(1, movie.getTitle());
                ps.setString(2, genre);
                ps.executeUpdate();
            }

            wrapper.commit();
            wrapper.setAutoCommit(true);
        } catch (SQLException | WrapperException e) {
            try {
                wrapper.rollback();
                wrapper.setAutoCommit(true);
            } catch ( WrapperException e1) {
                throw new DAOException("SQL exception", e);
            }
            throw new DAOException("SQL exception", e);
        }

    }

    public void changeStatus(User user) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(CHANGESTATUS);
            ps.setString(1, user.getStatus());
            ps.setString(2, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }

    }


    public void userBaning(User user) throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(BANING);
            ps.setBoolean(1, user.isBanned());
            ps.setString(2, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }

    public ResultSet users() throws DAOException {
        PreparedStatement ps;
        ResultSet res ;
        try {
            ps = wrapper.prepareStatement(USERS);
            res = ps.executeQuery();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
        return res;
    }

}
