package sk.backend.skiSchool.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sk.backend.skiSchool.model.Customers;

@RepositoryRestResource(exported = false)
public interface CustomerRepository extends JpaRepository<Customers, UUID> {    
}
