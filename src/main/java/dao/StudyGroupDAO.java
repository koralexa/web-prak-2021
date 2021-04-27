package dao;

import classes.Stream;
import classes.StudyGroup;

import java.sql.SQLException;
import java.util.Collection;

public interface StudyGroupDAO {
    public Collection<StudyGroup> getAllStudyGroups() throws SQLException;

    public StudyGroup getStudyGroupById(Long id) throws SQLException;
}
