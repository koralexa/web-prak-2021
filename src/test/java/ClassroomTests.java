import classes.*;
import dao.Factory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ClassroomTests {
    @Test
    public void insertClassroom() throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setClassroom_number(30L);
        classroom.setCapacity(100L);

        Factory.getInstance().getClassroomDAO().insertClassroom(classroom);
        Classroom inserted_classroom = Factory.getInstance().getClassroomDAO().getClassroomById(classroom.getClassroom_number());
        Assert.assertTrue(classroom.isEqual(inserted_classroom), "TEST FAILED: insertClassroom: objects aren't equal");
        Factory.getInstance().getClassroomDAO().deleteClassroom(classroom);
    }

    @Test
    public void updateClassroom() throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setClassroom_number(30L);
        classroom.setCapacity(100L);

        Factory.getInstance().getClassroomDAO().insertClassroom(classroom);
        classroom.setCapacity(50L);
        Factory.getInstance().getClassroomDAO().updateClassroom(classroom);
        Classroom updated_classroom = Factory.getInstance().getClassroomDAO().getClassroomById(classroom.getClassroom_number());
        Assert.assertTrue(classroom.isEqual(updated_classroom), "TEST FAILED: updateClassroom: objects aren't equal");
        Factory.getInstance().getClassroomDAO().deleteClassroom(classroom);
    }

    @Test
    public void deleteClassroom() throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setClassroom_number(30L);
        classroom.setCapacity(100L);

        Factory.getInstance().getClassroomDAO().insertClassroom(classroom);
        Factory.getInstance().getClassroomDAO().deleteClassroom(classroom);
        Classroom deleted_classroom = Factory.getInstance().getClassroomDAO().getClassroomById(classroom.getClassroom_number());
        Assert.assertNull(deleted_classroom, "TEST FAILED: deleteClassroom: object != null");
    }

    @Test
    public void getAllClassrooms() throws SQLException {
        List<Classroom> classrooms = new ArrayList<Classroom>();
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(1L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(2L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(3L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(4L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(5L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(6L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(7L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(8L));

        Collection<Classroom> selected_classrooms = Factory.getInstance().getClassroomDAO().getAllClassrooms();
        for (Object obj : selected_classrooms) {
            boolean selected = false;
            for (Object c : classrooms) {
                if (((Classroom)obj).isEqual((Classroom)c)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getAllClassrooms: required object not found");
        }
        Assert.assertEquals(classrooms.size(), selected_classrooms.size(), "TEST FAILED: getAllClassrooms: wrong number of objects");
    }

    @Test
    public void getClassroomByMinCapacityFound() throws SQLException {
        List<Classroom> classrooms = new ArrayList<Classroom>();
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(1L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(2L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(3L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(5L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(7L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(8L));

        Collection<Classroom> selected_classrooms = Factory.getInstance().getClassroomDAO().getClassroomsByMinCapacity(30L);
        for (Object obj : selected_classrooms) {
            boolean selected = false;
            for (Object c : classrooms) {
                if (((Classroom)obj).isEqual((Classroom)c)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getClassroomsByMinCapacityFound: required object not found");
        }
        Assert.assertEquals(classrooms.size(), selected_classrooms.size(), "TEST FAILED: getClassroomsByMinCapacityFound: wrong number of objects");
    }

    @Test
    public void getClassroomsByMinCapacityNotFound() throws SQLException {
        Collection<Classroom> selected_classrooms = Factory.getInstance().getClassroomDAO().getClassroomsByMinCapacity(150L);
        Assert.assertEquals(selected_classrooms.size(), 0, "TEST FAILED: getClassroomsByMinCapacityNotFound: wrong number of objects");
    }

    @Test
    public void getClassroomByMaxCapacityFound() throws SQLException {
        List<Classroom> classrooms = new ArrayList<Classroom>();
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(1L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(4L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(5L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(6L));
        classrooms.add(Factory.getInstance().getClassroomDAO().getClassroomById(8L));
        Collection<Classroom> selected_classrooms = Factory.getInstance().getClassroomDAO().getClassroomsByMaxCapacity(35L);
        for (Object obj : selected_classrooms) {
            boolean selected = false;
            for (Object c : classrooms) {
                if (((Classroom)obj).isEqual((Classroom)c)) {
                    selected = true;
                    break;
                }
            }
            Assert.assertTrue(selected, "TEST FAILED: getClassroomsByMaxCapacityFound: required object not found");
        }
        Assert.assertEquals(classrooms.size(), selected_classrooms.size(), "TEST FAILED: getClassroomsByMaxCapacityFound: wrong number of objects");
    }

    @Test
    public void getClassroomsByMaxCapacityNotFound() throws SQLException {
        Collection<Classroom> selected_classrooms = Factory.getInstance().getClassroomDAO().getClassroomsByMaxCapacity(10L);
        Assert.assertEquals(selected_classrooms.size(), 0, "TEST FAILED: getClassroomsByMaxCapacityNotFound: wrong number of objects");
    }
}

