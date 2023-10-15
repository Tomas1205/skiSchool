package sk.backend.skiSchool.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sk.backend.skiSchool.model.Instructors;

@RepositoryRestResource(exported = false)
public interface InstructorsRepository extends JpaRepository<Instructors, UUID> {
    
}
