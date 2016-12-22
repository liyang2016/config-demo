package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Parameter;

public interface ParamRepository extends JpaRepository<Parameter, Long> {
	public List<Parameter> findByenvIdAndType(Long envId,int type);
}
