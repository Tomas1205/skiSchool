package sk.backend.skiSchool;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


import sk.backend.skiSchool.model.Customers;
import sk.backend.skiSchool.repository.CustomerRepository;

@SpringBootTest
public class CustomerRestTest {

	//Generate test for CustomerRepository.java class
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	void testGetCustomers() {
		// get all customers from customer repository and save them to list
		List<Customers> customers = customerRepository.findAll();
		System.out.println("Number of customers: " + customers.size());
		System.out.println(customers.toString());		

		assertThat(customers).hasSize(5);
	}
}
