package main.freemarker.example;

import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Template;

public class TestOne {
	public static void main(String[] args) throws Exception {
		Template t = new Template(null, new StringReader("用户名：${user};URL：    ${url};姓名： 　${name}"), null);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "lavasoft");
		map.put("url", "http://www.baidu.com/");
		map.put("name", "百度");
		t.process(map, new OutputStreamWriter(System.out));
	}
}
