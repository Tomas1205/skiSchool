package sk.backend.skiSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sk.backend.skiSchool.model.Reservations;

@RepositoryRestResource(path = "reservations")
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    
}
