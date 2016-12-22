package main.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import main.domain.Application;
import main.repository.AppRepository;

@Service
@Transactional
public class AppService {

	private final AppRepository appRepository;

	public AppService(AppRepository appRepository) {
		this.appRepository = appRepository;
	}

	public List<Application> findAll() {
		List<Application> apps = new ArrayList<Application>();
		for (Application app : appRepository.findAll()) {
			apps.add(app);
		}
		return apps;
	}

	public void save(Application app) {
		appRepository.save(app);
	}

	public Application findApp(Long id) {
		return appRepository.findOne(id);
	}

	public void delete(Long id) {
		appRepository.delete(id);
	}

	public Long findAppId(String name, String version) {
		return appRepository.findByNameAndVersion(name, version).get(0).getId();
	}
}
