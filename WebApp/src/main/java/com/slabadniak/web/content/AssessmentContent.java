package com.slabadniak.web.content;

import com.slabadniak.web.entity.UsersAssessment;
import com.slabadniak.web.exeption.DAOException;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AssessmentContent implements DataContext{
    private List<UsersAssessment> assessments = null;
    private static final int COMMENT = 1;
    private static final int RATING = 2;
    private static final int LOGIN = 3;
    private static final int UPDATETIME = 4;

    public AssessmentContent() {
        assessments = new ArrayList<>();
    }

    @Override
    public Collection get() {
        return assessments;
    }

    @Override
    public void insert(ResultSet resultSet) throws DAOException {
        UsersAssessment assessment;
        try {
                while (resultSet.next()) {
                    assessment = new UsersAssessment(resultSet.getString(COMMENT),
                            resultSet.getFloat(RATING),
                            "",
                            resultSet.getString(LOGIN),
                            resultSet.getTimestamp(UPDATETIME));
                    assessments.add(assessment);
                }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }
}
