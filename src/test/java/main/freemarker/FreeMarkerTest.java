package main.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {

	private Configuration configuration;

	@Before
	public void init() throws IOException {
		configuration = new Configuration(Configuration.VERSION_2_3_25);
		configuration.setDirectoryForTemplateLoading(new File("src/main/freemarker/ftl"));
	}

	@Test
	public void testOne() throws Exception {
		Template t = new Template(null, new StringReader("用户名：${user};URL：    ${url};姓名： 　${name}"), null);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "lavasoft");
		map.put("url", "http://www.baidu.com/");
		map.put("name", "百度");
		t.process(map, new OutputStreamWriter(System.out));
	}

	@Test
	public void testTwo() throws Exception {
		Map<String, String> root = new HashMap<>();
		root.put("name", "root");
		root.put("pwd", "root");

		Template template = configuration.getTemplate("properties.ftl", "UTF-8");

		File file = new File("src/main/freemarker/dest/test.properties");
		if (!file.exists()) {
			file.createNewFile();
		}
		Writer out = new BufferedWriter(new FileWriter(file));
		template.process(root, out);
		out.flush();
		out.close();
	}

	@Test
	public void testThree() throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "lavasoft");
		map.put("url", "http://www.baidu.com/");
		map.put("name", "百度");
		// 创建模版对象
		Template t = configuration.getTemplate("test.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t.process(map, new OutputStreamWriter(System.out));
	}
}
