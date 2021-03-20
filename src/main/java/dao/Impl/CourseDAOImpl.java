package dao.Impl;

import classes.*;
import dao.CourseDAO;
import classes.Course;
import util.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    public Collection<Course> getAllCourses() throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select c from Course c order by c.course_name");
        List<Course> courses = (List<Course>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return courses;
    }

    public Course getCourseById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Course course = session.get(Course.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return course;
    }

    public Collection<Course> getCoursesByTeacher(Teacher teacher) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select c from Course c where (c.teacher.teacher_id = " + teacher.getTeacher_id() + ") order by c.course_name";
        Query query = session.createQuery(query_string);
        List<Course> courses = (List<Course>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return courses;
    }

    public void deleteCourse(Course course) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(course);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateCourse(Course course) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(course);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void insertCourse(Course course) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}