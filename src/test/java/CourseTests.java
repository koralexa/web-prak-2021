import classes.*;
import dao.Factory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;

public class CourseTests {
    @Test
    public void insertCourse() throws SQLException {
        Course course = new Course();
        course.setCourse_name("История");
        course.setStudy_year(2L);
        course.setCoverage("Потоковый");
        course.setIntensity(3L);
        course.setTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(5L));

        Factory.getInstance().getCourseDAO().insertCourse(course);
        Course inserted_course = Factory.getInstance().getCourseDAO().getCourseById(course.getCourse_id());
        Assert.assertTrue(course.isEqual(inserted_course), "TEST FAILED: insertCourse: objects aren't equal");
        Factory.getInstance().getCourseDAO().deleteCourse(course);
    }

    @Test
    public void updateCourse() throws SQLException {
        Course course = new Course();
        course.setCourse_name("История");
        course.setStudy_year(2L);
        course.setCoverage("Потоковый");
        course.setIntensity(3L);
        course.setTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(5L));

        Factory.getInstance().getCourseDAO().insertCourse(course);
        course.setTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(3L));
        Factory.getInstance().getCourseDAO().updateCourse(course);
        Course updated_course = Factory.getInstance().getCourseDAO().getCourseById(course.getCourse_id());
        Assert.assertTrue(course.isEqual(updated_course), "TEST FAILED: updateCourse: objects aren't equal");
        Factory.getInstance().getCourseDAO().deleteCourse(course);
    }

    @Test
    public void deleteCourse() throws SQLException {
        Course course = new Course();
        course.setCourse_name("История");
        course.setStudy_year(2L);
        course.setCoverage("Потоковый");
        course.setIntensity(3L);
        course.setTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(5L));

        Factory.getInstance().getCourseDAO().insertCourse(course);
        Factory.getInstance().getCourseDAO().deleteCourse(course);
        Course deleted_course = Factory.getInstance().getCourseDAO().getCourseById(course.getCourse_id());
        Assert.assertNull(deleted_course, "TEST FAILED: deleteCourse: object != null");
    }

    @Test
    public void getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(1L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(2L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(3L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(4L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(5L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(6L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(7L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(8L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(9L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(10L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(11L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(12L));

        Collection<Course> selected_courses = Factory.getInstance().getCourseDAO().getAllCourses();
        for (Object obj : selected_courses) {
            boolean selected = false;
            for (Object c : courses) {
                if (((Course)obj).isEqual((Course)c)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getAllCourses: required object not found");
        }
        Assert.assertEquals(courses.size(), selected_courses.size(), "TEST FAILED: getAllCourses: wrong number of objects");
    }

    @Test
    public void getCoursesByTeacherFound() throws SQLException {
        List<Course> courses = new ArrayList<>();
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(4L));
        courses.add(Factory.getInstance().getCourseDAO().getCourseById(9L));
        Teacher teacher = Factory.getInstance().getTeacherDAO().getTeacherById(4L);

        Collection<Course> selected_courses = Factory.getInstance().getCourseDAO().getCoursesByTeacher(teacher);
        for (Object obj : selected_courses) {
            boolean selected = false;
            for (Object c : courses) {
                if (((Course)obj).isEqual((Course)c)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getCoursesByTeacherFound: required object not found");
        }
        Assert.assertEquals(courses.size(), selected_courses.size(), "TEST FAILED: getCoursesByTeacherFound: wrong number of objects");
    }

    @Test
    public void getCoursesByTeacherNotFound() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setFull_name("Иванов Иван Иванович");
        Factory.getInstance().getTeacherDAO().insertTeacher(teacher);
        Collection<Course> selected_courses = Factory.getInstance().getCourseDAO().getCoursesByTeacher(teacher);
        Assert.assertEquals(selected_courses.size(), 0, "TEST FAILED: getCoursesByTeacherNotFound: wrong number of objects");
        Factory.getInstance().getTeacherDAO().deleteTeacher(teacher);
    }
}
