package dao;
import dao.Impl.*;

public class Factory {
    private static ClassroomDAO classroomDAO = null;
    private static StudentDAO studentDAO = null;
    private static CourseDAO courseDAO = null;
    private static LessonDAO lessonDAO = null;
    private static PassedCourseDAO passed_courseDAO = null;
    private static TeacherDAO teacherDAO;

    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public StudentDAO getStudentDAO() {
        if (studentDAO == null) {
            studentDAO = new StudentDAOImpl();
        }
        return studentDAO;
    }

    public ClassroomDAO getClassroomDAO() {
        if (classroomDAO == null) {
            classroomDAO = new ClassroomDAOImpl();
        }
        return classroomDAO;
    }

    public CourseDAO getCourseDAO() {
        if (courseDAO == null) {
            courseDAO = new CourseDAOImpl();
        }
        return courseDAO;
    }

    public LessonDAO getLessonDAO() {
        if (lessonDAO == null) {
            lessonDAO = new LessonDAOImpl();
        }
        return lessonDAO;
    }

    public PassedCourseDAO getPassedCourseDAO() {
        if (passed_courseDAO == null) {
            passed_courseDAO = new PassedCourseDAOImpl();
        }
        return passed_courseDAO;
    }

    public TeacherDAO getTeacherDAO() {
        if (teacherDAO == null) {
            teacherDAO = new TeacherDAOImpl();
        }
        return teacherDAO;
    }
}
