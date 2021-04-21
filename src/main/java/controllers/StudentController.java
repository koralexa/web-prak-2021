package controllers;

import classes.Student;
import classes.StudyGroup;
import classes.Stream;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.Impl.StudentServiceImpl;
import services.StudentService;

import java.sql.SQLException;
import java.util.Collection;

@RestController
public class StudentController {
    private StudentService studentService = new StudentServiceImpl();

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ModelAndView allStudents() throws SQLException {
        Collection<Student> students = studentService.getAllStudents();
        ModelAndView modelAndView = new ModelAndView();
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
    public ModelAndView addStudent(@ModelAttribute("student") Student student) throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/students");
        studentService.insertStudent(student);
        return modelAndView;
    }
}
