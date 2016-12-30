package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.GenreContent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    public  static final String ASSESSANDCOMMENT = "REPLACE INTO assessment (movie_id, user_id, mark, comment) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?,?);";
    public  static final String ONLYASSESS = "REPLACE INTO assessment (movie_id, user_id, mark) VALUE ((SELECT movie.movie_id FROM movie WHERE title = ? LIMIT 1),(SELECT user_id FROM user WHERE login = ? LIMIT 1),?);";

    public UserDAO(Wrapper wrapper){
        super(wrapper);
    }

    public boolean assess(UsersAssessment assessment){
        PreparedStatement ps = null;
        try {
            if(assessment.isComment()) {
                ps = getConnection().prepareStatement(ASSESSANDCOMMENT);
                ps.setString(4, assessment.getComment()  );
            }else {
                ps = getConnection().prepareStatement(ONLYASSESS);
            }
            ps.setString(1, assessment.getMovie());
            ps.setString(2, assessment.getUser());
            ps.setFloat(3, assessment.getRating());
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

        // CONDITION
        return true;
    }




}
