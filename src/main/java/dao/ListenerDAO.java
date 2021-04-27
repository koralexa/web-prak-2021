package dao;

import classes.Course;
import classes.Listener;

import java.sql.SQLException;
import java.util.Collection;

public interface ListenerDAO {
    public Listener getListenerById(Long id) throws SQLException;

    public void deleteListener(Listener listener) throws SQLException;

    public void updateListener(Listener listener) throws SQLException;

    public void insertListener(Listener listener) throws SQLException;
}
