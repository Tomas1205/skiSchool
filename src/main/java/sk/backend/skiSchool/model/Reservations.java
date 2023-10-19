package sk.backend.skiSchool.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "reservations")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Reservations {
    @Id
    @Column(name="reservation_id", unique = true, updatable = false, nullable = false, columnDefinition = "long")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    @Column(name="customer_id", nullable = false)
    @NotBlank(message = "Customer ID is mandatory")
    private UUID customer_id;

    @Column(name="lesson_id", nullable = false)
    @NotBlank(message = "Lesson ID is mandatory")
    private Long lesson_id;

    @Column(name = "purchasetime", nullable = false)
    private ZonedDateTime purchaseTime;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "Status is mandatory")
    private String status; 

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    Customers customers;

    @ManyToOne
    @JoinColumn(name = "lesson_id", insertable = false, updatable = false)
    Lessons lessons;
    
    @Override
    public String toString() {
        return "Reservation ID: " + reservation_id + "\n" +
                "Customer ID: " + customer_id + "\n" +
                "Lesson ID: " + lesson_id + "\n" +
                "Purchase time: " + purchaseTime + "\n" +
                "Status: " + status + "\n";
    }
    
}
