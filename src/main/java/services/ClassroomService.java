package services;

import classes.Classroom;

import java.sql.SQLException;
import java.util.Collection;

public interface ClassroomService {
    public Collection<Classroom> getAllClassrooms() throws SQLException;

    public Classroom getClassroomById(Long id) throws SQLException;

    public Collection<Classroom> getClassroomsByMinCapacity(Long min) throws SQLException;

    public Collection<Classroom> getClassroomsByMaxCapacity(Long max) throws SQLException;

    public void deleteClassroom(Classroom classroom) throws SQLException;

    public void updateClassroom(Classroom classroom) throws SQLException;

    public void insertClassroom(Classroom classroom) throws SQLException;
}
