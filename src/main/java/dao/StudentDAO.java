package dao;

import classes.Student;
import classes.StudyGroup;
import classes.Stream;

import java.util.Collection;
import java.sql.SQLException;

public interface StudentDAO {
    public Collection<Student> getAllStudents() throws SQLException;

    public Student getStudentById(Long id) throws SQLException;

    public Collection<Student> getStudentsByStudyYear(Long study_year) throws SQLException;

    public Collection<Student> getStudentsByStudyGroup(StudyGroup group) throws SQLException;

    public Collection<Student> getStudentsByStream(Stream stream) throws SQLException;

    public void deleteStudent(Student student) throws SQLException;

    public void updateStudent(Student student) throws SQLException;

    public void insertStudent(Student student) throws SQLException;
}
