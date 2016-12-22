package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Environment;

public interface EnvRepository extends JpaRepository<Environment, Long> {
	public List<Environment> findByAppenvAndAppId(String appenv, Long appId);
}
