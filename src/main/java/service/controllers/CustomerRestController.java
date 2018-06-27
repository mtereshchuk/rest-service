package service.controllers;

import java.util.Comparator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.entities.Customer;
import service.repositories.CustomerRepository;
import service.entities.Application;
import service.exceptions.ApplicationNotFoundException;
import service.exceptions.CustomerNotFoundException;

@RestController
public class CustomerRestController {

	private final CustomerRepository customerRepository;

	@Autowired
	CustomerRestController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	Application getLastProduct(@PathVariable int customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			Optional<Application> application = customer.get().getApplications().stream().max(Comparator.comparing(Application::getDate));
			return application.orElseThrow(() -> new ApplicationNotFoundException(customerId));
		} else {
			throw new CustomerNotFoundException(customerId);
		}
	}
}