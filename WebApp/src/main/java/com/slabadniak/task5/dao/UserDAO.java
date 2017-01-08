package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    public static final String ASSESSANDCOMMENT = "REPLACE INTO assessment (movie_id, user_id, mark, comment) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?,?);";
    public static final String ONLYASSESS = "REPLACE INTO assessment (movie_id, user_id, mark) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?);";
    public static final String CHANGEUSER = "UPDATE user set login = ? , email = ?, password = ?, gender = ?  WHERE login = ? && email = ?;";


    public UserDAO(Wrapper wrapper) {
        super(wrapper);
    }

    public boolean assess(UsersAssessment assessment) {
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
            closeStatement();
            closeConnection();
        }

        // CONDITION
        return true;
    }


    public void changeProfile(User unmodified, User modified) {
        PreparedStatement ps = null;

        try {
            ps = getConnection().prepareStatement(CHANGEUSER);
            ps.setString(1,modified.getLogin() );
            ps.setString(2, modified.getEmail());
            ps.setString(3, modified.getPassword());
            ps.setString(4, modified.getGender());
            ps.setString(5, unmodified.getLogin());
            ps.setString(6, unmodified.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           /* if(ps != null) {
                ps.close();
            }*/
            closeStatement();
            closeConnection();
        }

    }
}
