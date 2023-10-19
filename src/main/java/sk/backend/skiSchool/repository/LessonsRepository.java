package sk.backend.skiSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sk.backend.skiSchool.model.Lessons;


@RepositoryRestResource(exported = false)
public interface LessonsRepository extends JpaRepository<Lessons, Long> {
    
}