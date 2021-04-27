package spring_controllers;

import classes.Course;
import classes.PassedCourse;
import classes.Student;
import dao.Factory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.CourseService;
import services.Impl.StudentServiceImpl;
import services.StudentService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

@RestController
public class StudentController {
    private final StudentService studentService = new StudentServiceImpl();

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ModelAndView allStudents(@RequestParam(name = "group_number", required = false) String group_number_s,
                                    @RequestParam(name = "stream_number", required = false) String stream_number_s,
                                    @RequestParam(name = "study_year", required = false) String study_year_s) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("students");
        try {
            Long group_number = group_number_s == null || group_number_s.equals("") ? null : Long.valueOf(group_number_s);
            Long stream_number = stream_number_s == null || stream_number_s.equals("") ? null : Long.valueOf(stream_number_s);
            Long study_year = study_year_s == null || study_year_s.equals("") ? null : Long.valueOf(study_year_s);
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
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Некорректные значения фильтров");
            modelAndView.addObject("back", "/students");
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
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления студента");
            modelAndView.addObject("back", "/students");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/addStudentError", method = RequestMethod.GET)
    public ModelAndView addStudentErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", "Ошибка добавления студента");
        modelAndView.addObject("back", "/students");
        return modelAndView;
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentInfo");
        try {
            Student student = studentService.getStudentById(id);
            modelAndView.addObject("student", student);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/students");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/{id}/delete-course/{course_id}", method = RequestMethod.GET)
    public ModelAndView deletePassedCourse(@PathVariable("id") Long id,
                                           @PathVariable("course_id") Long course_id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentInfo");
        try {
            PassedCourse course = Factory.getInstance().getPassedCourseDAO().getPassedCourseById(course_id);
            Factory.getInstance().getPassedCourseDAO().deletePassedCourse(course);
            modelAndView.addObject("student", studentService.getStudentById(id));
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/students/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/{id}/add-course", method = RequestMethod.GET)
    public ModelAndView addPassedCoursePage(@PathVariable("id") Long id) throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPassedCourse");
        try {
            Student student = studentService.getStudentById(id);
            modelAndView.addObject("student", student);
            Collection<Course> courses = Factory.getInstance().getCourseDAO().getAllCourses();
            courses.removeIf(c -> !c.getCoverage().equals("Спецкурс"));
            modelAndView.addObject("courses", courses);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/students/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/{id}/add-course", method = RequestMethod.POST)
    public ModelAndView addPassedCourse(@RequestParam(name = "student_id", required = false) Long student_id,
                                        @RequestParam(name = "course_id", required = false) Long course_id,
                                        @RequestParam(name = "study_year", required = false) Long study_year,
                                        @PathVariable("id") Long id) {
        PassedCourse course = new PassedCourse();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/students/" + id.toString());
        try {
            course.setCourse(Factory.getInstance().getCourseDAO().getCourseById(course_id));
            course.setStudent(studentService.getStudentById(student_id));
            course.setStudy_year(study_year);
            Factory.getInstance().getPassedCourseDAO().insertPassedCourse(course);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления пройденного курса");
            modelAndView.addObject("back", "/students/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteStudent(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/students");
        try {
            Student student = studentService.getStudentById(id);
            studentService.deleteStudent(student);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка удаления студента");
            modelAndView.addObject("back", "/students/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editStudentPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editStudent");
        try {
            Student student = studentService.getStudentById(id);
            modelAndView.addObject("student", student);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/students/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/students/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editStudent(@RequestParam(name = "full_name", required = false) String full_name,
                                    @RequestParam(name = "study_year", required = false) Long study_year,
                                    @RequestParam(name = "group_number", required = false) Long group_number,
                                    @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/students/" + id.toString());
        try {
            Student student = studentService.getStudentById(id);
            if (full_name != null && !full_name.equals("")) {
                student.setFull_name(full_name);
            }
            if (study_year != null) {
                student.setStudy_year(study_year);
            }
            if (group_number != null) {
                student.setGroup(Factory.getInstance().getStudyGroupDAO().getStudyGroupById(group_number));
            }
            studentService.updateStudent(student);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка редактирования студента");
            modelAndView.addObject("back", "/students/" + id.toString());
        }
        return modelAndView;
    }
}
