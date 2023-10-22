package sk.backend.skiSchool.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sk.backend.skiSchool.model.Lessons;


@RepositoryRestResource(exported = false)
public interface LessonsRepository extends JpaRepository<Lessons, Long> {
    @Query("select l from Lessons l join l.instructors i where i.instructor_id = :instructorId")
    Set<Lessons> findAllByInstructorId(@Param("instructorId") UUID instructorId);   
}
