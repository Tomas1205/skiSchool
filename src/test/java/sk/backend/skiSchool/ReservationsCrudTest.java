package sk.backend.skiSchool;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import sk.backend.skiSchool.model.Reservations;
import sk.backend.skiSchool.repository.LessonsRepository;
import sk.backend.skiSchool.repository.ReservationsRepository;
import sk.backend.skiSchool.repository.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationsCrudTest {
    
    @Autowired
    ReservationsRepository reservationsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LessonsRepository lessonsRepository;

    @Test
    @Transactional
    void createReservation() {
        int sizeBeforeSave = reservationsRepository.findAll().size();
        UUID customerId = customerRepository.findAll().get(0).getCustomer_id();
        Long lessonId = lessonsRepository.findAll().get(0).getLesson_id();

        Reservations newReservations = Reservations.builder()
            .customer_id(customerId)
            .lesson_id(lessonId)
            .purchaseTime(ZonedDateTime.now())
            .status("active")
            .build();
        
        reservationsRepository.saveAndFlush(newReservations);

        assertThat(reservationsRepository.findAll())
            .hasSize(sizeBeforeSave + 1)
            .anyMatch(newReservation -> newReservation.getCustomer_id().equals(customerId) &&
                                        newReservation.getLesson_id().equals(lessonId) &&
                                        newReservation.getStatus().equals("active"));

    }
    
}
