package services.Impl;

import classes.Classroom;
import classes.Lesson;
import classes.Student;
import classes.Teacher;
import dao.Impl.LessonDAOImpl;
import dao.LessonDAO;
import services.LessonService;

import java.sql.SQLException;
import java.util.Collection;

public class LessonServiceImpl implements LessonService {
    private final LessonDAO lessonDAO = new LessonDAOImpl();

    @Override
    public Collection<Lesson> getAllLessons() throws SQLException {
        return lessonDAO.getAllLessons();
    }

    @Override
    public Lesson getLessonById(Long id) throws SQLException {
        return lessonDAO.getLessonById(id);
    }

    @Override
    public Collection<Lesson> getLessonsByTeacher(Teacher teacher) throws SQLException {
        return lessonDAO.getLessonsByTeacher(teacher);
    }

    @Override
    public Collection<Lesson> getLessonsByStudent(Student student) throws SQLException {
        return lessonDAO.getLessonsByStudent(student);
    }

    @Override
    public Collection<Lesson> getLessonsByClassroom(Classroom classroom) throws SQLException {
        return lessonDAO.getLessonsByClassroom(classroom);
    }

    @Override
    public Collection<Lesson> getLessonsByDay(Long day) throws SQLException {
        return lessonDAO.getLessonsByDay(day);
    }

    @Override
    public void deleteLesson(Lesson lesson) throws SQLException {
        lessonDAO.deleteLesson(lesson);
    }

    @Override
    public void updateLesson(Lesson lesson) throws SQLException {
        lessonDAO.updateLesson(lesson);
    }

    @Override
    public void insertLesson(Lesson lesson) throws SQLException {
        lessonDAO.insertLesson(lesson);
    }
}
