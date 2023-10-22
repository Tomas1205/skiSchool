package sk.backend.skiSchool.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import sk.backend.skiSchool.model.Customers;
import sk.backend.skiSchool.services.CustomerServices;

@RestController
@RequestMapping(value = "/api/v1/customers")
@Slf4j
public class CustomersControler {

    @Autowired
    private CustomerServices customerServices;

    @PostMapping(value = "/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestParam String firstName, @RequestParam String lastName, @RequestParam @Email(message = "Email not valid") String email, @RequestParam String phone) {
        
        log.info("Creating customer with first name: " + firstName + ", last name: " + lastName + ", email: " + email + ", phone: " + phone);
        
        if (firstName == null || firstName.isEmpty()) {
            log.info("Missing first name" + firstName);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing first name");
        }
        if (lastName == null || lastName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing last name");
        }
        if ((email == null || email.isEmpty()) && (phone == null || phone.isEmpty())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing email or phone");
        }

        try {
            customerServices.createCustomer(Customers.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .build());
        } catch (Exception e) {
            log.info("Error while creating customer. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating customer. Error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Customer created");   
    }
    
}
