package sk.backend.skiSchool.controler;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sk.backend.skiSchool.services.ReservationsServices;

@RestController
@RequestMapping(value = "/api/v1/reservations")
@Slf4j
public class ReservationsControler {

    @Autowired
    ReservationsServices reservationsServices;

    @PostMapping(value = "/createReservation")
    public ResponseEntity<String> createReservation(
        @RequestParam UUID customersId, 
        @RequestParam Long lessonId, 
        @RequestParam String status,
        @RequestParam(required = false) ZonedDateTime purchaseTime) {
        
        log.info("Creating reservation with customers ID: " + customersId + ", lesson ID: " + lessonId + ", status: " + status);
        
        try {
            reservationsServices.createReservation(customersId, lessonId, status, purchaseTime);
        } catch (Exception e) {
            log.info("Error while creating reservation. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating reservation. Error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Reservation created");   
    }

    @PostMapping(value = "/cancelReservation/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId) {
        log.info("Cancelling reservation with ID: " + reservationId);
        try {
            boolean wasCanceled = reservationsServices.cancelReservation(reservationId);
            if (wasCanceled){
                return ResponseEntity.status(HttpStatus.OK).body("Reservation cancelled");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Reservation was already cancelled");
            }

        } catch (Exception e) {
            log.info("Error while cancelling reservation. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while cancelling reservation. Error: " + e.getMessage());
        }  
    }
    
}
