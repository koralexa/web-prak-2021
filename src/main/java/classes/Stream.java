package classes;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "streams", schema="public")
public class Stream {
    @Id
    @Column(name = "stream_number", nullable = false)
    @Check(constraints = "stream_number > 0")
    private Long stream_number;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stream", cascade = CascadeType.ALL)
    private Set<StudyGroup> groups;

    public Stream() {
    }

    public Stream(Long stream_number, Set<StudyGroup> groups) {
        this.stream_number = stream_number;
        this.groups = groups;
    }

    public Long getStream_number() {
        return stream_number;
    }

    public void setStream_number(Long stream_number) {
        this.stream_number = stream_number;
    }

    public Set<StudyGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<StudyGroup> groups) {
        this.groups = groups;
    }
}
