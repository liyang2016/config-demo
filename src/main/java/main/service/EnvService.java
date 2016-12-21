package main.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import main.domain.Environment;
import main.repository.EnvRepository;

@Service
@Transactional
public class EnvService {

	private final EnvRepository envRepository;
	
	public EnvService(EnvRepository envRepository){
		this.envRepository=envRepository;
	}
	
	public List<Environment> findAll(){
		List<Environment> envs=new ArrayList<>();
		for(Environment env:envRepository.findAll()){
			envs.add(env);
		}
		return envs;
	}
	
	public Environment findEnv(Long id){
		return envRepository.findOne(id);
	}
	
	public void save(Environment env){
		envRepository.save(env);
	}
	
	public void delete(Long id){
		envRepository.delete(id);
	}
}
