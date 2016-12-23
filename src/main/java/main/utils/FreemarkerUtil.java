package main.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import main.domain.Parameter;

/**
 * FreeMarker工具类
 * @author liyang
 *
 */
public class FreemarkerUtil {

	/*
	 * 生成FreeMarker模板需要的数据模型
	 * 
	 * @param List<Parameter> 
	 * 查询数据库得到的Parameter数组
	 * @return Map<String, String>
	 * key表示配置的名称
	 * value表示配置的值
	 */
	public static Map<String, String> createModel(List<Parameter> params) {
		Map<String, String> model = new HashMap<>();
		for (Parameter parameter : params) {
			model.put(parameter.getName(), parameter.getValue());
		}
		return model;
	}

	/*
	 * 生成配置文件
	 * 
	 * @param
	 * dataModel FreeMarker数据模型
	 * srcDir 模板存放目录，绝对路径
	 * templeName 模板文件名，全名带后缀
	 * destFile 生成的文件，路径加文件名
	 * 
	 * @return void
	 */
	public static void produce(Map<String, String> dataModel, String srcDir, String templeName, String destFile) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
		try {
			configuration.setDirectoryForTemplateLoading(new File(srcDir));
			Template template = configuration.getTemplate(templeName, "UTF-8");
			File file = new File(destFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			Writer out = new BufferedWriter(new FileWriter(file));
			template.process(dataModel, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	
}
