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

import main.domain.Environment;
import main.service.EnvService;


/**
 * 应用运行环境管理控制器
 * @author liyang
 *
 */
@Controller
public class EnvironmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentController.class);
	@Autowired
	private EnvService envService;

	/*
	 * 处理请求
	 * 返回到environment.jsp
	 */
	@GetMapping("/env")
	public String envHome(){
		return "environment";
	}
	
	/*
	 * 返回所有的运行环境信息
	 */
	@GetMapping("/all-envs")
	public String allEnvs(HttpServletRequest request) {
		request.setAttribute("envs", envService.findAll());
		request.setAttribute("mode", "MODE_ALLENVS");
		return "environment";
	}

	/*
	 * 新建运行环境
	 */
	@GetMapping("/new-env")
	public String newEnv(HttpServletRequest request) {
		Date date = new Date();
		request.setAttribute("date", date);
		request.setAttribute("mode", "MODE_NEWENV");
		return "environment";
	}

	/*
	 * 保存提交的运行环境表单信息，到数据库
	 */
	@PostMapping("/save-env")
	public String saveEnv(@ModelAttribute Environment env, BindingResult bind, HttpServletRequest request) {
		env.setUpdateTime(new Date());
		envService.save(env);
		request.setAttribute("envs", envService.findAll());
		request.setAttribute("mode", "MODE_SAVEENV");
		return "environment";
	}

	/*
	 * 更新运行环境信息
	 */
	@GetMapping("/update-env")
	public String updateEnv(@RequestParam Long id, HttpServletRequest request) {
		request.setAttribute("env", envService.findEnv(id));
		request.setAttribute("mode", "MODE_UPDATEENV");
		return "environment";
	}

	/*
	 * 删除运行环境信息
	 */
	@GetMapping("/delete-env")
	public String deleteEnv(@RequestParam Long id, HttpServletRequest request) {
		envService.delete(id);
		LOGGER.warn("delete runtime:"+id);
		request.setAttribute("envs", envService.findAll());
		request.setAttribute("mode", "MODE_DELENV");
		return "environment";
	}
}
