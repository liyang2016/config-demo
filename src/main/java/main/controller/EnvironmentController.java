package main.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.Environment;
import main.service.EnvService;

@Controller
public class EnvironmentController {

	@Autowired
	private EnvService envService;

	@GetMapping("/env")
	public String envHome(){
		return "environment";
	}
	
	@GetMapping("/all-envs")
	public String allEnvs(HttpServletRequest request) {
		request.setAttribute("envs", envService.findAll());
		request.setAttribute("mode", "MODE_ALLENVS");
		return "environment";
	}

	@GetMapping("/new-env")
	public String newEnv(HttpServletRequest request) {
		Date date = new Date();
		request.setAttribute("date", date);
		request.setAttribute("mode", "MODE_NEWENV");
		return "environment";
	}

	@PostMapping("/save-env")
	public String saveEnv(@ModelAttribute Environment env, BindingResult bind, HttpServletRequest request) {
		env.setUpdateTime(new Date());
		envService.save(env);
		request.setAttribute("envs", envService.findAll());
		request.setAttribute("mode", "MODE_SAVEENV");
		return "environment";
	}

	@GetMapping("/update-env")
	public String updateEnv(@RequestParam Long id, HttpServletRequest request) {
		request.setAttribute("env", envService.findEnv(id));
		request.setAttribute("mode", "MODE_UPDATEENV");
		return "environment";
	}

	@GetMapping("/delete-env")
	public String deleteEnv(@RequestParam Long id, HttpServletRequest request) {
		envService.delete(id);
		request.setAttribute("envs", envService.findAll());
		request.setAttribute("mode", "MODE_DELENV");
		return "environment";
	}
}
