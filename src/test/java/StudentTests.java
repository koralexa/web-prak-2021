import classes.*;
import dao.Factory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;

public class StudentTests {
    @Test
    public void insertStudent() throws SQLException {
        Stream stream = new Stream();
        stream.setStream_number(1L);
        StudyGroup group = new StudyGroup();
        group.setGroup_number(201L);
        group.setStream(stream);
        Student student = new Student();
        student.setFull_name("Иванов Иван Иванович");
        student.setStudy_year(2L);
        student.setGroup(group);

        Factory.getInstance().getStudentDAO().insertStudent(student);
        Student inserted_student = Factory.getInstance().getStudentDAO().getStudentById(student.getStudent_id());
        Assert.assertTrue(student.isEqual(inserted_student), "TEST FAILED: insertStudent: objects aren't equal");
        Factory.getInstance().getStudentDAO().deleteStudent(student);
    }

    @Test
    public void updateStudent() throws SQLException {
        Stream stream = new Stream();
        stream.setStream_number(1L);
        StudyGroup group = new StudyGroup();
        group.setGroup_number(201L);
        group.setStream(stream);
        Student student = new Student();
        student.setFull_name("Иванов Иван Иванович");
        student.setStudy_year(2L);
        student.setGroup(group);

        Factory.getInstance().getStudentDAO().insertStudent(student);
        student.setFull_name("Петров Петр Петрович");
        Factory.getInstance().getStudentDAO().updateStudent(student);
        Student updated_student = Factory.getInstance().getStudentDAO().getStudentById(student.getStudent_id());
        Assert.assertTrue(student.isEqual(updated_student), "TEST FAILED: updateStudent: objects aren't equal");
        Factory.getInstance().getStudentDAO().deleteStudent(student);
    }

    @Test
    public void deleteStudent() throws SQLException {
        Stream stream = new Stream();
        stream.setStream_number(1L);
        StudyGroup group = new StudyGroup();
        group.setGroup_number(201L);
        group.setStream(stream);
        Student student = new Student();
        student.setFull_name("Иванов Иван Иванович");
        student.setStudy_year(2L);
        student.setGroup(group);

        Factory.getInstance().getStudentDAO().insertStudent(student);
        Factory.getInstance().getStudentDAO().deleteStudent(student);
        Student deleted_student = Factory.getInstance().getStudentDAO().getStudentById(student.getStudent_id());
        Assert.assertNull(deleted_student, "TEST FAILED: deleteStudent: object != null");
    }

    @Test
    public void getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        students.add(Factory.getInstance().getStudentDAO().getStudentById(1L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(2L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(3L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(4L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(5L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(6L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(7L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(8L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(9L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(10L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(11L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(12L));

        Collection<Student> selected_students = Factory.getInstance().getStudentDAO().getAllStudents();
        for (Object obj : selected_students) {
            boolean selected = false;
            for (Object s : students) {
                if (((Student)obj).isEqual((Student)s)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getAllStudents: required object not found");
        }
        Assert.assertEquals(students.size(), selected_students.size(), "TEST FAILED: getAllStudents: wrong number of objects");
    }

    @Test
    public void getStudentsByStudyYearFound() throws SQLException {
        List<Student> students = new ArrayList<>();
        students.add(Factory.getInstance().getStudentDAO().getStudentById(1L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(2L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(3L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(4L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(5L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(6L));

        Collection<Student> selected_students = Factory.getInstance().getStudentDAO().getStudentsByStudyYear(1L);
        for (Object obj : selected_students) {
            boolean selected = false;
            for (Object s : students) {
                if (((Student)obj).isEqual((Student)s)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getStudentsByStudyYearFound: required object not found");
        }
        Assert.assertEquals(students.size(), selected_students.size(), "TEST FAILED: getStudentsByStudyYearFound: wrong number of objects");
    }

    @Test
    public void getStudentsByStudyYearNotFound() throws SQLException {
        Collection<Student> selected_students = Factory.getInstance().getStudentDAO().getStudentsByStudyYear(8L);
        Assert.assertEquals(selected_students.size(), 0, "TEST FAILED: getStudentsByStudyYearNotFound: wrong number of objects");
    }

    @Test
    public void getStudentsByStudyGroupFound() throws SQLException {
        List<Student> students = new ArrayList<>();
        StudyGroup group = new StudyGroup();
        group.setGroup_number(203L);
        students.add(Factory.getInstance().getStudentDAO().getStudentById(11L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(12L));

        Collection<Student> selected_students = Factory.getInstance().getStudentDAO().getStudentsByStudyGroup(group);
        for (Object obj : selected_students) {
            boolean selected = false;
            for (Object s : students) {
                if (((Student)obj).isEqual((Student)s)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getStudentsByStudyGroupFound: required object not found");
        }
        Assert.assertEquals(students.size(), selected_students.size(), "TEST FAILED: getStudentsByStudyGroupFound: wrong number of objects");
    }

    @Test
    public void getStudentsByStudyGroupNotFound() throws SQLException {
        StudyGroup group = new StudyGroup();
        group.setGroup_number(403L);

        Collection<Student> selected_students = Factory.getInstance().getStudentDAO().getStudentsByStudyGroup(group);
        Assert.assertEquals(selected_students.size(), 0, "TEST FAILED: getStudentsByStudyGroupNotFound: wrong number of objects");
    }

    @Test
    public void getStudentsByStreamFound() throws SQLException {
        List<Student> students = new ArrayList<>();
        Stream stream = new Stream();
        stream.setStream_number(3L);
        students.add(Factory.getInstance().getStudentDAO().getStudentById(11L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(12L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(5L));
        students.add(Factory.getInstance().getStudentDAO().getStudentById(6L));

        Collection<Student> selected_students = Factory.getInstance().getStudentDAO().getStudentsByStream(stream);
        for (Object obj : selected_students) {
            boolean selected = false;
            for (Object s : students) {
                if (((Student)obj).isEqual((Student)s)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getStudentByStudyYearFound: required object not found");
        }
        Assert.assertEquals(students.size(), selected_students.size(), "TEST FAILED: getStudentsByStreamFound: wrong number of objects");
    }

    @Test
    public void getStudentsByStreamNotFound() throws SQLException {
        Stream stream = new Stream();
        stream.setStream_number(5L);

        Collection<Student> selected_students = Factory.getInstance().getStudentDAO().getStudentsByStream(stream);
        Assert.assertEquals(selected_students.size(), 0, "TEST FAILED: getStudentsByStreamNotFound: wrong number of objects");
    }
}
