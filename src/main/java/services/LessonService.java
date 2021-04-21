package services;

import classes.Classroom;
import classes.Lesson;
import classes.Student;
import classes.Teacher;

import java.sql.SQLException;
import java.util.Collection;

public interface LessonService {
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
