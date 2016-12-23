package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主控制器
 * 
 * @author liyang
 *
 */
@Controller
public class MainController {

	@GetMapping("/")
	public String home() {
		// request.setAttribute("mode", "MODE_HOME");
		return "index";
	}

}
