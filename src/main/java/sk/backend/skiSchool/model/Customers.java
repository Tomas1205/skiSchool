package sk.backend.skiSchool.model;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
@ToString
@Getter
@Setter

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

}