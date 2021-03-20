package classes;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students", schema = "public")
public class Student {
    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @Column(name = "full_name", nullable = false, length = 60)
    private String full_name;

    @Column(name = "study_year")
    @Check(constraints = "((study_year > 0) AND (study_year < 7)) OR (study_year IS NULL)")
    private Long study_year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_number", nullable = false)
    private StudyGroup group;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL)
    private Set<PassedCourse> courses;

    public Student() {
    }

    public Student(Long student_id, String full_name, Long study_year,
                   StudyGroup group, Set<PassedCourse> courses) {
        this.student_id = student_id;
        this.full_name = full_name;
        this.study_year = study_year;
        this.group = group;
        this.courses = courses;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Long getStudy_year() {
        return study_year;
    }

    public void setStudy_year(Long study_year) {
        this.study_year = study_year;
    }

    public StudyGroup getGroup() {
        return group;
    }

    public void setGroup(StudyGroup group) {
        this.group = group;
    }

    public Set<PassedCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<PassedCourse> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student:" + "\n  student_id: " + student_id + "\n  full_name: " + full_name +
                "\n  study_year: " + study_year + "\n  group_number: " + group.getGroup_number();
    }

    public boolean isEqual(Student s) {
        return ((student_id == s.getStudent_id()) && (full_name.equals(s.getFull_name())) &&
                (study_year == s.getStudy_year()) && (group.getGroup_number().equals(s.getGroup().getGroup_number())) &&
                (group.getStream().getStream_number().equals(s.getGroup().getStream().getStream_number())));
    }
}
