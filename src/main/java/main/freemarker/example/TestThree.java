package main.freemarker.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestThree {
	public static void main(String[] args) throws Exception {
		Map<String, String> root = new HashMap<>();
		root.put("name", "root");
		root.put("pwd", "root");

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
		configuration.setDirectoryForTemplateLoading(new File("src/main/ftl"));
		Template template = configuration.getTemplate("properties.ftl", "UTF-8");

		File file = new File("src/main/dest/test.properties");
		if (!file.exists()) {
			file.createNewFile();
		}
		Writer out = new BufferedWriter(new FileWriter(file));
		template.process(root, out);
		out.flush();
		out.close();
	}

}
