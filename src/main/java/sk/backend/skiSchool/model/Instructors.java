package sk.backend.skiSchool.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor; 
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "instructors")
@Getter @Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Instructors {

    @Id
    @Column(name = "instructor_id", updatable=false, nullable=false, columnDefinition="uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID instructor_id;

    @Column(name = "firstname", nullable=false)
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name = "lastname", nullable=false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;
    
    @Column(name = "qualificationtype")
    private String qualificationType;

    @Column(name = "qualificationlevel")
    private int qualificationLevel;

    @ManyToMany(mappedBy = "instructors")
    private Set<Lessons> lessons;

    @Override
    public String toString() {
        return "Instructor ID: " + instructor_id + "\n" +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Qualification type: " + qualificationType + "\n" +
                "Qualification level: " + qualificationLevel + "\n";
    }
};
