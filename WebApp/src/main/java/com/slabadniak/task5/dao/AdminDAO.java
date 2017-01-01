package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AdminDAO extends AbstractDAO {
    public  static final String USERS = "select login,email,status_id,banned from user WHERE admin = FALSE;";
    public  static final String BANING = "UPDATE user set banned = ? WHERE login = ?;";
    public  static final String CHANGESTATUS = "UPDATE user set status_id = ? WHERE login = ?;";
    public  static final String ADDMOVIE = "REPLACE INTO movie (title, rating, icon, year, country, description ) VALUE (?,?,?,?,?,?);";
    public  static final String CLEANGENRES = "delete FROM genre WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? LIMIT 1);";
    public  static final String ADDGENRES = "REPLACE INTO genre(movie_id, genre_id) VALUE ((SELECT movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT genre_id FROM genre_kind WHERE name = ?));";

    public AdminDAO(Wrapper wrapper) {
        super(wrapper);
    }


    public void addMovie(Movie movie, List<String> movieGenres) {
        PreparedStatement ps = null;
        try {
           /* ps = getConnection().prepareStatement(ADDMOVIE);
            ps.setString(1,movie.getTitle());
            ps.setFloat(2,movie.getRating());
            ps.setString(3,movie.getIcon());
            ps.setString(4,movie.getYear());
            ps.setString(5,movie.getCountry());
            ps.setString(6,movie.getDescription());
            ps.executeUpdate();*/
            ps = getConnection().prepareStatement(CLEANGENRES);
            ps.setString(1,movie.getTitle());
            ps.executeUpdate();
            ps = getConnection().prepareStatement(ADDGENRES);
            for(String genre :movieGenres){
                ps.setString(1,movie.getTitle());
                ps.setString(2,genre);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           /* if(ps != null) {
                ps.close();
            }*/
            closeStatement();
            closeConnection();
        }
    }

    public void changeStatus(User user){
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(CHANGESTATUS);
            ps.setString(1,user.getStatus());
            ps.setString(2,user.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           /* if(ps != null) {
                ps.close();
            }*/
            closeStatement();
            closeConnection();
        }

    }


    public void userBaning(User user){
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(BANING);
            ps.setBoolean(1,user.isBanned());
            ps.setString(2,user.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           /* if(ps != null) {
                ps.close();
            }*/
            closeStatement();
            closeConnection();
        }

    }

    public ResultSet users(){
        PreparedStatement ps = null;
        ResultSet res = null;

        try {
            ps = getConnection().prepareStatement(USERS);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           /* if(ps != null) {
                ps.close();
            }*/
            closeStatement();
            closeConnection();
        }
        return res;
    }

}
