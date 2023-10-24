package sk.backend.skiSchool.services;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import sk.backend.skiSchool.model.Reservations;
import sk.backend.skiSchool.repository.CustomerRepository;
import sk.backend.skiSchool.repository.LessonsRepository;
import sk.backend.skiSchool.repository.ReservationsRepository;
import sk.backend.skiSchool.services.messages.EventMessage;

@Service
@Transactional
@Slf4j
public class ReservationsServices {
    @Autowired
    ReservationsRepository reservationsRepository;

    @Autowired
    LessonsRepository lessonsRepository;

    @Autowired
    CustomerRepository customersRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

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

    public boolean cancelReservation(Long reservationId) {
          if (reservationId == null) {
            throw new IllegalArgumentException("Lesson ID cannot be null");
        }
        if (reservationsRepository.findById(reservationId).isEmpty()) {
            throw new IllegalArgumentException("Reservation with ID " + reservationId + " does not exist");
        }

        try {
            Reservations reservation = reservationsRepository.findById(reservationId).get();
            log.info("Starting reservation cancelation");


            if (reservation.getStatus().equals("active")) {
                log.info("Reservation status: " + reservation.getStatus());
                log.info("Reservation was canceled: " + reservation.getStatus().equals("active"));
                reservation.setStatus("canceled");
                reservationsRepository.save(reservation);
            } 
            else {
                log.info("Enterin false");
                return false;
            }            
            
        } catch (Exception e) {
            log.error("Error updating reservation status: " + e.getMessage());
            return false;
        }    

        try {
            Long lessonId = reservationsRepository.findById(reservationId).get().getLesson_id();
            String lessonDate = lessonsRepository.findById(lessonId).get().getStartTime().toString();
            String msg = String.format("Reservation at date %s was canceled", lessonDate);
            String customersEmail = customersRepository.findById(reservationsRepository.findById(reservationId).get().getCustomer_id()).get().getEmail();
            log.info("Sending email to: " + customersEmail);

            if(customersEmail != null && !customersEmail.isEmpty()) {
                EventMessage event = new EventMessage(this, msg, customersEmail);
                applicationEventPublisher.publishEvent(event);
            }       
            
        } catch(Exception e) {
            log.error("Error sending email: " + e.getMessage());
        }
        return true;
    }
    
}
