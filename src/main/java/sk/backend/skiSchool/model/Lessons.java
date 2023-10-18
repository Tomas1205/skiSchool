package sk.backend.skiSchool.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.ZonedDateTime;

import java.util.Set;

@Entity @Table(name = "lessons")
@AllArgsConstructor @NoArgsConstructor
@ToString @Getter @Setter
@Builder
public class Lessons {

    @Id
    @Column(name = "lesson_id", unique=true, updatable = false, nullable = false, columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lesson_id;

    @Column(name="lessontype", nullable = false)
    @NotBlank(message = "Lesson type is mandatory")
    private String lessonType;

    @Column(name="lessonlevel", nullable = false)
    @NotBlank(message = "Lesson level is mandatory")
    private String lessonLevel;

    @Column(name="starttime", nullable = false)
    @NotBlank(message = "Start time is mandatory")
    private ZonedDateTime startTime;

    @Column(name="endtime", nullable = false)
    @NotBlank(message = "End time is mandatory")
    private ZonedDateTime endTime;

    @Column(name="maxcapacity", nullable = false)
    @NotBlank(message = "Max capacity is mandatory")
    private int maxCapacity;

    @Column(name="price", nullable = false)
    @NotBlank(message = "Price is mandatory")
    private double price;

    @ManyToMany
    @JoinTable(name = "lessons_instructors",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private Set<Instructors> instructors;
    
}
