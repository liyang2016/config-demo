package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Application;

public interface AppRepository extends JpaRepository<Application, Long>{
	
	/*
	 * 通过应用名称与应用版本，查询出唯一的应用实体
	 */
	public List<Application> findByNameAndVersion(String name,String version);
}
