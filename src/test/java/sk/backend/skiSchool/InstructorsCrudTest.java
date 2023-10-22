package sk.backend.skiSchool;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.transaction.Transactional;
import sk.backend.skiSchool.model.Instructors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.UUID;

import sk.backend.skiSchool.repository.InstructorsRepository;
import sk.backend.skiSchool.repository.LessonsRepository;


@SpringBootTest
public class InstructorsCrudTest {

    @Autowired
    InstructorsRepository instructorsRepository;

    @Autowired
    LessonsRepository lessonsRepository;

    @Test
    @Transactional
    void createInstructorSuccess() {
        // create instructor and test if it was saved to database
        int sizeBeforeSave = instructorsRepository.findAll().size();
        instructorsRepository.save(Instructors.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john@doe.com")
            .phone("+421904555")
            .qualificationType("ski")
            .qualificationLevel(1)
            .build());

            assertThat(instructorsRepository.findAll())
                .hasSize(sizeBeforeSave + 1)
                .anyMatch(newInstructor -> newInstructor.getFirstName().equals("John") &&
                                            newInstructor.getLastName().equals("Doe") &&
                                            newInstructor.getEmail().equals("john@doe.com") &&
                                            newInstructor.getPhone().equals("+421904555") &&
                                            newInstructor.getQualificationType().equals("ski") &&
                                            newInstructor.getQualificationLevel() == 1);
            }

    @Test
    @Transactional
    void deleteInstructorSuccess() {
        // delete instructor and test if it was deleted from database
        UUID instructorId = instructorsRepository.findAll().get(0).getInstructor_id();

        instructorsRepository.deleteById(instructorId);
        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> instructorsRepository.flush());

       assertTrue(exception.getCause() instanceof ConstraintViolationException);      

    }

    @Test
    @Transactional
    void updateInstructorSuccess() {
        // update instructor and test if it was updated in database
        Instructors instructor = instructorsRepository.findAll().get(0);
        instructor.setFirstName("Tomas");
        instructor.setLastName("Penxa");
        instructor.setEmail("email@email.com");

        instructorsRepository.save(instructor);


        assertThat(instructorsRepository.findAll())
            .anyMatch(updatedInstructor -> updatedInstructor.getFirstName().equals(instructor.getFirstName()) &&
                                            updatedInstructor.getLastName().equals(instructor.getLastName()) &&
                                            updatedInstructor.getEmail().equals(instructor.getEmail()));
    }
    
}
