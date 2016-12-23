package main.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.controller.SearchController;

/**
 *
 * 执行本地CMD命令
 * 
 * @author liyang
 *
 *
 */
public class CmdUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

	/*
	 * 输入命令行执行CMD命令
	 */
	public static void excuteCommand(String command) {
		Runtime r = Runtime.getRuntime();
		Process p;
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
			String inline;
			while ((inline = br.readLine()) != null) {
				LOGGER.info(inline);
			}
			br.close();
		} catch (IOException e) {
			LOGGER.error("执行失败");
			e.printStackTrace();
		}

	}

}
