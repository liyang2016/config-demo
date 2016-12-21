package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Parameter;

public interface ParamRepository extends JpaRepository<Parameter, Long>{
	
}
