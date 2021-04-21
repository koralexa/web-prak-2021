package services.Impl;

import classes.Course;
import classes.Teacher;
import dao.CourseDAO;
import dao.Impl.CourseDAOImpl;
import services.CourseService;

import java.sql.SQLException;
import java.util.Collection;

public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO = new CourseDAOImpl();

    @Override
    public Collection<Course> getAllCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    @Override
    public Course getCourseById(Long id) throws SQLException {
        return courseDAO.getCourseById(id);
    }

    @Override
    public Collection<Course> getCoursesByTeacher(Teacher teacher) throws SQLException {
        return courseDAO.getCoursesByTeacher(teacher);
    }

    @Override
    public void deleteCourse(Course course) throws SQLException {
        courseDAO.deleteCourse(course);
    }

    @Override
    public void updateCourse(Course course) throws SQLException {
        courseDAO.updateCourse(course);
    }

    @Override
    public void insertCourse(Course course) throws SQLException {
        courseDAO.insertCourse(course);
    }
}
