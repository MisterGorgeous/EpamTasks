package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.MovieContent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DefaultDAO extends AbstractDAO {
    private static final String FILMS = "SELECT title,rating,icon,year,country,description FROM movie LIMIT 4;";
    private static final String SIGNIN = "INSERT INTO user (login,email,password) VALUE (?,?,?);";
    private static final String LOGIN = "SELECT * FROM user where login = ? && password = ?;";
    private static final String GENRES = "SELECT genre_kind.name FROM movie JOIN genre USING(movie_id) JOIN genre_kind USING(genre_id) WHERE movie.title = ?;";
    private static final String ACTORS = "SELECT f_name,s_name,birstday FROM movie JOIN role USING(movie_id) JOIN actor USING(actor_id) WHERE movie.title = ?;";
    private static final String COMMENTS = "SELECT comment,mark,user.login,update_time FROM assessment JOIN user on assessment.user_id = user.user_id where movie_id = (SELECT movie_id from movie WHERE title = ?) && comment IS NOT NULL && comment != ''  ORDER BY update_time DESC;";

    public DefaultDAO(Wrapper wrapper) {
        super(wrapper);
    }

    public ResultSet comments(String movie){
        PreparedStatement ps = null;
        ResultSet res = null;

        try {
            ps = getConnection().prepareStatement(COMMENTS);
            ps.setString(1,movie);
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

    public ResultSet actors(String movie){
        PreparedStatement ps = null;
        ResultSet res = null;

        try {
            ps = getConnection().prepareStatement(ACTORS);
            ps.setString(1,movie);
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

    public ResultSet genres(String movie){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(GENRES);
            ps.setString(1,movie);
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


    public MovieContent movies() {
        ResultSet films = null;
        try {
            films = getStatement().executeQuery(FILMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MovieContent content = new MovieContent();
        content.insert(films);
        closeStatement();
        closeConnection();
        return content;
    }

    public void signIn(User user){
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(SIGNIN);
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
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

    public ResultSet LogIn(User user){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = getConnection().prepareStatement(LOGIN);
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getPassword());
            res = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           /* if(ps != null) {
                ps.close();
            }*/
          //  closeStatement();
           closeConnection();
        }
        return res;

    }
}
