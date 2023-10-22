package sk.backend.skiSchool.controler;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sk.backend.skiSchool.model.Instructors;
import sk.backend.skiSchool.services.InstructorsServices;

@RestController
@RequestMapping(value = "/api/v1/instructors")
@Slf4j
public class InstructorsControler {

    @Autowired
    private InstructorsServices instructorsServices;

    @PostMapping(value = "/createInstructor")
    public ResponseEntity<String> createInstructor(
        @RequestParam String firstName, 
        @RequestParam String lastName,
        @RequestParam String email, 
        @RequestParam String phone, 
        @RequestParam String qualificationType,
        @RequestParam int qualificationLevel) {
            log.info("Creating instructor with first name: " + firstName + ", last name: " + lastName + ", email: " + email + ", phone: " + phone + ", qualification type: " + qualificationType + ", qualification level: " + qualificationLevel);

        try {
            Instructors newInstructor = Instructors.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .qualificationType(qualificationType)
                .qualificationLevel(qualificationLevel)
                .build();

            if (instructorsServices.createInstructor(newInstructor)) {
                return ResponseEntity.status(Response.SC_OK).body("Instructor created");
            } else {
                return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body("Error while creating instructor");
            }
            } 
            catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body("Error while creating instructor. Error: " + e.getMessage());
        } 
    }
}
