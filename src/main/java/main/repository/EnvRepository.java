package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Environment;

public interface EnvRepository extends JpaRepository<Environment, Long>{

}
