package sk.backend.skiSchool.services;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import sk.backend.skiSchool.model.Reservations;
import sk.backend.skiSchool.repository.LessonsRepository;
import sk.backend.skiSchool.repository.ReservationsRepository;

@Service
@Transactional
@Slf4j
public class ReservationsServices {
    @Autowired
    ReservationsRepository reservationsRepository;

    @Autowired
    LessonsRepository lessonsRepository;

    public boolean createReservation(UUID customersId, Long lessonId, String status, ZonedDateTime purchaseTime) {

        if (customersId == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (lessonId == null) {
            throw new IllegalArgumentException("Lesson ID cannot be null");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        if (lessonsRepository.findById(lessonId).isEmpty()) {
            throw new IllegalArgumentException("Lesson with ID " + lessonId + " does not exist");
        }
        //Check if lessons have sufficient capacity
        if (lessonsRepository.findById(lessonId).get().getMaxCapacity() <= 0) {
            throw new IllegalArgumentException("Lesson with ID " + lessonId + " is full");
        }

        if(purchaseTime == null) {
            purchaseTime = ZonedDateTime.now();
        }

        try {
            Reservations newReservation = Reservations.builder()
                .customer_id(customersId)
                .lesson_id(lessonId)
                .status(status)
                .purchaseTime(purchaseTime)
                .build();
            reservationsRepository.save(newReservation);
            return true;
        } catch (Exception e) {
            log.error("Error creating reservation: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelReservation(Long lessonId) {
          if (lessonId == null) {
            throw new IllegalArgumentException("Lesson ID cannot be null");
        }
        if (lessonsRepository.findById(lessonId).isEmpty()) {
            throw new IllegalArgumentException("Lesson with ID " + lessonId + " does not exist");
        }

        try {
            Reservations reservation = reservationsRepository.findById(lessonId).get();
            if(reservation.getStatus().equals("active")) {
                reservation.setStatus("canceled");
                reservationsRepository.save(reservation);
            } 
            else {
                return false;
            }
            
            
        } catch (Exception e) {
            log.error("Error updating reservation status: " + e.getMessage());
            return false;
        }
        
        return true;
    }
    
}
