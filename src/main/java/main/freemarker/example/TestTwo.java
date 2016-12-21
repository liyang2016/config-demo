package main.freemarker.example;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestTwo {
	private Configuration cfg;

	public void init() throws Exception {
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setDirectoryForTemplateLoading(new File("src/main/freemarker/ftl"));
	}

	public void process() throws Exception {
		// 构造填充数据的Map
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "lavasoft");
		map.put("url", "http://www.baidu.com/");
		map.put("name", "百度");
		// 创建模版对象
		Template t = cfg.getTemplate("test.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t.process(map, new OutputStreamWriter(System.out));
	}

	public static void main(String[] args) throws Exception {
		TestTwo hf = new TestTwo();
		hf.init();
		hf.process();
	}
}
