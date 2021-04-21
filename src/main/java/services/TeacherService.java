package services;

import classes.Teacher;

import java.sql.SQLException;
import java.util.Collection;

public interface TeacherService {
    public Collection<Teacher> getAllTeachers() throws SQLException;

    public Teacher getTeacherById(Long id) throws SQLException;

    public void deleteTeacher(Teacher teacher) throws SQLException;

    public void updateTeacher(Teacher teacher) throws SQLException;

    public void insertTeacher(Teacher teacher) throws SQLException;
}
