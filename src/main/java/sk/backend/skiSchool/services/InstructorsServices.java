package sk.backend.skiSchool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import sk.backend.skiSchool.model.Instructors;
import sk.backend.skiSchool.repository.InstructorsRepository;

@Service
@Transactional
@Slf4j
public class InstructorsServices {
    @Autowired
    private InstructorsRepository instructorsRepository;

    public boolean createInstructor(Instructors instructors) {

        if (instructors == null) {
            throw new IllegalArgumentException("Instructor cannot be null");
        }
        if (instructors.getFirstName() == null || instructors.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Instructor first name cannot be null or empty");
        }
        if (instructors.getLastName() == null || instructors.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Instructor last name cannot be null or empty");
        }
        if ((instructors.getEmail() == null || instructors.getEmail().isEmpty()) && (instructors.getPhone() == null || instructors.getPhone().isEmpty())) {
            throw new IllegalArgumentException("Instructor email cannot be null or empty");
        }
        if (instructors.getQualificationType() == null || instructors.getQualificationType().isEmpty()) {
            throw new IllegalArgumentException("Instructor qualification type cannot be null or empty");
        }
        if (instructors.getQualificationLevel() < 1 || instructors.getQualificationLevel() > 3) {
            throw new IllegalArgumentException("Instructor qualification level cannot be null or empty");
        }

        try {
            instructorsRepository.save(instructors);
            
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Instructor haven't been saved", e);
            return false;
        }

        return true;
    }    
}
