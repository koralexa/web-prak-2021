package util;

import classes.Classroom;
import classes.Course;
import classes.Lesson;
import classes.Listener;
import classes.PassedCourse;
import classes.Stream;
import classes.Student;
import classes.StudyGroup;
import classes.Teacher;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Classroom.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Lesson.class);
                configuration.addAnnotatedClass(Listener.class);
                configuration.addAnnotatedClass(PassedCourse.class);
                configuration.addAnnotatedClass(Stream.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(StudyGroup.class);
                configuration.addAnnotatedClass(Teacher.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return sessionFactory;
    }
}
