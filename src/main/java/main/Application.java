package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.utils.SpringContextUtil;

/**
 * 启动类
 * 
 * @author liyang
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		//让非Spring管理的类获得一个bean
		SpringContextUtil.setApplicationContext(applicationContext);
	}
}
