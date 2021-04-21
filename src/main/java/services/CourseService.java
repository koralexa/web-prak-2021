package services;

import classes.Course;
import classes.Teacher;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseService {
    public Collection<Course> getAllCourses() throws SQLException;

    public Course getCourseById(Long id) throws SQLException;

    public Collection<Course> getCoursesByTeacher(Teacher teacher) throws SQLException;

    public void deleteCourse(Course course) throws SQLException;

    public void updateCourse(Course course) throws SQLException;

    public void insertCourse(Course course) throws SQLException;
}
