package service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.entities.Customer;
import service.repositories.CustomerRepository;
import service.entities.Application;
import service.repositories.ApplicationRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Boot {

	@Bean
	CommandLineRunner init(CustomerRepository customerRepository, ApplicationRepository applicationRepository) {
		return (a) -> {
			int[] ids = {0, 1, 2, 3};
			for (int id : ids) {
				Customer customer = customerRepository.save(new Customer(id));
				for (int i = 1; i <= id; i++) {
					Thread.sleep(100);
					applicationRepository.save(new Application(customer, id * 10 + i, "Product#" + (id * 10 + i)));
				}
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}
}