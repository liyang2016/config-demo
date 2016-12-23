package main.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.domain.Application;
import main.service.AppService;

/**
 * 应用管理控制器
 * 
 * @author Administrator
 *
 */

@Controller
public class ApplicationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);
	@Autowired
	private AppService appService;

	/*
	 * 接收HTTP访问
	 * 返回application.jsp
	 */
	@GetMapping("/app")
	public String appHome() {
		return "application";
	}

	/*
	 * 返回所有的应用信息
	 */
	@GetMapping("/all-apps")
	public String allApps(HttpServletRequest request) {
		request.setAttribute("apps", appService.findAll());
		request.setAttribute("mode", "MODE_ALLAPPS");
		return "application";
	}

	/*
	 * 新建应用
	 */
	@GetMapping("/new-app")
	public String newApp(HttpServletRequest request) {
		Date date = new Date();
		request.setAttribute("date", date);
		request.setAttribute("mode", "MODE_NEWAPP");
		return "application";
	}

	/*
	 * 保存提交的应用信息到数据库
	 */
	@PostMapping("/save-app")
	public String saveEnv(@ModelAttribute Application app, BindingResult bind, HttpServletRequest request) {
		app.setUpdateTime(new Date());
		appService.save(app);
		request.setAttribute("apps", appService.findAll());
		request.setAttribute("mode", "MODE_SAVEAPP");
		return "application";
	}

	/*
	 * 更新应用信息
	 */
	@GetMapping("/update-app")
	public String updateEnv(@RequestParam Long id, HttpServletRequest request) {
		request.setAttribute("app", appService.findApp(id));
		request.setAttribute("mode", "MODE_UPDATEAPP");
		return "application";
	}

	/*
	 * 删除应用信息
	 */
	@GetMapping("/delete-app")
	public String deleteEnv(@RequestParam Long id, HttpServletRequest request) {
		appService.delete(id);
		LOGGER.warn("delete application: "+ id);
		request.setAttribute("apps", appService.findAll());
		request.setAttribute("mode", "MODE_DELAPP");
		return "application";
	}
}
