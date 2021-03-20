import java.io.*;
import classes.*;
import dao.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws SQLException {
        Collection students = Factory.getInstance().getStudentDAO().getAllStudents();
        Iterator iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println(student);
        }
    }
}