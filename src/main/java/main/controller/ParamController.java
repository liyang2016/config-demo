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

import main.domain.Parameter;
import main.service.ParamService;

/**
 * 配置参数管理控制器
 * 
 * @author liyang
 *
 */
@Controller
public class ParamController {

	@Autowired
	private ParamService paramService;

	@GetMapping("/param")
	public String paramHome() {
		return "parameter";
	}

	@GetMapping("/all-params")
	public String allParams(HttpServletRequest request) {
		request.setAttribute("params", paramService.findAll());
		request.setAttribute("mode", "MODE_ALLPARAMS");
		return "parameter";
	}

	@GetMapping("/new-param")
	public String newParam(HttpServletRequest request) {
		Date date = new Date();
		request.setAttribute("date", date);
		request.setAttribute("mode", "MODE_NEWPARAM");
		return "parameter";
	}

	@PostMapping("/save-param")
	public String saveParam(@ModelAttribute Parameter param, BindingResult bind, HttpServletRequest request) {
		param.setUpdateTime(new Date());
		paramService.save(param);
		request.setAttribute("params", paramService.findAll());
		request.setAttribute("mode", "MODE_SAVEPARAM");
		return "parameter";
	}

	@GetMapping("/update-param")
	public String updateParam(@RequestParam Long id, HttpServletRequest request) {
		request.setAttribute("param", paramService.findParam(id));
		request.setAttribute("mode", "MODE_UPDATEPARAM");
		return "parameter";
	}

	@GetMapping("/delete-param")
	public String deleteParam(@RequestParam Long id, HttpServletRequest request) {
		paramService.delete(id);
		request.setAttribute("params", paramService.findAll());
		request.setAttribute("mode", "MODE_DELPARAM");
		return "parameter";
	}

}
