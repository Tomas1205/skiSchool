package sk.backend.skiSchool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sk.backend.skiSchool.model.Customers;
import sk.backend.skiSchool.repository.CustomerRepository;

@Service
@Transactional
@Validated
public class CustomerServices {
    
    @Autowired
    private CustomerRepository customerRepository;

    public boolean createCustomer(@Valid Customers customer) throws IllegalArgumentException {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Customer first name cannot be null or empty");
        }
        if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Customer last name cannot be null or empty");
        }
        if ((customer.getEmail() == null || customer.getEmail().isEmpty()) && (customer.getPhone() == null || customer.getPhone().isEmpty())) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }

        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    
}
