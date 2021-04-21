package spring_controllers;

import classes.Student;
import dao.Factory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.Impl.StudentServiceImpl;
import services.StudentService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

@RestController
public class StudentController {
    private final StudentService studentService = new StudentServiceImpl();

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ModelAndView allStudents(@RequestParam(name = "group_number", required = false) Long group_number,
                                    @RequestParam(name = "stream_number", required = false) Long stream_number,
                                    @RequestParam(name = "study_year", required = false) Long study_year) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("students");
        try {
            Collection<Student> students_by_group = group_number == null
                    ? studentService.getAllStudents()
                    : studentService.getStudentsByStudyGroup(Factory.getInstance().getStudyGroupDAO().getStudyGroupById(group_number));
            Collection<Student> students_by_stream = stream_number == null
                    ? studentService.getAllStudents()
                    : studentService.getStudentsByStream(Factory.getInstance().getStreamDAO().getStreamById(stream_number));
            Collection<Student> students_by_year = study_year == null
                    ? studentService.getAllStudents()
                    : studentService.getStudentsByStudyYear(study_year);
            if (students_by_group.isEmpty() || students_by_stream.isEmpty() || students_by_year.isEmpty()) {
                modelAndView.addObject("studentsList", null);
            } else {
                Collection<Student> students = new java.util.ArrayList<>(Collections.emptyList());
                for (Student s : students_by_group) {
                    boolean in_stream = false;
                    boolean in_year = false;
                    for (Student s1 : students_by_stream) {
                        if (s1.isEqual(s)) {
                            in_stream = true;
                            break;
                        }
                    }
                    for (Student s1 : students_by_year) {
                        if (s1.isEqual(s)) {
                            in_year = true;
                            break;
                        }
                    }
                    if (in_stream && in_year) {
                        students.add(s);
                    }
                }
                modelAndView.addObject("studentsList", students);
            }
        } catch (Exception e) {
            modelAndView.setViewName("studentsFilterError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ModelAndView allStudents() throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Student> students = studentService.getAllStudents();
        modelAndView.setViewName("students");
        modelAndView.addObject("studentsList", students);
        return modelAndView;
    }

    @RequestMapping(value = "/students/addStudent", method = RequestMethod.GET)
    public ModelAndView addStudentPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addStudent");
        return modelAndView;
    }

    @RequestMapping(value = "/students/addStudent", method = RequestMethod.POST)
    public ModelAndView addStudent(@RequestParam(name = "full_name", required = false) String full_name,
                                   @RequestParam(name = "study_year", required = false) Long study_year,
                                   @RequestParam(name = "group_number", required = false) Long group_number) {
        Student student = new Student();
        student.setFull_name(full_name);
        student.setStudy_year(study_year);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/students");
        try {
            if (group_number != null) {
                student.setGroup(Factory.getInstance().getStudyGroupDAO().getStudyGroupById(group_number));
            }
            studentService.insertStudent(student);
        } catch (Exception e) {
            modelAndView.setViewName("addStudentError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/addStudentError", method = RequestMethod.GET)
    public ModelAndView addStudentErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addStudentError");
        return modelAndView;
    }
}
