import classes.*;
import dao.Factory;

import java.sql.SQLException;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PassedCourseTests {
    @Test
    public void insertPassedCourse() throws SQLException {
        PassedCourse passed_course = new PassedCourse();
        passed_course.setStudent(Factory.getInstance().getStudentDAO().getStudentById(9L));
        passed_course.setCourse(Factory.getInstance().getCourseDAO().getCourseById(6L));
        passed_course.setStudy_year(1L);

        Factory.getInstance().getPassedCourseDAO().insertPassedCourse(passed_course);
        PassedCourse inserted_passed_course = Factory.getInstance().getPassedCourseDAO().getPassedCourseById(passed_course.getPassed_course_id());
        Assert.assertTrue(passed_course.isEqual(inserted_passed_course), "TEST FAILED: insertPassedCourse: objects aren't equal");
        Factory.getInstance().getPassedCourseDAO().deletePassedCourse(passed_course);
    }

    @Test
    public void updateClassroom() throws SQLException {
        PassedCourse passed_course = new PassedCourse();
        passed_course.setStudent(Factory.getInstance().getStudentDAO().getStudentById(9L));
        passed_course.setCourse(Factory.getInstance().getCourseDAO().getCourseById(6L));
        passed_course.setStudy_year(1L);

        Factory.getInstance().getPassedCourseDAO().insertPassedCourse(passed_course);
        passed_course.setStudy_year(2L);
        Factory.getInstance().getPassedCourseDAO().updatePassedCourse(passed_course);
        PassedCourse updated_passed_course = Factory.getInstance().getPassedCourseDAO().getPassedCourseById(passed_course.getPassed_course_id());
        Assert.assertTrue(passed_course.isEqual(updated_passed_course), "TEST FAILED: updatePassedCourse: objects aren't equal");
        Factory.getInstance().getPassedCourseDAO().deletePassedCourse(passed_course);
    }

    @Test
    public void deleteClassroom() throws SQLException {
        PassedCourse passed_course = new PassedCourse();
        passed_course.setStudent(Factory.getInstance().getStudentDAO().getStudentById(9L));
        passed_course.setCourse(Factory.getInstance().getCourseDAO().getCourseById(6L));
        passed_course.setStudy_year(1L);

        Factory.getInstance().getPassedCourseDAO().insertPassedCourse(passed_course);
        Factory.getInstance().getPassedCourseDAO().deletePassedCourse(passed_course);
        PassedCourse deleted_passed_course = Factory.getInstance().getPassedCourseDAO().getPassedCourseById(passed_course.getPassed_course_id());
        Assert.assertNull(deleted_passed_course, "TEST FAILED: deletePassedCourse: object != null");
    }
}

