package dao;

import classes.Lesson;
import classes.Teacher;
import classes.Student;
import classes.Classroom;

import java.util.Collection;
import java.sql.SQLException;

public interface LessonDAO {
    public Collection<Lesson> getAllLessons() throws SQLException;

    public Lesson getLessonById(Long id) throws SQLException;

    public Collection<Lesson> getLessonsByTeacher(Teacher teacher) throws SQLException;

    public Collection<Lesson> getLessonsByStudent(Student student) throws SQLException;

    public Collection<Lesson> getLessonsByClassroom(Classroom classroom) throws SQLException;

    public Collection<Lesson> getLessonsByDay(Long day) throws SQLException;

    public void deleteLesson(Lesson lesson) throws SQLException;

    public void updateLesson(Lesson lesson) throws SQLException;

    public void insertLesson(Lesson lesson) throws SQLException;
}