package service.repositories;

import org.springframework.data.repository.CrudRepository;
import service.entities.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {}