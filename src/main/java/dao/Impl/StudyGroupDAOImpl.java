package dao.Impl;

import classes.StudyGroup;
import dao.StudyGroupDAO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;

public class StudyGroupDAOImpl implements StudyGroupDAO {
    public StudyGroup getStudyGroupById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        StudyGroup group = session.get(StudyGroup.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return group;
    }
}
