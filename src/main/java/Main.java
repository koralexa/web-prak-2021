import java.io.*;
import classes.*;
import dao.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("============================== Students ==============================");
        Collection students = Factory.getInstance().getStudentDAO().getAllStudents();
        Iterator iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println(student);
        }



//        Student student = Factory.getInstance().getStudentDAO().getStudentById(3L);
//        System.out.println(student);



//        Stream s = new Stream();
//        s.setStream_number(2L);
//        StudyGroup g = new StudyGroup();
//        g.setGroup_number(202L);
//        g.setStream(s);
//        students = Factory.getInstance().getStudentDAO().getStudentsByParams(null, null, null);
//        iterator = students.iterator();
//        while (iterator.hasNext()) {
//            Student student = (Student) iterator.next();
//            System.out.println(student);
//        }



//        Student student = Factory.getInstance().getStudentDAO().getStudentById(2L);
//        Factory.getInstance().getStudentDAO().deleteStudent(student);
//        students = Factory.getInstance().getStudentDAO().getAllStudents();
//        iterator = students.iterator();
//        while (iterator.hasNext()) {
//            Student stud = (Student) iterator.next();
//            System.out.println(stud);
//        }
//
//        Stream s = new Stream();
//        s.setStream_number(1L);
//        StudyGroup g = new StudyGroup();
//        g.setGroup_number(101L);
//        g.setStream(s);
//        Student student2 = new Student(2L, "Ksidbcfiwnhcwehuodw", 1L, g, null);
//        Factory.getInstance().getStudentDAO().insertStudent(student2);
//        students = Factory.getInstance().getStudentDAO().getAllStudents();
//        iterator = students.iterator();
//        while (iterator.hasNext()) {
//            Student stud = (Student) iterator.next();
//            System.out.println(stud);
//        }
//
//        Student student3 = new Student(13L, "Попова Елена Николаевна", 1L, g, null);
//        Factory.getInstance().getStudentDAO().updateStudent(student3);
//        students = Factory.getInstance().getStudentDAO().getAllStudents();
//        iterator = students.iterator();
//        while (iterator.hasNext()) {
//            Student stud = (Student) iterator.next();
//            System.out.println(stud);
//        }



//        System.out.println("============================== Classrooms ==============================");
//        Collection classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
//        Iterator iterator = classrooms.iterator();
//        while (iterator.hasNext()) {
//            Classroom classroom = (Classroom) iterator.next();
//            System.out.println(classroom);
//        }



//        Classroom classroom = Factory.getInstance().getClassroomDAO().getClassroomById(5L);
//        System.out.println(classroom);



//        classrooms = Factory.getInstance().getClassroomDAO().getClassroomsByParams(10L, 50L);
//        iterator = classrooms.iterator();
//        while (iterator.hasNext()) {
//            Classroom classroom = (Classroom) iterator.next();
//            System.out.println(classroom);
//        }



//        Classroom classroom = Factory.getInstance().getClassroomDAO().getClassroomById(6L);
//        Factory.getInstance().getClassroomDAO().deleteClassroom(classroom);
//        classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
//        iterator = classrooms.iterator();
//        while (iterator.hasNext()) {
//            Classroom cl = (Classroom) iterator.next();
//            System.out.println(cl);
//        }
//
//        Classroom classroom2 = new Classroom(6L, 1000L, null);
//        Factory.getInstance().getClassroomDAO().insertClassroom(classroom2);
//        classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
//        iterator = classrooms.iterator();
//        while (iterator.hasNext()) {
//            Classroom cl = (Classroom) iterator.next();
//            System.out.println(cl);
//        }
//
//        Classroom classroom3 = new Classroom(6L, 15L, null);
//        Factory.getInstance().getClassroomDAO().updateClassroom(classroom3);
//        classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
//        iterator = classrooms.iterator();
//        while (iterator.hasNext()) {
//            Classroom cl = (Classroom) iterator.next();
//            System.out.println(cl);
//        }



//        System.out.println("============================== Courses ==============================");
//        Collection courses = Factory.getInstance().getCourseDAO().getAllCourses();
//        Iterator iterator = courses.iterator();
//        while (iterator.hasNext()) {
//            Course course = (Course) iterator.next();
//            System.out.println(course);
//        }



//        Course course = Factory.getInstance().getCourseDAO().getCourseById(11L);
//        System.out.println(course);


//        Teacher teacher = new Teacher();
//        teacher.setTeacher_id(4L);
//        teacher.setFull_name("Григорьев Илья Русланович");
//        courses = Factory.getInstance().getCourseDAO().getCoursesByTeacher(teacher);
//        iterator = courses.iterator();
//        while (iterator.hasNext()) {
//            Course course = (Course) iterator.next();
//            System.out.println(course);
//        }




//        Factory.getInstance().getLessonDAO().deleteLesson(Factory.getInstance().getLessonDAO().getLessonById(23L));
//        Factory.getInstance().getLessonDAO().deleteLesson(Factory.getInstance().getLessonDAO().getLessonById(30L));
//
//
//        System.out.println("============================== Lessons ==============================");
//        Collection lessons = Factory.getInstance().getLessonDAO().getAllLessons();
//        Iterator iterator = lessons.iterator();
//        while (iterator.hasNext()) {
//            Lesson lesson = (Lesson) iterator.next();
//            System.out.println(lesson);
//        }
//
//        System.out.println("============================== Lessons ==============================");
//        lessons = Factory.getInstance().getLessonDAO().getLessonsByStudent(Factory.getInstance().getStudentDAO().getStudentById(7L));
//        iterator = lessons.iterator();
//        while (iterator.hasNext()) {
//            Lesson lesson = (Lesson) iterator.next();
//            System.out.println(lesson);
//        }



//        Lesson lesson = Factory.getInstance().getLessonDAO().getLessonById(13L);
//        System.out.println(lesson);



//        Teacher teacher = new Teacher();
//        teacher.setTeacher_id(4L);
//        teacher.setFull_name("Григорьев Илья Русланович");
//        Student student = Factory.getInstance().getStudentDAO().getStudentById(3L);
//        Classroom classroom = Factory.getInstance().getClassroomDAO().getClassroomById(3L);
//        String day = "Пятница";
//        lessons = Factory.getInstance().getLessonDAO().getLessonsByParams(teacher, student, classroom, day);
//        iterator = lessons.iterator();
//        while (iterator.hasNext()) {
//            Lesson lesson = (Lesson) iterator.next();
//            System.out.println(lesson);
//        }



//        System.out.println("============================== Teachers ==============================");
//        Collection teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
//        Iterator iterator = teachers.iterator();
//        while (iterator.hasNext()) {
//            Teacher teacher = (Teacher) iterator.next();
//            System.out.println(teacher);
//        }
//
//
//
//        Teacher teacher = Factory.getInstance().getTeacherDAO().getTeacherById(2L);
//        System.out.println(teacher);
    }
}