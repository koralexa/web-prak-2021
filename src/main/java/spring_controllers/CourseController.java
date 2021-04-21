package spring_controllers;

import classes.Course;
import classes.Teacher;
import dao.Factory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.Impl.CourseServiceImpl;
import services.CourseService;

import java.sql.SQLException;
import java.util.Collection;

@RestController
public class CourseController {
    private final CourseService courseService = new CourseServiceImpl();

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ModelAndView allCourses(@RequestParam(name = "teacher_id", required = false) Long teacher_id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("courses");
        try {
            Collection<Course> courses = teacher_id == null
                    ? courseService.getAllCourses()
                    : courseService.getCoursesByTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(teacher_id));
            modelAndView.addObject("coursesList", courses);
            Collection<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
            modelAndView.addObject("teachers", teachers);
        } catch (Exception e) {
            modelAndView.setViewName("coursesFilterError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView allCourses() throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Course> courses = courseService.getAllCourses();
        modelAndView.setViewName("courses");
        Collection<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
        modelAndView.addObject("teachers", teachers);
        modelAndView.addObject("coursesList", courses);
        return modelAndView;
    }

    @RequestMapping(value = "/courses/addCourse", method = RequestMethod.GET)
    public ModelAndView addCoursePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addCourse");
        try {
            Collection<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
            modelAndView.addObject("teachers", teachers);
        } catch (Exception e) {
            modelAndView.setViewName("addCourseError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/addCourse", method = RequestMethod.POST)
    public ModelAndView addCourse(@RequestParam(name = "course_name", required = false) String course_name,
                                  @RequestParam(name = "coverage", required = false) String coverage,
                                  @RequestParam(name = "intensity", required = false) Long intensity,
                                  @RequestParam(name = "study_year", required = false) Long study_year,
                                  @RequestParam(name = "teacher_id", required = false) Long teacher_id) {
        Course course = new Course();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/courses");
        try {
            course.setCourse_name(course_name);
            course.setCoverage(coverage);
            course.setIntensity(intensity);
            if (study_year != null) {
                course.setStudy_year(study_year);
            }
            if (teacher_id != null) {
                course.setTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(teacher_id));
            }
            courseService.insertCourse(course);
        } catch (Exception e) {
            modelAndView.setViewName("addCourseError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/addCourseError", method = RequestMethod.GET)
    public ModelAndView addCourseErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addCourseError");
        return modelAndView;
    }
}
