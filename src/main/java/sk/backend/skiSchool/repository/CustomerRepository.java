package sk.backend.skiSchool.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import sk.backend.skiSchool.model.Customers;

@RepositoryRestResource
@RestResource(exported = false)
public interface CustomerRepository extends JpaRepository<Customers, UUID> {    
}
