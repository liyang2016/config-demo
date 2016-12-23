package main.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 存放常量配置类
 * @author liyang
 *
 */
public class SourceInfo {
	//war包路径，绝对路径
	public static final String SRC_DIR = readProperties("sourceInfo.src_dir");
	//模版文件名称
	public static final String TEMLP = readProperties("sourceInfo.temple");
	//生成配置文件名称与路径，绝对路径
	public static final String DEST_FILE = readProperties("sourceInfo.dest_file");
	//主机名称
	public static final String HOST = readProperties("sourceInfo.host");
	//登录名
	public static final String USER = readProperties("sourceInfo.user");
	//登录密码
	public static final String PASSWORD = readProperties("sourceInfo.password");
	//Tomcat启动命令
	public static final String TOMCAT_COMMAND = readProperties("sourceInfo.tomcat_command");

	/*
	 * 读取src/main/resources/目录下configinfo.properties文件
	 * 赋值到上面常量配置
	 */
	public static String readProperties(String key){
		Properties properties = new Properties();
		String value="";
		try {
			properties.load(new FileInputStream("src/main/resources/configinfo.properties"));
			value=(String) properties.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
