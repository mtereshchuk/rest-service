package service.repositories;

import org.springframework.data.repository.CrudRepository;
import service.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {}