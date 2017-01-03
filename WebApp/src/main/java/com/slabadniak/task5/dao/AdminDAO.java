package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.Actor;
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
    public  static final String MOVIEID = "SELECT movie_id FROM movie WHERE title = ? && year = ?;";
    public  static final String ACTORID = "SELECT actor_id FROM actor WHERE f_name = ? && s_name = ? && birthday = ? && birth_place = ?;";
    public  static final String ADDACTOR = "INSERT INTO actor(f_name, s_name,birthday,birth_place) VALUE (?,?,?,?);";
    public  static final String ADDROLE = "INSERT INTO role(movie_id,actor_id,person,profession) VALUE (?,?,?,'actor');";

    public AdminDAO(Wrapper wrapper) {
        super(wrapper);
    }

    private int getActorId(Actor actor) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(ACTORID);
        ps.setString(1, actor.getFirstName());
        ps.setString(2, actor.getSeccondName());
        ps.setString(3, actor.getBirthday());
        ps.setString(4, actor.getBirthplace());
        ResultSet res = ps.executeQuery();
        if(res.next()) {
            return res.getInt(1);
        }
        else{
            return 0;
        }
    }


    public void addActors(String movie,String year, List<Actor> actors) {
        PreparedStatement ps = null;
        ResultSet res = null;
        int actorId,movieId;

        try {
            ps = getConnection().prepareStatement(MOVIEID);
            ps.setString(1,movie);
            ps.setString(2,year);
            res = ps.executeQuery();
            if(res.next()) {
                movieId = res.getInt(1);

                for(Actor actor: actors) {
                 actorId =  getActorId(actor);
                    if(actorId == 0) {
                        ps = getConnection().prepareStatement(ADDACTOR);
                        ps.setString(1, actor.getFirstName());
                        ps.setString(2, actor.getSeccondName());
                        ps.setString(3, actor.getBirthday());
                        ps.setString(4, actor.getBirthplace());
                        ps.executeUpdate();
                        actorId = getActorId(actor);
                    }

                    ps = getConnection().prepareStatement(ADDROLE);
                        ps.setInt(1, movieId);
                        ps.setInt(2, actorId);
                        ps.setString(3, actor.getRole());
                        ps.executeUpdate();
                }


            }else{
                //no such movie
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

    public void addMovie(Movie movie, List<String> movieGenres) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(ADDMOVIE);
            ps.setString(1,movie.getTitle());
            ps.setFloat(2,movie.getRating());
            ps.setString(3,movie.getIcon());
            ps.setString(4,movie.getYear());
            ps.setString(5,movie.getCountry());
            ps.setString(6,movie.getDescription());
            ps.executeUpdate();
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
