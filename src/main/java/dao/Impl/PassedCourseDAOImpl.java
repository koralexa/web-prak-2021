package dao.Impl;
import classes.*;
import dao.*;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;

public class PassedCourseDAOImpl implements PassedCourseDAO {
    public PassedCourse getPassedCourseById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        PassedCourse passed_course = session.get(PassedCourse.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return passed_course;
    }

    public void deletePassedCourse(PassedCourse passed_course) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(passed_course);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updatePassedCourse(PassedCourse passed_course) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(passed_course);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void insertPassedCourse(PassedCourse passed_course) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(passed_course);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
