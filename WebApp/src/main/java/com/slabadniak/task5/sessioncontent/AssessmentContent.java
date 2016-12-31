package com.slabadniak.task5.sessioncontent;

import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.UsersAssessment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AssessmentContent implements DataContext {
    private List<UsersAssessment> assessments = null;
    private static final int COMMENT = 1;
    private static final int RATING = 2;
    private static final int LOGIN = 3;
    private static final int UPDATETIME = 4;

    public AssessmentContent(){
        assessments = new ArrayList<>();
    }

    @Override
    public Collection get() {
        return assessments;
    }

    @Override
    public void insert(ResultSet resultSet) {
        UsersAssessment assessment;


        try {

            if (resultSet.isBeforeFirst() ) {
                while (resultSet.next()) {
                    assessment = new UsersAssessment(resultSet.getString(COMMENT),
                            resultSet.getFloat(RATING),
                            "",
                            resultSet.getString(LOGIN),
                            resultSet.getTimestamp(UPDATETIME));
                    assessments.add(assessment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
