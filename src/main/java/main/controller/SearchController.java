package main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import main.domain.Parameter;
import main.domain.SearchEntity;
import main.domain.SourceInfo;
import main.service.AppService;
import main.service.EnvService;
import main.service.ParamService;
import main.utils.FreemarkerUtils;
import main.utils.ShellUtil;

/**
 * 查询配置参数控制器
 * @author liyang
 *
 */
@Controller
public class SearchController {

	@Autowired
	private EnvService envService;
	@Autowired
	private ParamService paramService;
	@Autowired
	private AppService appService;

	/*
	 * 接收请求，返回到search.jsp
	 */
	@GetMapping("/search")
	public String search() {
		return "search";
	}

	/*
	 * 输入查询参数页面
	 */
	@GetMapping("/search-param")
	public String searchParma(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_SEARCHFILEPARAM");
		return "search";
	}

	/*
	 * 根据提交的参数:
	 * ppName 应用名称
	 * appVersion 应用版本号
	 * appEnv 应用运行环境
	 *         查出用于输出配置文件的参数
	 */
	@PostMapping("/search-fileParams")
	public String fileParam(@ModelAttribute SearchEntity param, HttpServletRequest request, HttpSession session) {
		//查询应用Id
		Long appId = appService.findAppId(param.getAppName(), param.getAppVersion());
		//通过应用Id与应用运行环境得到文件配置项
		Long envId = envService.findEnvId(appId, param.getAppenv());
		List<Parameter> params = paramService.findFileParameter(envId);
		
		//将查询参数、查询出的配置项放入session
		session.setAttribute("search", param);
		session.setAttribute("fileParams", params);
		session.setAttribute("envId", envId);
		// System.out.println(param);
		
		//将查询的配置项发送到jsp页面面
		request.setAttribute("params", params);
		request.setAttribute("mode", "MODE_FILEPARAMS");
		return "search";
	}

	@PostMapping("/produce")
	public String produce(HttpServletRequest request, HttpSession session) {
		Long envId = (Long) request.getSession().getAttribute("envId");
		List<Parameter> startParams = paramService.findStartParameter(envId);
		session.setAttribute("startParams", startParams);
		request.setAttribute("params", startParams);
		return "produce";
	}

	@PostMapping("/run")
	public String start(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Parameter> startParams = (List<Parameter>) request.getSession().getAttribute("startParams");
		@SuppressWarnings("unchecked")
		List<Parameter> fileParams = (List<Parameter>) request.getSession().getAttribute("fileParams");
		makeConfigFile(fileParams);
		startApplication(startParams);
		return "run";
	}

	public void makeConfigFile(List<Parameter> fileParams) {
		Map<String, String> dataModel = FreemarkerUtils.createModel(fileParams);
		FreemarkerUtils.produce(dataModel, SourceInfo.SRC_DIR, SourceInfo.TEMLP, SourceInfo.DEST_FILE);
	}

	public void startApplication(List<Parameter> startParams) {
		String script = SourceInfo.TOMCAT_COMMAND + "  ";

		for (Parameter param : startParams) {
			script += param.getName();
			script += "=";
			script += param.getValue();
			script += "  ";
		}
		try {
			ShellUtil.ssh(SourceInfo.HOST, SourceInfo.USER, SourceInfo.PASSWORD, script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
