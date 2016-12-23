package main.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import main.domain.Parameter;
import main.domain.ParameterType;
import main.repository.ParamRepository;

@Service
@Transactional
public class ParamService {

	private final ParamRepository paramRepository;

	public ParamService(ParamRepository paramRepository) {
		this.paramRepository = paramRepository;
	}

	public List<Parameter> findAll() {
		List<Parameter> params = new ArrayList<Parameter>();
		for (Parameter param : paramRepository.findAll()) {
			params.add(param);
		}
		return params;
	}

	public void save(Parameter parameter) {
		paramRepository.save(parameter);
	}

	public Parameter findParam(Long id) {
		return paramRepository.findOne(id);
	}

	public void delete(Long id) {
		paramRepository.delete(id);
	}

	/*
	 * 查询出文件配置项
	 */
	public List<Parameter> findFileParameter(Long envId) {
		return paramRepository.findByenvIdAndType(envId, ParameterType.FILEPARAM);
	}

	/*
	 * 查询出启动配置项
	 */
	public List<Parameter> findStartParameter(Long envId) {
		return paramRepository.findByenvIdAndType(envId, ParameterType.STARTPARAM);
	}
	
}
