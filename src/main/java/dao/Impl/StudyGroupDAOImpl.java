package dao.Impl;

import classes.Stream;
import classes.StudyGroup;
import dao.StudyGroupDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class StudyGroupDAOImpl implements StudyGroupDAO {
    public Collection<StudyGroup> getAllStudyGroups() throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select s from StudyGroup s order by s.group_number");
        List<StudyGroup> study_groups = (List<StudyGroup>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return study_groups;
    }

    public StudyGroup getStudyGroupById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        StudyGroup group = session.get(StudyGroup.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return group;
    }
}
