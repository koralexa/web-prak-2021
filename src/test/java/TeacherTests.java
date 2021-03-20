import classes.*;
import dao.Factory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;

public class TeacherTests {
    @Test
    public void insertTeacher() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setFull_name("Иванов Иван Иванович");

        Factory.getInstance().getTeacherDAO().insertTeacher(teacher);
        Teacher inserted_teacher = Factory.getInstance().getTeacherDAO().getTeacherById(teacher.getTeacher_id());
        Assert.assertTrue(teacher.isEqual(inserted_teacher), "TEST FAILED: insertTeacher: objects aren't equal");
        Factory.getInstance().getTeacherDAO().deleteTeacher(teacher);
    }

    @Test
    public void updateTeacher() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setFull_name("Иванов Иван Иванович");

        Factory.getInstance().getTeacherDAO().insertTeacher(teacher);
        teacher.setFull_name("Петров Петр Петрович");
        Factory.getInstance().getTeacherDAO().updateTeacher(teacher);
        Teacher updated_teacher = Factory.getInstance().getTeacherDAO().getTeacherById(teacher.getTeacher_id());
        Assert.assertTrue(teacher.isEqual(updated_teacher), "TEST FAILED: updateTeacher: objects aren't equal");
        Factory.getInstance().getTeacherDAO().deleteTeacher(teacher);
    }

    @Test
    public void deleteCourse() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setFull_name("Иванов Иван Иванович");

        Factory.getInstance().getTeacherDAO().insertTeacher(teacher);
        Factory.getInstance().getTeacherDAO().deleteTeacher(teacher);
        Teacher deleted_teacher = Factory.getInstance().getTeacherDAO().getTeacherById(teacher.getTeacher_id());
        Assert.assertNull(deleted_teacher, "TEST FAILED: deleteTeacher: object != null");
    }

    @Test
    public void getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(Factory.getInstance().getTeacherDAO().getTeacherById(1L));
        teachers.add(Factory.getInstance().getTeacherDAO().getTeacherById(2L));
        teachers.add(Factory.getInstance().getTeacherDAO().getTeacherById(3L));
        teachers.add(Factory.getInstance().getTeacherDAO().getTeacherById(4L));
        teachers.add(Factory.getInstance().getTeacherDAO().getTeacherById(5L));

        Collection<Teacher> selected_teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
        for (Object obj : selected_teachers) {
            boolean selected = false;
            for (Object t : teachers) {
                if (((Teacher)obj).isEqual((Teacher)t)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getAllTeachers: required object not found");
        }
        Assert.assertEquals(teachers.size(), selected_teachers.size(), "TEST FAILED: getAllTeachers: wrong number of objects");
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