package dao.Impl;

import classes.Stream;
import classes.Student;
import dao.StreamDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class StreamDAOImpl implements StreamDAO {
    public Collection<Stream> getAllStreams() throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select s from Stream s order by s.stream_number");
        List<Stream> streams = (List<Stream>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return streams;
    }

    public Stream getStreamById(Long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Stream stream = session.get(Stream.class, id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return stream;
    }
}
