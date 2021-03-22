import classes.*;
import dao.Factory;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;

public class LessonTests {
    @Test
    public void insertLesson() throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setCourse(Factory.getInstance().getCourseDAO().getCourseById(7L));
        lesson.setClassroom(Factory.getInstance().getClassroomDAO().getClassroomById(1L));
        lesson.setWeek_day(3L);
        lesson.setLesson_time(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));

        Factory.getInstance().getLessonDAO().insertLesson(lesson);
        Lesson inserted_lesson = Factory.getInstance().getLessonDAO().getLessonById(lesson.getLesson_id());
        Assert.assertTrue(lesson.isEqual(inserted_lesson), "insertLesson: objects aren't equal");
        Factory.getInstance().getLessonDAO().deleteLesson(lesson);
    }

    @Test
    public void updateLesson() throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setCourse(Factory.getInstance().getCourseDAO().getCourseById(7L));
        lesson.setClassroom(Factory.getInstance().getClassroomDAO().getClassroomById(1L));
        lesson.setWeek_day(3L);
        lesson.setLesson_time(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));

        Factory.getInstance().getLessonDAO().insertLesson(lesson);
        lesson.setLesson_time(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
        Factory.getInstance().getLessonDAO().updateLesson(lesson);
        Lesson updated_lesson = Factory.getInstance().getLessonDAO().getLessonById(lesson.getLesson_id());
        Assert.assertTrue(lesson.isEqual(updated_lesson), "updateLesson: objects aren't equal");
        Factory.getInstance().getLessonDAO().deleteLesson(lesson);
    }

    @Test
    public void deleteLesson() throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setCourse(Factory.getInstance().getCourseDAO().getCourseById(7L));
        lesson.setClassroom(Factory.getInstance().getClassroomDAO().getClassroomById(1L));
        lesson.setWeek_day(3L);
        lesson.setLesson_time(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));

        Factory.getInstance().getLessonDAO().insertLesson(lesson);
        Factory.getInstance().getLessonDAO().deleteLesson(lesson);
        Lesson deleted_lesson = Factory.getInstance().getLessonDAO().getLessonById(lesson.getLesson_id());
        Assert.assertNull(deleted_lesson, "deleteLesson: object != null");
    }

    @Test
    public void getAllLessons() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(1L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(2L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(3L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(4L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(5L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(6L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(7L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(8L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(9L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(10L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(11L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(12L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(13L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(14L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(15L));

        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getAllLessons();
        for (Object obj : selected_lessons) {
            boolean selected = false;
            for (Object l : lessons) {
                if (((Lesson)obj).isEqual((Lesson)l)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "getAllLessons: required object not found");
        }
        Assert.assertEquals(lessons.size(), selected_lessons.size(), "getAllLessons: wrong number of objects");
    }

    @Test
    public void getLessonsByTeacherFound() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(5L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(7L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(8L));
        Teacher teacher = Factory.getInstance().getTeacherDAO().getTeacherById(3L);

        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByTeacher(teacher);
        for (Object obj : selected_lessons) {
            boolean selected = false;
            for (Object l : lessons) {
                if (((Lesson)obj).isEqual((Lesson)l)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "getLessonsByTeacherFound: required object not found");
        }
        Assert.assertEquals(lessons.size(), selected_lessons.size(), "getLessonsByTeacherFound: wrong number of objects");
    }

    @Test
    public void getLessonsByTeacherNotFound() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setFull_name("Иванов Иван Иванович");
        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByTeacher(teacher);
        Assert.assertEquals(selected_lessons.size(), 0, "getLessonsByTeacherNotFound: wrong number of objects");
    }

    @Test
    public void getLessonsByStudentFound() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(2L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(3L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(12L));
        Student student = Factory.getInstance().getStudentDAO().getStudentById(7L);

        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByStudent(student);
        for (Object obj : selected_lessons) {
            boolean selected = false;
            for (Object l : lessons) {
                if (((Lesson)obj).isEqual((Lesson)l)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "getLessonsByStudentFound: required object not found");
        }
        Assert.assertEquals(lessons.size(), selected_lessons.size(), "getLessonsByStudentFound: wrong number of objects");
    }

    @Test
    public void getLessonsByStudentNotFound() throws SQLException {
        Stream stream = new Stream();
        stream.setStream_number(4L);
        StudyGroup group = new StudyGroup();
        group.setGroup_number(308L);
        group.setStream(stream);
        Student student = new Student();
        student.setFull_name("Петров Петр Петрович");
        student.setStudy_year(4L);
        student.setGroup(group);

        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByStudent(student);
        Assert.assertEquals(selected_lessons.size(), 0, "getLessonsByStudentNotFound: wrong number of objects");
    }

    @Test
    public void getLessonsByClassroomFound() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(5L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(13L));
        Classroom classroom = Factory.getInstance().getClassroomDAO().getClassroomById(5L);

        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByClassroom(classroom);
        for (Object obj : selected_lessons) {
            boolean selected = false;
            for (Object l : lessons) {
                if (((Lesson)obj).isEqual((Lesson)l)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "getLessonsByClassroomFound: required object not found");
        }
        Assert.assertEquals(lessons.size(), selected_lessons.size(), "getLessonsByClassroomFound: wrong number of objects");
    }

    @Test
    public void getLessonsByClassroomNotFound() throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setClassroom_number(410L);
        classroom.setCapacity(90L);

        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByClassroom(classroom);
        Assert.assertEquals(selected_lessons.size(), 0, "getLessonsByClassroomNotFound: wrong number of objects");
    }

    @Test
    public void getLessonsByDayFound() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(4L));
        lessons.add(Factory.getInstance().getLessonDAO().getLessonById(10L));

        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByDay(4L);
        for (Object obj : selected_lessons) {
            boolean selected = false;
            for (Object l : lessons) {
                if (((Lesson)obj).isEqual((Lesson)l)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "getLessonsByDayFound: required object not found");
        }
        Assert.assertEquals(lessons.size(), selected_lessons.size(), "getLessonsByDayFound: wrong number of objects");
    }

    @Test
    public void getLessonsByDayNotFound() throws SQLException {
        Collection<Lesson> selected_lessons = Factory.getInstance().getLessonDAO().getLessonsByDay(7L);
        Assert.assertEquals(selected_lessons.size(), 0, "getLessonsByDayNotFound: wrong number of objects");
    }
}
