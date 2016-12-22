package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Application;

public interface AppRepository extends JpaRepository<Application, Long>{
	public List<Application> findByNameAndVersion(String name,String version);
}
