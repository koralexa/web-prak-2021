package classes;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers", schema="public")
public class Teacher {
    @Id
    @Column(name = "teacher_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacher_id;

    @Column(name = "full_name", nullable = false, length = 60)
    private String full_name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Teacher() {
    }

    public Teacher(Long teacher_id, String full_name, Set<Course> courses) {
        this.teacher_id = teacher_id;
        this.full_name = full_name;
        this.courses = courses;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher:" + "\n  teacher_id: " + teacher_id + "\n  full_name: " + full_name;
    }

    public boolean isEqual(Teacher t) {
        return ((teacher_id == t.getTeacher_id()) && (full_name.equals(t.getFull_name())));
    }
}
