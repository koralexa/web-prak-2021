package dao.Impl;
import dao.*;

import classes.Classroom;
import classes.Lesson;
import classes.Student;
import classes.Teacher;
import classes.Course;
import classes.Listener;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import util.HibernateSessionFactoryUtil;

import java.util.*;

import java.sql.SQLException;

public class LessonDAOImpl implements LessonDAO {
    public Collection<Lesson> getAllLessons() throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select l from Lesson l order by l.week_day, l.lesson_time");
        List<Lesson> lessons = (List<Lesson>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lessons;
    }

    public Lesson getLessonById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Lesson lesson = session.get(Lesson.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lesson;
    }

    public Collection<Lesson> getLessonsByTeacher(Teacher teacher) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select l from Lesson l where l.course.teacher.teacher_id = " + teacher.getTeacher_id() + " order by l.week_day, l.lesson_time";
        Query query = session.createQuery(query_string);
        List<Lesson> lessons = (List<Lesson>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lessons;
    }

    public Collection<Lesson> getLessonsByStudent(Student student) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Course> suitable_courses = new ArrayList<>();
        Collection<Course> courses = Factory.getInstance().getCourseDAO().getAllCourses();
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = (Course) iterator.next();
            if ((course.getStudy_year() != student.getStudy_year()) && (course.getStudy_year() != null)) {
                continue;
            }
            Set<Listener> listeners = course.getListeners();
            Iterator<Listener> iterator2 = listeners.iterator();
            while (iterator2.hasNext()) {
                Listener listener = (Listener)iterator2.next();
                if ((listener.getListener_type().equals("Поток")) &&
                    (listener.getListener_num().equals(student.getGroup().getStream().getStream_number()))) {
                    suitable_courses.add(course);
                    break;
                } else if ((listener.getListener_type().equals("Группа")) &&
                           (listener.getListener_num().equals(student.getGroup().getGroup_number()))) {
                    suitable_courses.add(course);
                    break;
                } else if ((listener.getListener_type().equals("Студент")) &&
                           (listener.getListener_num().equals(student.getStudent_id()))) {
                    suitable_courses.add(course);
                    break;
                }
            }
        }
        String query_string = "select l from Lesson l where l.course in (:suitable_courses) order by l.week_day, l.lesson_time";
        Query query = session.createQuery(query_string);
        query.setParameter("suitable_courses", suitable_courses);
        List<Lesson> lessons = (List<Lesson>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lessons;
    }

    public Collection<Lesson> getLessonsByClassroom(Classroom classroom) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select l from Lesson l where l.classroom.classroom_number = " + classroom.getClassroom_number() + " order by l.week_day, l.lesson_time";
        Query query = session.createQuery(query_string);
        List<Lesson> lessons = (List<Lesson>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lessons;
    }

    public Collection<Lesson> getLessonsByDay(Long day) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query_string = "select l from Lesson l where l.week_day = " + day + " order by l.week_day, l.lesson_time";
        Query query = session.createQuery(query_string);
        List<Lesson> lessons = (List<Lesson>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lessons;
    }

    public void deleteLesson(Lesson lesson) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(lesson);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateLesson(Lesson lesson) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(lesson);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void insertLesson(Lesson lesson) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(lesson);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
