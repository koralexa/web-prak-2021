package classes;

import javax.persistence.*;

@Entity
@Table(name = "listeners", schema="public",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"listener_type", "listener_num", "course"})})
public class Listener {
    @Id
    @Column(name = "listener_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listener_id;

    @Column(name = "listener_type", nullable = false, length = 7)
    private String listener_type;

    @Column(name = "listener_num", nullable = false)
    private Long listener_num;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course", nullable = false)
    private Course course;

    public Listener() {
    }

    public Listener(Long listener_id, String listener_type, Long listener_num, Course course) {
        this.listener_id = listener_id;
        this.listener_type = listener_type;
        this.listener_num = listener_num;
        this.course = course;
    }

    public Long getListener_id() {
        return listener_id;
    }

    public void setListener_id(Long listener_id) {
        this.listener_id = listener_id;
    }

    public String getListener_type() {
        return listener_type;
    }

    public void setListener_type(String listener_type) {
        this.listener_type = listener_type;
    }

    public Long getListener_num() {
        return listener_num;
    }

    public void setListener_num(Long listener_num) {
        this.listener_num = listener_num;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Listener:" + "\n  listener_id: " + listener_id + "\n  listener_type: " + listener_type +
                "\n  listener_num: " + listener_num + "\n  course: " + course.getCourse_name();
    }

    public boolean isEqual(Listener l) {
        return ((listener_id == l.getListener_id()) && (listener_type.equals(l.getListener_type())) &&
                (listener_num == l.getListener_num()) && (course.getCourse_id() == l.getCourse().getCourse_id()));
    }
}
