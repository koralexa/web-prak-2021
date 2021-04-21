package dao.Impl;

import classes.Stream;
import dao.StreamDAO;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;

public class StreamDAOImpl implements StreamDAO {
    public Stream getStreamById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Stream stream = session.get(Stream.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return stream;
    }
}
