package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Environment;

public interface EnvRepository extends JpaRepository<Environment, Long> {
	/*
	 * 通过应用的ID值和应用运行环境查出运行环境的唯一ID值
	 */
	public List<Environment> findByAppenvAndAppId(String appenv, Long appId);
}
