package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    private Feedback feedback;
    private static final int MARK = 1;
    private static final int VALUE = 1;
    public static final String ASSESSANDCOMMENT = "REPLACE INTO assessment (movie_id, user_id, mark, comment) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?,?);";
    public static final String ONLYASSESS = "REPLACE INTO assessment (movie_id, user_id, mark) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?);";
    public static final String CHANGEUSER = "UPDATE user set login = ? , email = ?, password = ?, gender = ?, icon = ?  WHERE login = ? && email = ?;";
    public static final String CHANGESTATUS = "UPDATE user set status_id = ?  WHERE login = ? && email = ?;";
    public static final String USERSRATE = "SELECT AVG(mark) FROM assessment WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? LIMIT 1);";
    public static final String ASSESSVALUE = "SELECT COUNT(mark) FROM assessment WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? LIMIT 1);";


    public UserDAO(Wrapper wrapper) {
        super(wrapper);
        feedback = new Feedback();
    }

    public void assess(UsersAssessment assessment) {
        PreparedStatement ps = null;
        try {
            if (assessment.isMarkandText()) {
                ps = getConnection().prepareStatement(ASSESSANDCOMMENT);
                ps.setString(4, assessment.getComment());
            } else {
                ps = getConnection().prepareStatement(ONLYASSESS);
            }
            ps.setString(1, assessment.getMovie());
            ps.setString(2, assessment.getUser());
            ps.setFloat(3, assessment.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           /* if(ps != null) {
                ps.close();
            }*/

            closeConnection();
        }
    }


    public void changeProfile(User unmodified, User modified) {
        PreparedStatement ps = null;

        try {
            ps = getConnection().prepareStatement(CHANGEUSER);
            ps.setString(1, modified.getLogin());
            ps.setString(2, modified.getEmail());
            ps.setString(3, modified.getPassword());
            ps.setString(4, modified.getGender());
            ps.setString(5, modified.getIcon());
            ps.setString(6, unmodified.getLogin());
            ps.setString(7, unmodified.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           /* if(ps != null) {
                ps.close();
            }*/

            closeConnection();
        }

    }

    public float usersRate(String movie) {
        PreparedStatement ps = null;
        ResultSet res = null;
        float usersRate = 0;

        try {
            ps = getConnection().prepareStatement(USERSRATE);
            ps.setString(1, movie);
            res = ps.executeQuery();
            if(res.next()) {
                usersRate = res.getFloat(MARK);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           /* if(ps != null) {
                ps.close();
            }*/

            closeConnection();
        }

        return usersRate;
    }

    public void changeStatus(User user, String status) {
        PreparedStatement ps = null;

        try {
            ps = getConnection().prepareStatement(CHANGESTATUS);
            ps.setString(1, status);
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           /* if(ps != null) {
                ps.close();
            }*/

            closeConnection();
        }
    }

    public int numAssess(String title) {
        PreparedStatement ps = null;
        ResultSet res = null;
        int numAssess = 0;

        try {
            ps = getConnection().prepareStatement(ASSESSVALUE);
            ps.setString(1, title);
            res = ps.executeQuery();
            if(res.next()) {
                numAssess = res.getInt(VALUE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           /* if(ps != null) {
                ps.close();
            }*/

            closeConnection();
        }

        return numAssess;
    }
}
