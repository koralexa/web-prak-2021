package classes;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "lessons", schema="public",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"classroom", "week_day", "lesson_time"}),
                            @UniqueConstraint(columnNames = {"course", "week_day", "lesson_time"})})
public class Lesson {
    @Id
    @Column(name = "lesson_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lesson_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom", nullable = false)
    private Classroom classroom;

    @Column(name = "week_day", nullable = false)
    @Check(constraints = "((week_day > 0) AND (week_day < 8))")
    private Long week_day;

    @Column(name = "lesson_time", nullable = false)
    private LocalTime lesson_time;

    public Lesson() {
    }

    public Lesson(Long lesson_id, Course course,
                  Classroom classroom, Long week_day, LocalTime lesson_time) {
        this.lesson_id = lesson_id;
        this.course = course;
        this.classroom = classroom;
        this.week_day = week_day;
        this.lesson_time = lesson_time;
    }

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Long getWeek_day() {
        return week_day;
    }

    public void setWeek_day(Long week_day) {
        this.week_day = week_day;
    }

    public LocalTime getLesson_time() {
        return lesson_time;
    }

    public void setLesson_time(LocalTime lesson_time) {
        this.lesson_time = lesson_time;
    }

    @Override
    public String toString() {
        return "Lesson:" + "\n  lesson_id: " + lesson_id + "\n  course: " + course.getCourse_name() +
                "\n  classroom: " + classroom.getClassroom_number() + "\n  week_day: " + week_day +
                "\n  lesson_time: " + lesson_time;
    }

    public boolean isEqual(Lesson l) {
        return ((lesson_id == l.getLesson_id()) && (course.getCourse_id() == l.getCourse().getCourse_id()) &&
                (classroom.getClassroom_number() == l.getClassroom().getClassroom_number()) &&
                (week_day.equals(l.getWeek_day())) && (lesson_time.equals(l.getLesson_time())));
    }
}
