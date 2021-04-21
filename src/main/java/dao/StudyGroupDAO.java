package dao;

import classes.StudyGroup;

import java.sql.SQLException;

public interface StudyGroupDAO {
    public StudyGroup getStudyGroupById(Long id) throws SQLException;
}
