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

public class FreemarkerUtils {

	public static Map<String, String> createModel(List<Parameter> params) {
		Map<String, String> model = new HashMap<>();
		for (Parameter parameter : params) {
			model.put(parameter.getName(), parameter.getValue());
		}
		return model;
	}

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
