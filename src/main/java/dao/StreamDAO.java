package dao;

import classes.Stream;

import java.sql.SQLException;

public interface StreamDAO {
    public Stream getStreamById(Long id) throws SQLException;
}
