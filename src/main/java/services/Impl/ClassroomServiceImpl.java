package services.Impl;

import classes.Classroom;
import dao.Impl.ClassroomDAOImpl;
import dao.ClassroomDAO;
import services.ClassroomService;

import java.sql.SQLException;
import java.util.Collection;

public class ClassroomServiceImpl implements ClassroomService {
    private ClassroomDAO classroomDAO = new ClassroomDAOImpl();

    @Override
    public Collection<Classroom> getAllClassrooms() throws SQLException {
        return classroomDAO.getAllClassrooms();
    }

    @Override
    public Classroom getClassroomById(Long id) throws SQLException {
        return classroomDAO.getClassroomById(id);
    }

    @Override
    public Collection<Classroom> getClassroomsByMinCapacity(Long min) throws SQLException {
        return classroomDAO.getClassroomsByMinCapacity(min);
    }

    @Override
    public Collection<Classroom> getClassroomsByMaxCapacity(Long max) throws SQLException {
        return classroomDAO.getClassroomsByMaxCapacity(max);
    }

    @Override
    public void deleteClassroom(Classroom classroom) throws SQLException {
        classroomDAO.deleteClassroom(classroom);
    }

    @Override
    public void updateClassroom(Classroom classroom) throws SQLException {
        classroomDAO.updateClassroom(classroom);
    }

    @Override
    public void insertClassroom(Classroom classroom) throws SQLException {
        classroomDAO.insertClassroom(classroom);
    }
}
