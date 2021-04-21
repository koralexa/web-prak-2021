package spring_controllers;

import classes.Classroom;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ClassroomService;
import services.Impl.ClassroomServiceImpl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

@RestController
public class ClassroomController {
    private final ClassroomService classroomService = new ClassroomServiceImpl();

    @RequestMapping(value = "/classrooms", method = RequestMethod.POST)
    public ModelAndView allClassrooms(@RequestParam(name = "min", required = false) Long min,
                                      @RequestParam(name = "max", required = false) Long max) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("classrooms");
        try {
            Collection<Classroom> classrooms_by_min_capacity = min == null
                    ? classroomService.getAllClassrooms()
                    : classroomService.getClassroomsByMinCapacity(min);
            Collection<Classroom> classrooms_by_max_capacity = max == null
                    ? classroomService.getAllClassrooms()
                    : classroomService.getClassroomsByMaxCapacity(max);
            if (classrooms_by_min_capacity.isEmpty() || classrooms_by_max_capacity.isEmpty()) {
                modelAndView.addObject("classroomsList", null);
            } else {
                Collection<Classroom> classrooms = new java.util.ArrayList<>(Collections.emptyList());
                for (Classroom c : classrooms_by_min_capacity) {
                    boolean in_max_capacity = false;
                    for (Classroom c1 : classrooms_by_max_capacity) {
                        if (c1.isEqual(c)) {
                            in_max_capacity = true;
                            break;
                        }
                    }
                    if (in_max_capacity) {
                        classrooms.add(c);
                    }
                }
                modelAndView.addObject("classroomsList", classrooms);
            }
        } catch (Exception e) {
            modelAndView.setViewName("classroomsFilterError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/classrooms", method = RequestMethod.GET)
    public ModelAndView allClassrooms() throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Classroom> classrooms = classroomService.getAllClassrooms();
        modelAndView.setViewName("classrooms");
        modelAndView.addObject("classroomsList", classrooms);
        return modelAndView;
    }

    @RequestMapping(value = "/classrooms/addClassroom", method = RequestMethod.GET)
    public ModelAndView addClassroomPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addClassroom");
        return modelAndView;
    }

    @RequestMapping(value = "/classrooms/addClassroom", method = RequestMethod.POST)
    public ModelAndView addClassroom(@RequestParam(name = "classroom_number", required = false) Long classroom_number,
                                     @RequestParam(name = "capacity", required = false) Long capacity) {
        Classroom classroom = new Classroom();
        classroom.setClassroom_number(classroom_number);
        classroom.setCapacity(capacity);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/classrooms");
        try {
            classroomService.insertClassroom(classroom);
        } catch (Exception e) {
            modelAndView.setViewName("addClassroomError");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/classrooms/addClassroomError", method = RequestMethod.GET)
    public ModelAndView addClassroomErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addClassroomError");
        return modelAndView;
    }
}
