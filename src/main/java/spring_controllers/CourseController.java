package spring_controllers;

import classes.*;
import classes.Course;
import dao.Factory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.Impl.CourseServiceImpl;
import services.CourseService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

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
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Неверные значения фильтров");
            modelAndView.addObject("back", "/courses");
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
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления курса");
            modelAndView.addObject("back", "/courses");
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
            if (coverage.equals("Спецкурс") && study_year != null) {
                throw new Exception();
            }
            if (study_year != null) {
                course.setStudy_year(study_year);
            }
            if (teacher_id != null) {
                course.setTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(teacher_id));
            }
            courseService.insertCourse(course);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления курса");
            modelAndView.addObject("back", "/courses");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/addCourseError", method = RequestMethod.GET)
    public ModelAndView addCourseErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", "Ошибка добавления курса");
        modelAndView.addObject("back", "/courses");
        return modelAndView;
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("courseInfo");
        try {
            Course course = courseService.getCourseById(id);
            modelAndView.addObject("course", course);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/courses");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/{id}/delete-listener/{listener_id}", method = RequestMethod.GET)
    public ModelAndView deleteListener(@PathVariable("id") Long id,
                                       @PathVariable("listener_id") Long listener_id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("courseInfo");
        try {
            Listener listener = Factory.getInstance().getListenerDAO().getListenerById(listener_id);
            Factory.getInstance().getListenerDAO().deleteListener(listener);
            modelAndView.addObject("course", courseService.getCourseById(id));
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/courses/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/{id}/add-listener", method = RequestMethod.GET)
    public ModelAndView addListenerPage(@PathVariable("id") Long id) throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addListener");
        try {
            Course course = courseService.getCourseById(id);
            modelAndView.addObject("course", course);
            Collection<Listener> listeners = course.getListeners();
            Collection<Stream> streams = new java.util.ArrayList<>(Collections.emptyList());
            Collection<StudyGroup> groups = new java.util.ArrayList<>(Collections.emptyList());
            Collection<Student> students = new java.util.ArrayList<>(Collections.emptyList());
            if (course.getCoverage().equals("Потоковый")) {
                streams = Factory.getInstance().getStreamDAO().getAllStreams();
            } else if (course.getCoverage().equals("Групповой")) {
                groups = Factory.getInstance().getStudyGroupDAO().getAllStudyGroups();
            } else {
                students = Factory.getInstance().getStudentDAO().getAllStudents();
            }
            modelAndView.addObject("streams", streams);
            modelAndView.addObject("groups", groups);
            modelAndView.addObject("students", students);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/courses/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/{id}/add-listener", method = RequestMethod.POST)
    public ModelAndView addListener(@RequestParam(name = "listener_num", required = false) Long listener_num,
                                    @PathVariable("id") Long id) {
        Listener listener = new Listener();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/courses/" + id.toString());
        try {
            Course course = courseService.getCourseById(id);
            listener.setCourse(course);
            listener.setListener_num(listener_num);
            if (course.getCoverage().equals("Потоковый")) {
                listener.setListener_type("Поток");
            } else if (course.getCoverage().equals("Групповой")) {
                if (listener_num / 100 != course.getStudy_year()) {
                    throw new Exception();
                }
                listener.setListener_type("Группа");
            } else {
                listener.setListener_type("Студент");
            }
            Factory.getInstance().getListenerDAO().insertListener(listener);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления слушателя");
            modelAndView.addObject("back", "/courses/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteCourse(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/courses");
        try {
            Course course = courseService.getCourseById(id);
            courseService.deleteCourse(course);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка удаления курса");
            modelAndView.addObject("back", "/courses/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editCoursePage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editCourse");
        try {
            Course course = courseService.getCourseById(id);
            modelAndView.addObject("course", course);
            Collection<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
            modelAndView.addObject("teachers", teachers);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/courses/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/courses/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editCourse(@RequestParam(name = "course_name", required = false) String course_name,
                                   @RequestParam(name = "coverage", required = false) String coverage,
                                   @RequestParam(name = "intensity", required = false) Long intensity,
                                   @RequestParam(name = "study_year", required = false) Long study_year,
                                   @RequestParam(name = "teacher_id", required = false) Long teacher_id,
                                   @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/courses/" + id.toString());
        try {
            Course course = courseService.getCourseById(id);
            if (course_name != null && !course_name.equals("")) {
                course.setCourse_name(course_name);
            }
            if (coverage != null) {
                course.setCoverage(coverage);
            }
            if (coverage != null && coverage.equals("Спецкурс") && study_year != null) {
                throw new Exception();
            }
            if (coverage != null && !coverage.equals("Спецкурс") && study_year == null) {
                throw new Exception();
            }
            course.setStudy_year(study_year);
            if (intensity != null) {
                course.setIntensity(intensity);
            }
            if (teacher_id != null) {
                course.setTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(teacher_id));
            }
            courseService.updateCourse(course);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка редактирования курса");
            modelAndView.addObject("back", "/courses/" + id.toString());
        }
        return modelAndView;
    }
}
