package sk.backend.skiSchool;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import sk.backend.skiSchool.model.Lessons;
import sk.backend.skiSchool.repository.LessonsRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LessonsCrudTest {

    @Autowired
    LessonsRepository lessonsRepository;

    @Test
    @Transactional
    void createLessonSuccess() {
        int sizeBeforeSave = lessonsRepository.findAll().size();

        lessonsRepository.save(
            Lessons.builder()
                .lessonType("ski")
                .lessonLevel(1)
                .startTime(ZonedDateTime.of(2023, 10, 18, 9, 0, 0, 0, ZoneId.of("Europe/Bratislava")))
                .endTime(ZonedDateTime.of(2023, 10, 18, 10, 0, 0, 0, ZoneId.of("Europe/Bratislava")))
                .maxCapacity(60)
                .price(20.0)
                .build());

            assertThat(lessonsRepository.findAll())
                .hasSize(sizeBeforeSave + 1)
                .anyMatch(newLesson -> newLesson.getLessonType().equals("ski") &&
                                            newLesson.getLessonLevel() == 1 &&
                                            newLesson.getStartTime().equals(ZonedDateTime.of(2023, 10, 18, 9, 0, 0, 0, ZoneId.of("Europe/Bratislava")) )&&
                                            newLesson.getEndTime().equals(ZonedDateTime.of(2023, 10, 18, 10, 0, 0, 0, ZoneId.of("Europe/Bratislava"))) &&
                                            newLesson.getMaxCapacity() == 60 &&
                                            newLesson.getPrice() == 20);
            }

    @Test
    @Transactional
    void getAllLessonsSuccess() {
        // get all lessons and test if they are returned
        assertThat(lessonsRepository.findAll())
            .hasSize(lessonsRepository.findAll().size());
    }

    @Test
    @Transactional
    void deleteLessonSuccess() {
        // delete lesson and test if it was deleted from database
        int sizeBeforeDelete = lessonsRepository.findAll().size();
        
        lessonsRepository.deleteById(lessonsRepository.findAll().get(0).getLesson_id());

        assertThat(lessonsRepository.findAll())
            .hasSize(sizeBeforeDelete - 1);     
    }
}

    
    

