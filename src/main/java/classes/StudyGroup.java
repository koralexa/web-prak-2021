package classes;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "study_groups", schema="public")
public class StudyGroup {
    @Id
    @Column(name = "group_number", nullable = false)
    @Check(constraints = "group_number > 0")
    private Long group_number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stream", nullable = false)
    private Stream stream;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Student> students;

    public StudyGroup() {
    }

    public StudyGroup(Long group_number, Stream stream, Set<Student> students) {
        this.group_number = group_number;
        this.stream = stream;
        this.students = students;
    }

    public Long getGroup_number() {
        return group_number;
    }

    public void setGroup_number(Long group_number) {
        this.group_number = group_number;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
