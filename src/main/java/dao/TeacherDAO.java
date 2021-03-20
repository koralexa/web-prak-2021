package dao;

import classes.Teacher;

import java.util.Collection;
import java.sql.SQLException;

public interface TeacherDAO {
    public Collection<Teacher> getAllTeachers() throws SQLException;

    public Teacher getTeacherById(Long id) throws SQLException;

    public void deleteTeacher(Teacher teacher) throws SQLException;

    public void updateTeacher(Teacher teacher) throws SQLException;

    public void insertTeacher(Teacher teacher) throws SQLException;
}