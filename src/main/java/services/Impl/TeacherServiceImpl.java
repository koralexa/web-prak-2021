package services.Impl;

import classes.Teacher;
import dao.Impl.TeacherDAOImpl;
import dao.TeacherDAO;
import services.TeacherService;

import java.sql.SQLException;
import java.util.Collection;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDAO teacherDAO = new TeacherDAOImpl();

    @Override
    public Collection<Teacher> getAllTeachers() throws SQLException {
        return teacherDAO.getAllTeachers();
    }

    @Override
    public Teacher getTeacherById(Long id) throws SQLException {
        return teacherDAO.getTeacherById(id);
    }

    @Override
    public void deleteTeacher(Teacher teacher) throws SQLException {
        teacherDAO.deleteTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        teacherDAO.updateTeacher(teacher);
    }

    @Override
    public void insertTeacher(Teacher teacher) throws SQLException {
        teacherDAO.insertTeacher(teacher);
    }
}
