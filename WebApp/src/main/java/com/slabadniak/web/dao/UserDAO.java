package com.slabadniak.web.dao;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.entity.UsersAssessment;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represent all user's command.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class UserDAO extends AbstractDAO {
    private static final int MARK = 1;
    private static final int VALUE = 1;
    public static final String ASSESSANDCOMMENT = "REPLACE INTO assessment (movie_id, user_id, mark, comment) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? and movie.year = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?,?);";
    public static final String ONLYASSESS = "REPLACE INTO assessment (movie_id, user_id, mark) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? and movie.year = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?);";
    public static final String CHANGE_LOGIN = "UPDATE user set login = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_EMAIL = "UPDATE user set email = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_PASSWORD = "UPDATE user set password = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_GENDER = "UPDATE user set  gender = ? WHERE login = ? && email = ?;";
    public static final String CHANGE_ICON = "UPDATE user set icon = ?  WHERE login = ? && email = ?;";
    public static final String CHANGESTATUS = "UPDATE user set status_id = ?  WHERE login = ? && email = ?;";
    public static final String USERSRATE = "SELECT AVG(mark) FROM assessment WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? and movie.year = ? LIMIT 1);";
    public static final String ASSESSVALUE = "SELECT COUNT(mark) FROM assessment WHERE movie_id = (SELECT movie_id FROM movie WHERE title = ? and movie.year = ? LIMIT 1);";


    public UserDAO(Wrapper wrapper) {
        super(wrapper);
    }

    public void assess(UsersAssessment assessment) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            if (assessment.isMarkandText()) {
                ps = wrapper.prepareStatement(ASSESSANDCOMMENT);
                ps.setString(5, assessment.getComment());
            } else {
                ps = wrapper.prepareStatement(ONLYASSESS);
            }
            ps.setString(1, assessment.getMovie().getTitle());
            ps.setString(2, assessment.getMovie().getYear());
            ps.setString(3, assessment.getUser());
            ps.setFloat(4, assessment.getRating());
            ps.executeUpdate();
        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }


    public float usersRate(Movie movie) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        float usersRate = 0;
        try {
            ps = wrapper.prepareStatement(USERSRATE);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getYear());
            res = ps.executeQuery();
            if (res.next()) {
                usersRate = res.getFloat(MARK);
            }

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }

        return usersRate;
    }

    public void changeStatus(User user, String status) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = wrapper.prepareStatement(CHANGESTATUS);
            ps.setString(1, status);
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }

    public int numAssess(Movie movie) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        int numAssess = 0;

        try {
            ps = wrapper.prepareStatement(ASSESSVALUE);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getYear());
            res = ps.executeQuery();
            if (res.next()) {
                numAssess = res.getInt(VALUE);
            }

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }

        return numAssess;
    }

    public void changeIcon(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = wrapper.prepareStatement(CHANGE_ICON);
            ps.setString(1, modified.getIcon());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }
    public void changeLogin(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = wrapper.prepareStatement(CHANGE_LOGIN);
            ps.setString(1, modified.getLogin());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }
    public void changeEmail(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = wrapper.prepareStatement(CHANGE_EMAIL);
            ps.setString(1, modified.getEmail());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }
    public void changePassword(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = wrapper.prepareStatement(CHANGE_PASSWORD);
            ps.setString(1, modified.getPassword());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }

    public void changeGender(User unmodified, User modified) throws DAOException {
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = wrapper.prepareStatement(CHANGE_GENDER);
            ps.setString(1, modified.getGender());
            ps.setString(2, unmodified.getLogin());
            ps.setString(3, unmodified.getEmail());
            ps.executeUpdate();

        } catch (SQLException | WrapperException e) {
            throw new DAOException("SQL exception", e);
        }
    }
}
