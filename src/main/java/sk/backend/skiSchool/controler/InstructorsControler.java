package sk.backend.skiSchool.controler;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sk.backend.skiSchool.model.Instructors;
import sk.backend.skiSchool.services.InstructorsServices;

@RestController
@RequestMapping(value = "/api/v1/instructors")
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

        if (firstName == null || firstName.isEmpty()) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Missing first name");
        }
        if (lastName == null || lastName.isEmpty()) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Missing last name");
        }
        if ((email == null || email.isEmpty()) && (phone == null || phone.isEmpty())) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Missing email or phone");
        }
        if (qualificationType == null || qualificationType.isEmpty()) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Missing qualification type");
        }
        if (qualificationLevel < 1 || qualificationLevel > 3) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Missing qualification level");
        }

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
