package main.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import main.domain.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTest {

	@Resource
	private AppService appService;

	@Test
	public void findAppIdTest() {
		String name = "demo";
		String version = "0.0.1";
		Long actual = appService.findAppId(name, version);
		Long expected = 2L;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void findAppTest() {
		Long id = 2L;
		Application application = appService.findApp(id);
		Assert.assertEquals(id, application.getId());
	}
}
