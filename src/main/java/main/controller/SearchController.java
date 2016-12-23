package main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import main.utils.CmdUtil;
import main.utils.FreemarkerUtil;
import main.utils.ZipUtil;
import net.lingala.zip4j.exception.ZipException;

/**
 * 查询配置参数控制器
 * 
 * @author liyang
 *
 */
@Controller
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
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
	 * 根据提交的参数: ppName 应用名称 appVersion 应用版本号 appEnv 应用运行环境 查出用于输出配置文件的参数
	 */
	@PostMapping("/search-fileParams")
	public String fileParam(@ModelAttribute SearchEntity param, HttpServletRequest request, HttpSession session) {
		LOGGER.info("searchInfo=" + param);
		// 查询应用Id
		Long appId = appService.findAppId(param.getAppName(), param.getAppVersion());
		// 通过应用Id与应用运行环境得到文件配置项
		Long envId = envService.findEnvId(appId, param.getAppenv());
		List<Parameter> params = paramService.findFileParameter(envId);

		// 将查询参数、查询出的配置项放入session
		session.setAttribute("search", param);
		session.setAttribute("fileParams", params);
		session.setAttribute("envId", envId);
		// System.out.println(param);

		// 将查询的配置项发送到jsp页面面
		request.setAttribute("params", params);
		request.setAttribute("mode", "MODE_FILEPARAMS");
		return "search";
	}

	/*
	 * 查询出启动配置项
	 */
	@PostMapping("/produce")
	public String produce(HttpServletRequest request, HttpSession session) {
		Long envId = (Long) request.getSession().getAttribute("envId");
		List<Parameter> startParams = paramService.findStartParameter(envId);
		// 将查询到的启动配置项放入session中
		session.setAttribute("startParams", startParams);
		request.setAttribute("params", startParams);
		return "produce";
	}

	/*
	 * 取出session中的数据 执行创建配置文件 执行添加启动参数到命令行运行
	 */
	@PostMapping("/run")
	public String start(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Parameter> startParams = (List<Parameter>) request.getSession().getAttribute("startParams");
		@SuppressWarnings("unchecked")
		List<Parameter> fileParams = (List<Parameter>) request.getSession().getAttribute("fileParams");
		String appenv = ((SearchEntity) request.getSession().getAttribute("search")).getAppenv();
		makeConfigFile(fileParams, appenv);
		startApplication(startParams);
		return "run";
	}

	/*
	 * 生成配置文件
	 */
	public void makeConfigFile(List<Parameter> fileParams, String appenv) {
		Map<String, String> dataModel = FreemarkerUtil.createModel(fileParams);
		try {
			ZipUtil.unzip(SourceInfo.SRC_DIR + ".war", SourceInfo.SRC_DIR, null);
		} catch (ZipException e) {
			LOGGER.error("解压失败");
			e.printStackTrace();
		}
		String templeDir = SourceInfo.SRC_DIR + "\\WEB-INF\\classes\\" + appenv;
		FreemarkerUtil.produce(dataModel, templeDir, SourceInfo.TEMLP, SourceInfo.DEST_FILE);
	}

	/*
	 * 生成启动命令
	 */
	public void startApplication(List<Parameter> startParams) {
		String script = SourceInfo.TOMCAT_COMMAND + "  ";
		for (Parameter param : startParams) {
			script += param.getName();
			script += "=";
			script += param.getValue();
			script += "  ";
		}
		CmdUtil.excuteCommand(script);
	}

}
