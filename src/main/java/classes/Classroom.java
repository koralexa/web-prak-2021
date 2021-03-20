package classes;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classrooms", schema="public")
public class Classroom {
    @Id
    @Column(name = "classroom_number", nullable = false)
    private Long classroom_number;

    @Column(name = "capacity", nullable = false)
    @Check(constraints = "capacity > 0")
    private Long capacity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom", cascade = CascadeType.ALL)
    private Set<Lesson> lessons;

    public Classroom() {
    }

    public Classroom(Long classroom_number, Long capacity, Set<Lesson> lessons) {
        this.classroom_number = classroom_number;
        this.capacity = capacity;
        this.lessons = lessons;
    }

    public Long getClassroom_number() {
        return classroom_number;
    }

    public void setClassroom_number(Long classroom_number) {
        this.classroom_number = classroom_number;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Classroom:" + "\n  classroom_number: " + classroom_number + "\n  capacity: " + capacity;
    }

    public boolean isEqual(Classroom c) {
        return ((classroom_number == c.getClassroom_number()) && (capacity == c.getCapacity()));
    }
}
