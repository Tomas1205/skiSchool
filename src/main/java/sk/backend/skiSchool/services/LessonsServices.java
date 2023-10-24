package sk.backend.skiSchool.services;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import sk.backend.skiSchool.model.Instructors;
import sk.backend.skiSchool.model.Lessons;
import sk.backend.skiSchool.repository.InstructorsRepository;
import sk.backend.skiSchool.repository.LessonsRepository;

@Service
@Transactional
@Slf4j
public class LessonsServices {

    @Autowired
    private LessonsRepository lessonsRepository;

    @Autowired
    private InstructorsRepository instructorsRepository;

    public boolean createLesson(UUID instructor_id, String lessonType, int lessonLevel, int capacity, int price, ZonedDateTime startTime, ZonedDateTime endTime){
        if (instructor_id == null) {
            throw new IllegalArgumentException("Missing instructor");
        }
        if (lessonType == null || lessonType.isEmpty()) {
            throw new IllegalArgumentException("Missing lesson type");
        }
        if (lessonLevel < 1 || lessonLevel > 3) {
            throw new IllegalArgumentException("Missing lesson level");
        }
        if (capacity < 1) {
            throw new IllegalArgumentException("Missing capacity");
        }
        if (price < 1) {
            throw new IllegalArgumentException("Missing price");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("Missing start time");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("Missing end time");
        }

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time is after end time");
        }
        
        log.info("Instructor id: " + instructor_id);

        Set<Lessons> instructorLessons = lessonsRepository.findAllByInstructorId(instructor_id);
        Optional<Instructors> instructor = instructorsRepository.findById(instructor_id);       
        

        
        for (Lessons lesson : instructorLessons) {
            log.info("Lesson: " + lesson);
            log.info("Start time of requested lesson: " + startTime);
            log.info("Lesson start time: " + lesson.getStartTime());
            log.info("End time of requested lesson: " + endTime);
            log.info("Lesson end time: " + lesson.getEndTime());
            log.info("Start lessons checking");

            if (lessonOverlap(startTime, endTime, lesson.getStartTime(), lesson.getEndTime())) {
                log.info("Lesson overlaps with another lesson");
                throw new IllegalArgumentException("Lesson overlaps with another lesson");
            }
        }       
               

        try {
            log.info("Creating lesson");
            Lessons newLesson = Lessons.builder()
                .lessonType(lessonType)
                .lessonLevel(lessonLevel)
                .maxCapacity(capacity)
                .price(price)
                .startTime(startTime)
                .endTime(endTime)
                .instructors(Set.of(instructor.get()))
                .build();

            lessonsRepository.save(newLesson);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error creating lesson: " + e.getMessage());
            throw new IllegalArgumentException("Error creating lesson");
        }

        return true;
    }

    public List<Lessons> getAllLessons() {
        return lessonsRepository.findAll();
    }

    private boolean lessonOverlap(ZonedDateTime startTime, ZonedDateTime endTime, ZonedDateTime otherStartTime, ZonedDateTime otherEndTime) {
        boolean isOverlap = (startTime.isBefore(otherEndTime) || startTime.isEqual(otherEndTime)) &&
                            (endTime.isAfter(otherStartTime) || endTime.isEqual(otherStartTime));
        log.info("Is overlap: " + isOverlap);    
        return isOverlap;
    }
    
    
}
