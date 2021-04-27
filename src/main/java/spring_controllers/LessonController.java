package spring_controllers;

import classes.*;
import dao.Factory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.Impl.LessonServiceImpl;
import services.LessonService;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

@RestController
public class LessonController {
    private final LessonService lessonService = new LessonServiceImpl();

    @RequestMapping(value = "/lessons", method = RequestMethod.POST)
    public ModelAndView allLessons(@RequestParam(name = "teacher_id", required = false) Long teacher_id,
                                   @RequestParam(name = "student_id", required = false) Long student_id,
                                   @RequestParam(name = "classroom_id", required = false) Long classroom_id,
                                   @RequestParam(name = "day", required = false) Long day) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lessons");
        try {
            Collection<Lesson> lessons_by_teacher = teacher_id == null
                    ? lessonService.getAllLessons()
                    : lessonService.getLessonsByTeacher(Factory.getInstance().getTeacherDAO().getTeacherById(teacher_id));
            Collection<Lesson> lessons_by_student = student_id == null
                    ? lessonService.getAllLessons()
                    : lessonService.getLessonsByStudent(Factory.getInstance().getStudentDAO().getStudentById(student_id));
            Collection<Lesson> lessons_by_classroom = classroom_id == null
                    ? lessonService.getAllLessons()
                    : lessonService.getLessonsByClassroom(Factory.getInstance().getClassroomDAO().getClassroomById(classroom_id));
            Collection<Lesson> lessons_by_day = day == null
                    ? lessonService.getAllLessons()
                    : lessonService.getLessonsByDay(day);
            if (lessons_by_teacher.isEmpty() || lessons_by_student.isEmpty()
                || lessons_by_classroom.isEmpty() || lessons_by_day.isEmpty()) {
                modelAndView.addObject("lessonsList", null);
            } else {
                Collection<Lesson> lessons = new java.util.ArrayList<>(Collections.emptyList());
                for (Lesson l : lessons_by_teacher) {
                    boolean in_student = false;
                    boolean in_classroom = false;
                    boolean in_day = false;
                    for (Lesson l1 : lessons_by_student) {
                        if (l1.isEqual(l)) {
                            in_student = true;
                            break;
                        }
                    }
                    for (Lesson l1 : lessons_by_classroom) {
                        if (l1.isEqual(l)) {
                            in_classroom = true;
                            break;
                        }
                    }
                    for (Lesson l1 : lessons_by_day) {
                        if (l1.isEqual(l)) {
                            in_day = true;
                            break;
                        }
                    }
                    if (in_student && in_classroom && in_day) {
                        lessons.add(l);
                    }
                }
                modelAndView.addObject("lessonsList", lessons);
            }
            Collection<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
            modelAndView.addObject("teachers", teachers);
            Collection<Student> students = Factory.getInstance().getStudentDAO().getAllStudents();
            modelAndView.addObject("students", students);
            Collection<Classroom> classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
            modelAndView.addObject("classrooms", classrooms);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Некорректные значения фильтров");
            modelAndView.addObject("back", "/lessons");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/lessons", method = RequestMethod.GET)
    public ModelAndView allLessons() throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Lesson> lessons = lessonService.getAllLessons();
        modelAndView.setViewName("lessons");
        Collection<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
        modelAndView.addObject("teachers", teachers);
        Collection<Student> students = Factory.getInstance().getStudentDAO().getAllStudents();
        modelAndView.addObject("students", students);
        Collection<Classroom> classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
        modelAndView.addObject("classrooms", classrooms);
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }

    @RequestMapping(value = "/lessons/addLesson", method = RequestMethod.GET)
    public ModelAndView addLessonPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addLesson");
        try {
            Collection<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
            modelAndView.addObject("teachers", teachers);
            Collection<Student> students = Factory.getInstance().getStudentDAO().getAllStudents();
            modelAndView.addObject("students", students);
            Collection<Classroom> classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
            modelAndView.addObject("classrooms", classrooms);
            Collection<Course> courses = Factory.getInstance().getCourseDAO().getAllCourses();
            modelAndView.addObject("courses", courses);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления занятия");
            modelAndView.addObject("back", "/lessons");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/lessons/addLesson", method = RequestMethod.POST)
    public ModelAndView addLesson(@RequestParam(name = "course_id", required = false) Long course_id,
                                  @RequestParam(name = "classroom_id", required = false) Long classroom_id,
                                  @RequestParam(name = "day", required = false) Long day,
                                  @RequestParam(name = "time", required = false) String time_s) {
        Lesson lesson = new Lesson();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lessons");
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = time_s == null || time_s.equals("") ? null : LocalTime.parse(time_s, dtf);
            lesson.setCourse(Factory.getInstance().getCourseDAO().getCourseById(course_id));
            lesson.setClassroom(Factory.getInstance().getClassroomDAO().getClassroomById(classroom_id));
            lesson.setWeek_day(day);
            lesson.setLesson_time(time);
            lessonService.insertLesson(lesson);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка добавления занятия");
            modelAndView.addObject("back", "/lessons");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/lessons/addLessonError", method = RequestMethod.GET)
    public ModelAndView addLessonErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", "Ошибка добавления занятия");
        modelAndView.addObject("back", "/lessons");
        return modelAndView;
    }

    @RequestMapping(value = "/lessons/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lessonInfo");
        try {
            Lesson lesson = lessonService.getLessonById(id);
            modelAndView.addObject("lesson", lesson);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/lessons");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/lessons/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteLesson(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lessons");
        try {
            Lesson lesson = lessonService.getLessonById(id);
            lessonService.deleteLesson(lesson);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка удаления занятия");
            modelAndView.addObject("back", "/lessons/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/lessons/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editLessonPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editLesson");
        try {
            Lesson lesson = lessonService.getLessonById(id);
            modelAndView.addObject("lesson", lesson);
            Collection<Classroom> classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
            modelAndView.addObject("classrooms", classrooms);
            Collection<Course> courses = Factory.getInstance().getCourseDAO().getAllCourses();
            modelAndView.addObject("courses", courses);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/lessons/" + id.toString());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/lessons/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editLesson(@RequestParam(name = "course_id", required = false) Long course_id,
                                   @RequestParam(name = "classroom_id", required = false) Long classroom_id,
                                   @RequestParam(name = "day", required = false) Long day,
                                   @RequestParam(name = "time", required = false) String time_s,
                                   @PathVariable("id") Long id) throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lessons/" + id.toString());
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = time_s == null || time_s.equals("") ? null : LocalTime.parse(time_s, dtf);
            Lesson lesson = Factory.getInstance().getLessonDAO().getLessonById(id);
            if (course_id != null) {
                lesson.setCourse(Factory.getInstance().getCourseDAO().getCourseById(course_id));
            }
            if (classroom_id != null) {
                lesson.setClassroom(Factory.getInstance().getClassroomDAO().getClassroomById(classroom_id));
            }
            if (day != null) {
                lesson.setWeek_day(day);
            }
            if (time != null) {
                lesson.setLesson_time(time);
            } else {
                throw new Exception();
            }
            lessonService.updateLesson(lesson);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Ошибка редактирования занятия");
            modelAndView.addObject("back", "/lessons/" + id.toString());
        }
        return modelAndView;
    }
}
