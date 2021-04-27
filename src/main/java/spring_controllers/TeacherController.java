package spring_controllers;

import classes.Course;
import classes.PassedCourse;
import classes.Teacher;
import classes.Teacher;
import dao.Factory;
import org.springframework.web.bind.annotation.*;
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
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления преподавателя");
            modelAndView.addObject("back", "/teachers");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/addTeacherError", method = RequestMethod.GET)
    public ModelAndView addTeacherErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", "Ошибка добавления преподавателя");
        modelAndView.addObject("back", "/teachers");
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacherInfo");
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            modelAndView.addObject("teacher", teacher);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/teachers");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteTeacher(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/teachers");
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            teacherService.deleteTeacher(teacher);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка удаления преподавателя");
            modelAndView.addObject("back", "/teachers/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editTeacherPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editTeacher");
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            modelAndView.addObject("teacher", teacher);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/teachers/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/teachers/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editTeacher(@RequestParam(name = "full_name", required = false) String full_name,
                                    @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/teachers/" + id.toString());
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            if (full_name != null && !full_name.equals("")) {
                teacher.setFull_name(full_name);
            } else {
                throw new Exception();
            }
            teacherService.updateTeacher(teacher);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка редактирования преподавателя");
            modelAndView.addObject("back", "/teachers/" + id.toString());
        }
        return modelAndView;
    }
}
