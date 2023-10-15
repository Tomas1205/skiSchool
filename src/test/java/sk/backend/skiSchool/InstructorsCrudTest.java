package sk.backend.skiSchool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import sk.backend.skiSchool.model.Instructors;

import static org.assertj.core.api.Assertions.assertThat;

import sk.backend.skiSchool.repository.InstructorsRepository;

@SpringBootTest
public class InstructorsCrudTest {

    @Autowired
    InstructorsRepository instructorsRepository;

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
        int sizeBeforeDelete = instructorsRepository.findAll().size();
        instructorsRepository.deleteById(instructorsRepository.findAll().get(0).getInstructor_id());

        assertThat(instructorsRepository.findAll())
            .hasSize(sizeBeforeDelete - 1); 
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
