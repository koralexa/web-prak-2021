package classes;

import javax.persistence.*;

import org.hibernate.annotations.Check;

import java.util.Set;

@Entity
@Table(name = "courses", schema="public")
public class Course {
    @Id
    @Column(name = "course_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;

    @Column(name = "course_name", nullable = false, length = 40)
    private String course_name;

    @Column(name = "coverage", nullable = false, length = 9)
    private String coverage;

    @Column(name = "intensity", nullable = false)
    @Check(constraints = "(intensity > 0) AND (intensity < 8)")
    private Long intensity;

    @Column(name = "study_year")
    @Check(constraints = "((study_year > 0) AND (study_year < 7)) OR (study_year IS NULL)")
    private Long study_year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher", nullable = false)
    private Teacher teacher;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Listener> listeners;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Lesson> lessons;

    public Course() {
    }

    public Course(Long course_id, String course_name, String coverage, Long intensity,
                  Long study_year, Teacher teacher, Set<Listener> listeners, Set<Lesson> lessons) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.coverage = coverage;
        this.intensity = intensity;
        this.study_year = study_year;
        this.teacher = teacher;
        this.listeners = listeners;
        this.lessons = lessons;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public Long getIntensity() {
        return intensity;
    }

    public void setIntensity(Long intensity) {
        this.intensity = intensity;
    }

    public Long getStudy_year() {
        return study_year;
    }

    public void setStudy_year(Long study_year) {
        this.study_year = study_year;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(Set<Listener> listeners) {
        this.listeners = listeners;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Course:" + "\n  course_id: " + course_id + "\n  course_name: " + course_name +
                "\n  coverage: " + coverage + "\n  intensity: " + intensity + "\n  study_year: " + study_year +
                "\n  teacher: " + teacher.getFull_name();
    }

    public boolean isEqual(Course c) {
        return ((course_id == c.getCourse_id()) && (course_name.equals(c.getCourse_name())) &&
                (coverage.equals(c.getCoverage())) && (intensity == c.getIntensity()) &&
                (study_year == c.getStudy_year()) && (teacher.getTeacher_id().equals(c.getTeacher().getTeacher_id())));
    }
}
