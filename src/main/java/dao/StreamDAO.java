package dao;

import classes.Stream;
import classes.Student;

import java.sql.SQLException;
import java.util.Collection;

public interface StreamDAO {
    public Collection<Stream> getAllStreams() throws SQLException;

    public Stream getStreamById(Long id) throws SQLException;
}
