package classes;

import org.hibernate.annotations.Check;

import javax.persistence.*;

@Entity
@Table(name = "passed_courses", schema="public",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"student", "course"})})
public class PassedCourse {
    @Id
    @Column(name = "passed_course_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passed_course_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course", nullable = false)
    private Course course;

    @Column(name = "study_year", nullable = false)
    @Check(constraints = "(study_year > 0) AND (study_year < 7)")
    private Long study_year;

    public PassedCourse() {
    }

    public PassedCourse(Long passed_course_id, Student student, Course course, Long study_year) {
        this.passed_course_id = passed_course_id;
        this.student = student;
        this.course = course;
        this.study_year = study_year;
    }

    public Long getPassed_course_id() {
        return passed_course_id;
    }

    public void setPassed_course_id(Long passed_course_id) {
        this.passed_course_id = passed_course_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getStudy_year() {
        return study_year;
    }

    public void setStudy_year(Long study_year) {
        this.study_year = study_year;
    }

    public boolean isEqual(PassedCourse p) {
        return ((passed_course_id == p.getPassed_course_id()) && (student.getStudent_id() == p.getStudent().getStudent_id()) &&
                (course.getCourse_id() == p.getCourse().getCourse_id()) && (study_year == p.getStudy_year()));
    }
}
