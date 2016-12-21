package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Application;

public interface AppRepository extends JpaRepository<Application, Long>{
	
}
