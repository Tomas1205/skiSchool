package sk.backend.skiSchool;

import sk.backend.skiSchool.model.Customers;
import sk.backend.skiSchool.repository.CustomerRepository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerCrudTest {

	//Test if you get all customers from database 
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	void testFindAllCustomers() {
		// get all customers from customer repository and save them to list
		List<Customers> customers = customerRepository.findAll();
		int expectedCustomers = 5;
		
		assertThat(customers).hasSize(expectedCustomers);
	}

	@Test
	@Transactional
	void testCreateCustomerSuccess() {
		List<Customers> customersBeforeSave = customerRepository.findAll();
		int expectedCustomersBeforeSave = customersBeforeSave.size();		
		// create new customer
		Customers customer = Customers.builder()
				.firstName("John")
				.lastName("Doe")
				.email("customer@customer.sk")
				.phone("+421904555")
				.build();		
		
		customerRepository.save(customer);
		
		List<Customers> customers = customerRepository.findAll();
		int expectedCustomers = expectedCustomersBeforeSave + 1;

		assertThat(customers)
			.hasSize(expectedCustomers)
			.anyMatch(savedCustomer -> 	savedCustomer.getFirstName().equals("John") &&
										savedCustomer.getLastName().equals("Doe") &&
										savedCustomer.getEmail().equals("customer@customer.sk") &&
										savedCustomer.getPhone().equals("+421904555"));
	};

	@Test
	@Transactional
	void deleteCustomerSuccess() {
		List<Customers> customersBeforeDelete = customerRepository.findAll();
		int customersSizeBeforeDelete = customersBeforeDelete.size();

		Customers customerZero = customersBeforeDelete.get(0);	

		customerRepository.delete(customerZero);

		List<Customers> customersAfterDelete = customerRepository.findAll();

		assertThat(customersAfterDelete)
		.hasSize(customersSizeBeforeDelete - 1);
	}
}
