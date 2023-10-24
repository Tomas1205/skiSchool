package sk.backend.skiSchool.controler;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sk.backend.skiSchool.services.LessonsServices;

@RestController
@RequestMapping(value = "/api/v1/lessons")
public class LessonsControler {
    @Autowired
    private LessonsServices lessonsServices;

    @PostMapping(value = "/createLesson")
    public ResponseEntity<String> createLesson(@RequestParam UUID instructor, @RequestParam String lessonType, @RequestParam int lessonLevel, @RequestParam int capacity, @RequestParam int price, @RequestParam ZonedDateTime startTime, @RequestParam ZonedDateTime endTime) {
        if (lessonsServices.createLesson(instructor, lessonType, lessonLevel, capacity, price, startTime, endTime)) {
            return ResponseEntity.ok("Lesson created");
        } else {
            return ResponseEntity.badRequest().body("Error while creating lesson");
        }
    }

    @GetMapping(value = "/getAllLessons")
    public ResponseEntity<List> getAllLessons() {
        try {
            return ResponseEntity.ok(lessonsServices.getAllLessons());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(List.of("Error while getting all lessons. Error: " + e.getMessage()));
        }
    }

    
    
}
