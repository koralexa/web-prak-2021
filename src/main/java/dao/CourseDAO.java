package dao;

import classes.Course;
import classes.Teacher;

import java.util.Collection;
import java.sql.SQLException;

public interface CourseDAO {
    public Collection<Course> getAllCourses() throws SQLException;

    public Course getCourseById(Long id) throws SQLException;

    public Collection<Course> getCoursesByTeacher(Teacher teacher) throws SQLException;

    public void deleteCourse(Course course) throws SQLException;

    public void updateCourse(Course course) throws SQLException;

    public void insertCourse(Course course) throws SQLException;
}