package spring_controllers;

import classes.Teacher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import services.TeacherService;
import services.Impl.TeacherServiceImpl;

import java.sql.SQLException;
import java.util.Collection;

@RestController
public class TeacherController {
    private final TeacherService teacherService = new TeacherServiceImpl();

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ModelAndView allTeachers() throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Teacher> teachers = teacherService.getAllTeachers();
        modelAndView.setViewName("teachers");
        modelAndView.addObject("teachersList", teachers);
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/addTeacher", method = RequestMethod.GET)
    public ModelAndView addTeacherPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addTeacher");
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/addTeacher", method = RequestMethod.POST)
    public ModelAndView addTeacher(@RequestParam(name = "full_name", required = false) String full_name) {
        Teacher teacher = new Teacher();
        teacher.setFull_name(full_name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/teachers");
        try {
            if (full_name == null || full_name == "") {
                throw new Exception();
            }
            teacherService.insertTeacher(teacher);
        } catch (Exception e) {
            modelAndView.setViewName("addTeacherError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/addTeacherError", method = RequestMethod.GET)
    public ModelAndView addTeacherErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addTeacherError");
        return modelAndView;
    }
}
