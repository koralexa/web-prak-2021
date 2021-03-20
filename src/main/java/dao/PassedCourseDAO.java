package dao;

import classes.PassedCourse;
import classes.Student;

import java.sql.SQLException;

public interface PassedCourseDAO {
    public PassedCourse getPassedCourseById(Long id) throws SQLException;

    public void deletePassedCourse(PassedCourse passed_course) throws SQLException;

    public void updatePassedCourse(PassedCourse passed_course) throws SQLException;

    public void insertPassedCourse(PassedCourse passed_course) throws SQLException;
}