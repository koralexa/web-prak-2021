package dao.Impl;

import classes.Stream;
import classes.StudyGroup;
import dao.StudentDAO;
import classes.Student;
import util.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    public Collection<Student> getAllStudents() throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select s from Student s order by s.group.stream.stream_number, s.group.group_number, s.full_name");
        List<Student> students = (List<Student>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return students;
    }

    public Student getStudentById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Student student = session.get(Student.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return student;
    }

    public Collection<Student> getStudentsByStudyYear(Long study_year) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select s from Student s where s.study_year = " + study_year + " order by s.group.stream.stream_number, s.group.group_number, s.full_name";
        Query query = session.createQuery(query_string);
        List<Student> students = (List<Student>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return students;
    }

    public Collection<Student> getStudentsByStudyGroup(StudyGroup group) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select s from Student s where s.group.group_number = " + group.getGroup_number() + " order by s.group.stream.stream_number, s.group.group_number, s.full_name";
        Query query = session.createQuery(query_string);
        List<Student> students = (List<Student>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return students;
    }

    public Collection<Student> getStudentsByStream(Stream stream) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select s from Student s where s.group.stream.stream_number = " + stream.getStream_number() + " order by s.group.stream.stream_number, s.group.group_number, s.full_name";
        Query query = session.createQuery(query_string);
        List<Student> students = (List<Student>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return students;
    }

    public void deleteStudent(Student student) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void insertStudent(Student student) throws SQLException {
        Session  session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
