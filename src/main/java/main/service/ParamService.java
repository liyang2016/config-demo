package main.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import main.domain.Parameter;
import main.repository.ParamRepository;

@Service
@Transactional
public class ParamService {
	private final ParamRepository paramRepository;

	public ParamService(ParamRepository paramRepository) {
		this.paramRepository = paramRepository;
	}

	public List<Parameter> findAll() {
		List<Parameter> params = new ArrayList<>();
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
}
