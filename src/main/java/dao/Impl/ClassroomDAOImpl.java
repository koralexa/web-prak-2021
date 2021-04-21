package dao.Impl;

import classes.Classroom;
import dao.ClassroomDAO;
import org.springframework.stereotype.Repository;
import util.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ClassroomDAOImpl implements ClassroomDAO {
    public Collection<Classroom> getAllClassrooms() throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select c from Classroom c order by c.classroom_number");
        List<Classroom> classrooms = (List<Classroom>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return classrooms;
    }

    public Classroom getClassroomById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Classroom classroom = session.get(Classroom.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return classroom;
    }

    public Collection<Classroom> getClassroomsByMinCapacity(Long min) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select c from Classroom c where c.capacity >= " + min + " order by c.classroom_number";
        Query query = session.createQuery(query_string);
        List<Classroom> classrooms = (List<Classroom>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return classrooms;
    }

    public Collection<Classroom> getClassroomsByMaxCapacity(Long max) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select c from Classroom c where c.capacity <= " + max + " order by c.classroom_number";
        Query query = session.createQuery(query_string);
        List<Classroom> classrooms = (List<Classroom>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return classrooms;
    }

    public void deleteClassroom(Classroom classroom) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(classroom);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateClassroom(Classroom classroom) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(classroom);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void insertClassroom(Classroom classroom) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(classroom);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}