package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.Parameter;

public interface ParamRepository extends JpaRepository<Parameter, Long> {
	/*
	 * 通过运行环境的ID与键值的类型，查出一组对应唯一应用唯一运行环境的配置键值对
	 */
	public List<Parameter> findByenvIdAndType(Long envId,int type);
}
