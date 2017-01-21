package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    private static final int MARK = 1;
    private static final int VALUE = 1;
    public static final String ASSESSANDCOMMENT = "REPLACE INTO assessment (movie_id, user_id, mark, comment) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?,?);";
    public static final String ONLYASSESS = "REPLACE INTO assessment (movie_id, user_id, mark) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?);";
    public static final String CHANGE_LOGIN = "UPDATE user set login = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_EMAIL = "UPDATE user set email = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_PASSWORD = "UPDATE user set password = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_GENDER = "UPDATE user set  gender = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_ICON = "UPDATE user set icon = ?  WHERE login = ? && email = ?;";
    public static final String CHANGESTATUS = "UPDATE user set status_id = ?  WHERE login = ? && email = ?;";
    public static final String USERSRATE = "SELECT AVG(mark) FROM assessment WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? LIMIT 1);";
    public static final String ASSESSVALUE = "SELECT COUNT(mark) FROM assessment WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? LIMIT 1);";


    public UserDAO(Wrapper wrapper) {
        super(wrapper);
    }

    public void assess(UsersAssessment assessment) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
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
            throw new DAOException("SQL exception", e);
        }
    }


    public float usersRate(String movie) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        float usersRate = 0;
        try {
            ps = getConnection().prepareStatement(USERSRATE);
            ps.setString(1, movie);
            res = ps.executeQuery();
            if (res.next()) {
                usersRate = res.getFloat(MARK);
            }

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }

        return usersRate;
    }

    public void changeStatus(User user, String status) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = getConnection().prepareStatement(CHANGESTATUS);
            ps.setString(1, status);
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }

    public int numAssess(String title) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        int numAssess = 0;

        try {
            ps = getConnection().prepareStatement(ASSESSVALUE);
            ps.setString(1, title);
            res = ps.executeQuery();
            if (res.next()) {
                numAssess = res.getInt(VALUE);
            }

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }

        return numAssess;
    }

    public void changeIcon(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = getConnection().prepareStatement(CHANGE_ICON);
            ps.setString(1, modified.getIcon());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }
    public void changeLogin(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = getConnection().prepareStatement(CHANGE_LOGIN);
            ps.setString(1, modified.getLogin());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }
    public void changeEmail(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = getConnection().prepareStatement(CHANGE_EMAIL);
            ps.setString(1, modified.getEmail());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }
    public void changePassword(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = getConnection().prepareStatement(CHANGE_PASSWORD);
            ps.setString(1, modified.getPassword());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }

    public void changeGender(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = getConnection().prepareStatement(CHANGE_GENDER);
            ps.setString(1, modified.getGender());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }
}
