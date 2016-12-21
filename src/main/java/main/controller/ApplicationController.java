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

import main.domain.Application;
import main.service.AppService;

@Controller
public class ApplicationController {

	@Autowired
	private AppService appService;

	@GetMapping("/app")
	public String appHome() {
		return "application";
	}

	@GetMapping("/all-apps")
	public String allApps(HttpServletRequest request) {
		request.setAttribute("apps", appService.findAll());
		request.setAttribute("mode", "MODE_ALLAPPS");
		return "application";
	}

	@GetMapping("/new-app")
	public String newApp(HttpServletRequest request) {
		Date date = new Date();
		request.setAttribute("date", date);
		request.setAttribute("mode", "MODE_NEWAPP");
		return "application";
	}

	@PostMapping("/save-app")
	public String saveEnv(@ModelAttribute Application app, BindingResult bind, HttpServletRequest request) {
		app.setUpdateTime(new Date());
		appService.save(app);
		request.setAttribute("apps", appService.findAll());
		request.setAttribute("mode", "MODE_SAVEAPP");
		return "application";
	}

	@GetMapping("/update-app")
	public String updateEnv(@RequestParam Long id, HttpServletRequest request) {
		request.setAttribute("app", appService.findApp(id));
		request.setAttribute("mode", "MODE_UPDATEAPP");
		return "application";
	}

	@GetMapping("/delete-app")
	public String deleteEnv(@RequestParam Long id, HttpServletRequest request) {
		appService.delete(id);
		request.setAttribute("apps", appService.findAll());
		request.setAttribute("mode", "MODE_DELAPP");
		return "application";
	}
}
