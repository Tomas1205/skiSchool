package sk.backend.skiSchool.model;


import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity @Table(name = "customers")
@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Customers {
    @Id
    @Column(name = "customer_id", updatable = false, nullable = false, columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customer_id;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Email
    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @OneToMany(mappedBy = "customers")
    private Set<Reservations> reservations;

    @Override
    public String toString() {
        return "Customer ID: " + customer_id + "\n" +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n";
    }

}