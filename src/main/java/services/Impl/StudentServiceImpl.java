package services.Impl;

import classes.Stream;
import classes.StudyGroup;
import dao.StudentDAO;
import dao.Impl.StudentDAOImpl;
import classes.Student;

import services.StudentService;

import java.sql.SQLException;
import java.util.Collection;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public Collection<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    @Override
    public Student getStudentById(Long id) throws SQLException {
        return studentDAO.getStudentById(id);
    }

    @Override
    public Collection<Student> getStudentsByStudyYear(Long study_year) throws SQLException {
        return studentDAO.getStudentsByStudyYear(study_year);
    }

    @Override
    public Collection<Student> getStudentsByStudyGroup(StudyGroup group) throws SQLException {
        return studentDAO.getStudentsByStudyGroup(group);
    }

    @Override
    public Collection<Student> getStudentsByStream(Stream stream) throws SQLException {
        return studentDAO.getStudentsByStream(stream);
    }

    @Override
    public void deleteStudent(Student student) throws SQLException {
        studentDAO.deleteStudent(student);
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        studentDAO.updateStudent(student);
    }

    @Override
    public void insertStudent(Student student) throws SQLException {
        studentDAO.insertStudent(student);
    }
}
